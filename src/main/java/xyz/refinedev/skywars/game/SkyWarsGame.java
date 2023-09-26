package xyz.refinedev.skywars.game;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.skywars.arena.Arena;
import xyz.refinedev.skywars.game.event.GameStateChangeEvent;
import xyz.refinedev.skywars.game.event.PlayerJoinGameEvent;
import xyz.refinedev.skywars.game.event.PlayerLeaveGameEvent;
import xyz.refinedev.skywars.game.event.PlayerLeavePreGameEvent;
import xyz.refinedev.skywars.game.state.ChestType;
import xyz.refinedev.skywars.game.state.GameState;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.game.state.RefillType;
import xyz.refinedev.skywars.game.task.GameCountdownTask;
import xyz.refinedev.skywars.kit.Kit;
import xyz.refinedev.skywars.profile.SkyWarsProfile;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.utils.RefillUtil;
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

    public SkyWarsGame(Arena arena, GameType gameType) {
        this.arena = arena;
        this.gameType = gameType;
    }

    public void startCountdown() {
        state = GameState.COUNTDOWN;

        for (UUID uuid : players) {
            Player player = Bukkit.getPlayer(uuid);
            arena.getSpawns().forEach(player::teleport);

            player.setMetadata("frozen", new FixedMetadataValue(pxSkyWars.getInstance(), this));
        }

        Bukkit.getScheduler().runTaskTimer(pxSkyWars.getInstance(), new GameCountdownTask(this), 0L, 20L);
    }

    public void startGame() {
        state = GameState.LIVE;
        alivePlayers.addAll(players);

        this.fillChests();

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
        if (winner == null)
            return;

        SkyWarsProfile profile = pxSkyWars.getInstance().getProfileHandler().getProfile(winner.getUniqueId());
        profile.incrementWins();
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

        players.add(player.getUniqueId());
    }

    public void removePlayer(Player player) {
        if (state != GameState.LIVE) {
            players.remove(player.getUniqueId());

            PlayerLeavePreGameEvent event = new PlayerLeavePreGameEvent(player, this);
            Bukkit.getPluginManager().callEvent(event);
            return;
        }

        PlayerLeaveGameEvent event = new PlayerLeaveGameEvent(player, this);
        Bukkit.getPluginManager().callEvent(event);
        onDeath(player, null);
    }

    public void fillChests() {
        for (Map.Entry<Location, ChestType> entry : arena.getChests().entrySet()) {
            Block block = arena.getBounds().getWorld().getBlockAt(entry.getKey());

            if (refillType == RefillType.SECOND && entry.getValue() != ChestType.MIDDLE && (!block.hasMetadata("placedByGame"))) {
                continue;
            }

            if (refillType == RefillType.THIRD && entry.getValue() == ChestType.MIDDLE && block.getType() == Material.AIR) {
                block.setType(Material.CHEST);
            }

            if (!(block instanceof Chest))
                continue;

            Chest chest = (Chest) block;

            List<ItemStack> items = entry.getValue() == ChestType.SPAWN
                    ? RefillUtil.getRandomizedSpawnItems(gameType, refillType) : RefillUtil.getRandomizedMiddleItems(gameType, refillType);

            int itemsToAdd = pxSkyWars.getInstance().getRandom().nextInt(1, 6);

            Inventory inventory = chest.getBlockInventory();

            for (int i = 0; i < itemsToAdd; i++) {
                int slot = pxSkyWars.getInstance().getRandom().nextInt(27);

                inventory.setItem(slot, items.get(pxSkyWars.getInstance().getRandom().nextInt(items.size())));
            }
        }
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

        if (killer == null)
            return;

        SkyWarsProfile killerProfile = pxSkyWars.getInstance().getProfileHandler().getProfile(killer.getUniqueId());
        killerProfile.incrementKills();
        killerProfile.save();

        if (alivePlayers.size() == 1)
            this.onWin(killer);
        else if (alivePlayers.isEmpty())
            this.onWin(null);
    }

    public void addSpectator(Player player) {
        spectators.add(player.getUniqueId());

        for (Player alivePlayers : Bukkit.getOnlinePlayers()) {
            if (spectators.contains(alivePlayers.getUniqueId()))
                continue;

            player.hidePlayer(alivePlayers);
        }

        player.teleport(arena.getSpectatorSpawn());
    }

    public void removeSpectator(Player player) {
        spectators.remove(player.getUniqueId());
        SpawnUtil.teleportToSpawn(player);

        for (Player players : Bukkit.getOnlinePlayers())
            player.showPlayer(players);
    }

    public boolean hasStarted() {
        return state != GameState.LIVE;
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
