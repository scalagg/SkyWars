package xyz.refinedev.skywars.game;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.general.Tasks;
import xyz.refinedev.skywars.arena.Arena;
import xyz.refinedev.skywars.chests.LootableChest;
import xyz.refinedev.skywars.game.event.*;
import xyz.refinedev.skywars.game.state.GameState;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.game.state.RefillType;
import xyz.refinedev.skywars.game.task.GameCountdownTask;
import xyz.refinedev.skywars.kit.Kit;
import xyz.refinedev.skywars.profile.SkyWarsProfile;
import xyz.refinedev.skywars.profile.state.ProfileState;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.utils.SpawnUtil;

import java.util.*;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.game
 * @date 26/09/2023 / 20:43
 */

@Data
public class SkyWarsGame {

    private final UUID id = UUID.randomUUID();

    private Arena arena;
    private GameType gameType;

    private Set<UUID> spectators = new HashSet<>();
    private Set<UUID> players = new HashSet<>();
    private Set<UUID> alivePlayers = new HashSet<>();

    private RefillType refillType = RefillType.FIRST;
    private GameState state = GameState.WAITING;

    private boolean forceStarted = false;

    public SkyWarsGame(Arena arena, GameType gameType) {
        this.arena = arena;
        this.gameType = gameType;
    }

    public void startCountdown() {
        state = GameState.COUNTDOWN;

        for (UUID uuid : players) {
            Player player = Bukkit.getPlayer(uuid);
            player.teleport(arena.getRandomSpawn());

            player.setMetadata("frozen", new FixedMetadataValue(pxSkyWars.getInstance(), this));
        }

        Bukkit.getScheduler().runTaskTimer(pxSkyWars.getInstance(), new GameCountdownTask(this), 0L, 20L);
    }

    public void startGame() {
        state = GameState.LIVE;
        alivePlayers.addAll(players);

        LootableChest chest = pxSkyWars.getInstance().getChestHandler().getChest(gameType);
        chest.fillChests(arena, RefillType.FIRST);

        Tasks.runLater(() -> chest.fillChests(arena, RefillType.SECOND), 20 * 60 * 3);
        Tasks.runLater(() -> chest.fillChests(arena, RefillType.THIRD), 20 * 60 * 6);

        for (UUID uuid : alivePlayers) {
            Player player = Bukkit.getPlayer(uuid);

            player.removeMetadata("frozen", pxSkyWars.getInstance());

            player.getInventory().clear();
            player.getInventory().setArmorContents(null);

            SkyWarsProfile profile = pxSkyWars.getInstance().getProfileHandler().getProfile(player.getUniqueId());
            Kit kit = pxSkyWars.getInstance().getKitHandler().getKitByName(profile.getSelectedKit());
            kit.apply(player);

            player.setHealth(20);
            player.setFoodLevel(20);
        }
    }

    public void onWin(Player winner) {
        if (winner == null) {
            System.out.println("ERROR: Winner is null");
            this.endGame();
            return;
        }

        SkyWarsProfile profile = pxSkyWars.getInstance().getProfileHandler().getProfile(winner.getUniqueId());
        profile.incrementWins();

        profile.setState(ProfileState.LOBBY);
        profile.save();

        SpawnUtil.teleportToSpawn(winner);

        this.endGame();
    }

    public void broadcast(String message) {
        for (UUID uuid : players) {
            Player player = Bukkit.getPlayer(uuid);
            player.sendMessage(Color.translate(message));
        }
    }

    public void endGame() {
        state = GameState.ENDED;

        for (UUID spectator : spectators) {
            Player player = Bukkit.getPlayer(spectator);
            if (player == null)
                continue;

            this.removeSpectator(player);
        }

        GameEndEvent event = new GameEndEvent(this);
        Bukkit.getPluginManager().callEvent(event);

        this.players.clear();
        this.alivePlayers.clear();
        this.spectators.clear();

        pxSkyWars.getInstance().getArenaHandler().getWorldManager().deleteWorld(arena.getWorld());
        pxSkyWars.getInstance().getGameHandler().removeGame(this);
    }

    public void addPlayer(Player player) {
        if (state == GameState.LIVE) {
            player.sendMessage("§cYou cannot join this game as it has already started.");
            return;
        }

        PlayerJoinGameEvent event = new PlayerJoinGameEvent(player, this);
        Bukkit.getPluginManager().callEvent(event);

        SkyWarsProfile profile = pxSkyWars.getInstance().getProfileHandler().getProfile(player.getUniqueId());
        profile.setState(ProfileState.GAME);

        players.add(player.getUniqueId());
        player.teleport(arena.getLobbySpawn());
    }

    public void removePlayer(Player player) {
        if (state != GameState.LIVE) {
            players.remove(player.getUniqueId());

            arena.removeUsedSpawn(player.getLocation());
            SpawnUtil.teleportToSpawn(player);

            SkyWarsProfile profile = pxSkyWars.getInstance().getProfileHandler().getProfile(player.getUniqueId());
            profile.setState(ProfileState.LOBBY);

            PlayerLeavePreGameEvent event = new PlayerLeavePreGameEvent(player, this);
            Bukkit.getPluginManager().callEvent(event);
            return;
        }

        onDeath(player, null);
        PlayerLeaveGameEvent event = new PlayerLeaveGameEvent(player, this);
        Bukkit.getPluginManager().callEvent(event);
    }

    public void setState(GameState state) {
        GameState oldState = this.state;
        this.state = state;

        GameStateChangeEvent event = new GameStateChangeEvent(this, oldState, state);
        Bukkit.getPluginManager().callEvent(event);
    }

    public void onDeath(Player player, Player killer) {
        alivePlayers.remove(player.getUniqueId());
        this.addSpectator(player);

        SkyWarsProfile profile = pxSkyWars.getInstance().getProfileHandler().getProfile(player.getUniqueId());
        profile.incrementDeaths();
        profile.incrementLosses();
        profile.save();

        if (killer != null) {
            SkyWarsProfile killerProfile = pxSkyWars.getInstance().getProfileHandler().getProfile(killer.getUniqueId());
            killerProfile.incrementKills();
            killerProfile.save();
        }

        if (alivePlayers.size() <= 1) {
            this.onWin(killer);
        }
    }

    public void addSpectator(Player player) {
        spectators.add(player.getUniqueId());

        player.setGameMode(GameMode.SURVIVAL);
        player.setAllowFlight(true);
        player.setFlying(true);

        SkyWarsProfile profile = pxSkyWars.getInstance().getProfileHandler().getProfile(player.getUniqueId());
        profile.setState(ProfileState.SPECTATING);

        for (UUID alive : alivePlayers) {
            Player alivePlayer = Bukkit.getPlayer(alive);
            if (alivePlayer == null)
                continue;

            alivePlayer.hidePlayer(player);
        }

        for (UUID spectator : spectators) {
            Player spectatorPlayer = Bukkit.getPlayer(spectator);
            if (spectatorPlayer == null)
                continue;

            spectatorPlayer.showPlayer(player);
        }
    }

    public void removeSpectator(Player player) {
        SpawnUtil.teleportToSpawn(player);

        player.setGameMode(GameMode.SURVIVAL);

        SkyWarsProfile profile = pxSkyWars.getInstance().getProfileHandler().getProfile(player.getUniqueId());
        profile.setState(ProfileState.LOBBY);
        profile.save();

        for (UUID alive : alivePlayers) {
            Player alivePlayer = Bukkit.getPlayer(alive);
            if (alivePlayer == null)
                continue;

            alivePlayer.showPlayer(player);
        }
    }

    public boolean hasStarted() {
        return state == GameState.LIVE || state == GameState.ENDED;
    }

    public boolean isInGame(Player player) {
        return players.contains(player.getUniqueId());
    }

    public boolean isInGame(UUID player) {
        return players.contains(player);
    }

    public boolean isSpectating(Player player) {
        return spectators.contains(player.getUniqueId());
    }

    public boolean isSpectating(UUID player) {
        return spectators.contains(player);
    }

}
