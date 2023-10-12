package gg.pixelarcade.skywars.game;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.game.event.*;
import gg.pixelarcade.skywars.game.task.GameCountdownTask;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.utils.Tuple;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.profile.Profile;
import xyz.refinedev.phoenix.utils.CenterUtil;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.general.Tasks;
import gg.pixelarcade.skywars.arena.Arena;
import gg.pixelarcade.skywars.chests.LootableChest;
import xyz.refinedev.skywars.game.event.*;
import gg.pixelarcade.skywars.game.state.GameState;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.game.state.RefillType;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import gg.pixelarcade.skywars.utils.SpawnUtil;

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

    private Map<UUID, Integer> kills = new HashMap<>();

    private RefillType refillType = RefillType.FIRST;
    private GameState state = GameState.WAITING;

    private Date startedAt;
    private Date endedAt;

    private boolean forceStarted = false;
    private boolean active = false;

    public SkyWarsGame(Arena arena, GameType gameType) {
        this.arena = arena;
        this.gameType = gameType;
    }

    public void startCountdown() {
        state = GameState.COUNTDOWN;

        for (UUID uuid : players) {
            Player player = Bukkit.getPlayer(uuid);
            player.teleport(arena.getRandomSpawn());

            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            player.setGameMode(GameMode.SURVIVAL);

            player.setMetadata("frozen", new FixedMetadataValue(SkyWarsPlugin.getInstance(), this));
        }

        Bukkit.getScheduler().runTaskTimer(SkyWarsPlugin.getInstance(), new GameCountdownTask(this), 0L, 20L);
    }

    public void startGame() {
        state = GameState.LIVE;
        alivePlayers.addAll(players);

        active = true;
        startedAt = new Date();

        LootableChest chest = SkyWarsPlugin.getInstance().getChestHandler().getChest(gameType);
        chest.fillChests(this, RefillType.FIRST);

        Tasks.runLater(() -> chest.fillChests(this, RefillType.SECOND), 20 * 60 * 2);
        Tasks.runLater(() -> chest.fillChests(this, RefillType.THIRD), 20 * 60 * 5);
        Tasks.runLater(this::suddenDeath, 20 * 60 * 10);

        for (UUID uuid : alivePlayers) {
            Player player = Bukkit.getPlayer(uuid);
            player.removeMetadata("frozen", SkyWarsPlugin.getInstance());
            SkyWarsPlugin.getInstance().resetPlayer(player);

            kills.put(uuid, 0);

            Profile levelProfile = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());
            SharedAPI.getInstance().getLevelHook().addExperience(levelProfile, 10, "Started a game of SkyWars");

            if (player.getInventory().firstEmpty() == 0) {
                SkyWarsProfile profile = SkyWarsPlugin.getInstance().getProfileHandler().getProfile(player.getUniqueId());
                Kit kit = SkyWarsPlugin.getInstance().getKitHandler().getKitByName(profile.getSelectedKit());

                for (GameType type : kit.getGameType()) {
                    if (type == gameType) {
                        kit.apply(player);
                        break;
                    }
                }
            }
        }

        GameStartEvent event = new GameStartEvent(this);
        Bukkit.getPluginManager().callEvent(event);
    }

    public void suddenDeath() {
        if (!active && state != GameState.LIVE)
            return;

        for (UUID uuid : alivePlayers) {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null)
                continue;

            player.teleport(arena.getSuddenDeathSpawn());
            player.sendMessage(Color.translate("&c&lSudden Death has started!"));
        }
    }

    public void onWin(Player winner) {
        if (winner == null) {
            System.out.println("No winner found");
            this.endGame();
            return;
        }

        SkyWarsProfile skyWarsProfile = SkyWarsPlugin.getInstance().getProfileHandler().getProfile(winner.getUniqueId());
        skyWarsProfile.incrementWins();
        skyWarsProfile.save();

        Profile profile = SharedAPI.getInstance().getProfileHandler().getProfile(winner.getUniqueId());
        SharedAPI.getInstance().getLevelHook().addExperience(profile, 50, "Won a game of SkyWars");

        for (String message : this.sendWinningMessage(profile)) {
            broadcast(message);
        }

        SpawnUtil.teleportToSpawn(winner);
        this.endGame();
    }

    public void broadcast(String message) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendMessage(Color.translate(message));
        }
    }

    public void endGame() {
        state = GameState.ENDED;
        active = false;
        endedAt = new Date();

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

        SkyWarsPlugin.getInstance().getArenaHandler().getWorldManager().deleteWorld(arena.getWorld());
        SkyWarsPlugin.getInstance().getGameHandler().removeGame(this);
    }

    public void addPlayer(Player player) {
        if (state == GameState.LIVE) {
            player.sendMessage("§cYou cannot join this game as it has already started.");
            return;
        }

        PlayerJoinGameEvent event = new PlayerJoinGameEvent(player, this);
        Bukkit.getPluginManager().callEvent(event);

        players.add(player.getUniqueId());
        player.teleport(arena.getLobbySpawn());

        Profile pxProfile = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());
        pxProfile.updateMetadata("inGame", true, false);
    }

    public void removePlayer(Player player) {
        if (state != GameState.LIVE) {
            players.remove(player.getUniqueId());

            arena.removeUsedSpawn(player.getLocation());
            SpawnUtil.teleportToSpawn(player);

            Profile pxProfile = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());
            pxProfile.updateMetadata("inGame", false, false);

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

        SkyWarsProfile profile = SkyWarsPlugin.getInstance().getProfileHandler().getProfile(player.getUniqueId());
        profile.incrementDeaths();
        profile.incrementLosses();
        profile.save();

        if (killer != null) {
            SkyWarsProfile killerProfile = SkyWarsPlugin.getInstance().getProfileHandler().getProfile(killer.getUniqueId());
            killerProfile.incrementKills();
            killerProfile.save();

            kills.put(killer.getUniqueId(), kills.getOrDefault(killer.getUniqueId(), 0) + 1);

            Profile levelProfile = SharedAPI.getInstance().getProfileHandler().getProfile(killer.getUniqueId());
            SharedAPI.getInstance().getLevelHook().addExperience(levelProfile, 10, "Killed a player in SkyWars");
        }

        if (alivePlayers.size() <= 1)
            alivePlayers.stream().findFirst().ifPresentOrElse(winner -> this.onWin(Bukkit.getPlayer(winner)), () -> this.onWin(null));
    }

    public void addSpectator(Player player) {
        spectators.add(player.getUniqueId());

        player.setGameMode(GameMode.SURVIVAL);
        player.setAllowFlight(true);
        player.setFlying(true);

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
        player.setFlying(false);

        Profile pxProfile = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());
        pxProfile.updateMetadata("inGame", false, false);

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

    public List<String> sendWinningMessage(Profile winner) {
        List<Tuple<UUID, String>> topKillers = kills.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit((Math.min(kills.size(), 3)))
                .map(entry -> new Tuple<>(entry.getKey(), SharedAPI.getInstance().getProfileHandler().getProfile(entry.getKey()).getNameWithColor()))
                .toList();

        Tuple<UUID, String> firstKiller = topKillers.get(0);
        Tuple<UUID, String> secondKiller = topKillers.get(1);
        Tuple<UUID, String> thirdKiller = topKillers.get(2);

        return Color.translate(List.of(
                "&7&m----------------------------------------",
                CenterUtil.getCentered("&6&lSkyWars"),
                "",
                CenterUtil.getCentered("&eWinner &7- " + winner.getNameWithColor()),
                "",
                CenterUtil.getCentered("&e&l1st Killer &7- " + firstKiller.value() + " &7(&e" + kills.get(firstKiller.key()) + "&7)"),
                CenterUtil.getCentered("&6&l2nd Killer &7- " + secondKiller.value() + " &7(&e" + kills.get(secondKiller.key()) + "&7)"),
                CenterUtil.getCentered("&c&l3rd Killer &7- " + thirdKiller.value() + " &7(&e" + kills.get(thirdKiller.key()) + "&7)"),
                "",
                "&7&m----------------------------------------"
        ));
    }

}
