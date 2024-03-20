package gg.pixelarcade.skywars.game;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.Vector;
import gg.pixelarcade.skywars.SkyWarsGame;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.chests.LootableChest;
import gg.pixelarcade.skywars.chests.type.RefillType;
import gg.pixelarcade.skywars.config.GameConfig;
import gg.pixelarcade.skywars.game.event.*;
import gg.pixelarcade.skywars.game.state.GameState;
import gg.pixelarcade.skywars.game.state.GameTeamType;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.game.task.GameCountdownTask;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.map.GameMap;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import gg.pixelarcade.skywars.quests.Quest;
import gg.pixelarcade.skywars.util.PlayerUtil;
import gg.pixelarcade.skywars.util.Tuple;
import gg.pixelarcade.skywars.util.WorldEditUtils;
import gg.pixelarcade.skywars.util.cuboid.Cuboid;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.profile.Profile;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.general.Tasks;

import java.util.*;

import static xyz.refinedev.phoenix.utils.CenterUtil.getCentered;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.handler
 * @date 26/09/2023 / 21:16
 */

@Getter
public class GameHandler {

    private final Cuboid bounds = new Cuboid(GameConfig.SETTINGS.firstCorner, GameConfig.SETTINGS.secondCorner);

    @Getter
    private final Game game;
    private final GameMap map = new GameMap(
            GameConfig.SETTINGS.name,
            bounds,
            GameConfig.SETTINGS.type);

    public GameHandler() {
        //TODO: make a method that can pass in a gameType when launching the server (depending on in what way it was launched)
        this.game = new Game(GameConfig.SETTINGS.type);
    }

    public void startCountdown() {
        game.setState(GameState.COUNTDOWN);

        for (UUID uuid : game.getPlayers()) {
            SkyWarsProfile profile = SkyWarsGame.getGameInstance().getProfileHandler().getProfile(uuid);
            Player player = Bukkit.getPlayer(uuid);

            // Paste the cage for the player
            Vector vector = WorldEditUtils.getVectorByLocation(map.getRandomSpawn());
            try {
                WorldEditUtils.pasteSchematic(
                        profile.getCurrentCage(game.getGameTeamType()).getFilePath(),
                        vector);
            } catch (Exception e) {
                SkyWarsPlugin.getInstance().getLogger().severe("Failed to paste cage for " + player.getName());
                e.printStackTrace();
            }

            player.teleport(map.getRandomSpawn());

            PlayerUtil.resetPlayer(player);
            player.setMetadata("frozen", new FixedMetadataValue(SkyWarsPlugin.getInstance(), this));
        }

        Bukkit.getScheduler().runTaskTimer(SkyWarsPlugin.getInstance(), new GameCountdownTask(), 0L, 20L);
    }

    public void startGame() {
        game.setState(GameState.LIVE);
        game.getAlivePlayers().addAll(game.getPlayers());

        game.setActive(true);
        game.setStartedAt(new Date());

        // Remove the cages from the map after the game has started
        for (Location location : map.getUsedSpawns()) {
            WorldEditUtils.clear(
                    WorldEditUtils.getVectorByLocation(location),
                    WorldEditUtils.getVectorByLocation(location.add(10, 7, 10))
            );
        }

        LootableChest chest = SkyWarsGame.getGameInstance().getChestHandler().getChest(game.getGameType());
        chest.fillChests(game, RefillType.FIRST);

        Tasks.runLater(() -> chest.fillChests(game, RefillType.SECOND), Game.SECOND_REFILL_DELAY);
        Tasks.runLater(() -> chest.fillChests(game, RefillType.THIRD), Game.THIRD_REFILL_DELAY);
        Tasks.runLater(this::suddenDeath, Game.SUDDEN_DEATH_COMMENCES);

        for (UUID uuid : game.getPlayers()) {
            Player player = Bukkit.getPlayer(uuid);
            player.removeMetadata("frozen", SkyWarsPlugin.getInstance());

            PlayerUtil.resetPlayer(player);

            game.getKills().put(uuid, 0);

            Profile levelProfile = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());
            SharedAPI.getInstance().getLevelHook().addExperience(levelProfile, 10, "Started a game of SkyWarsGame");

            if (player.getInventory().firstEmpty() == 0) {
                SkyWarsProfile profile = SkyWarsGame.getGameInstance().getProfileHandler().getProfile(player.getUniqueId());
                Kit kit = SkyWarsGame.getGameInstance().getKitHandler().getKitByName(profile.getSelectedKit());

//                if (kit.getGameType() == game.getGameType()) {
//                for (GameType type : kit.getGameType()) {
//                    if (type == game.getGameType()) {
//                        kit.equipKit(kit, player);
//                        break;
//                    }
//                }
// }
            }
        }

        GameStartEvent event = new GameStartEvent(game);
        Bukkit.getPluginManager().callEvent(event);
    }

    public void suddenDeath() {
        if (!game.isActive())
            return;

        for (UUID uuid : game.getAlivePlayers()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null)
                continue;

            player.teleport(map.getSuddenDeathSpawn());
            player.sendMessage(Color.translate("&c&lSudden Death has started!"));
        }
    }

    public void onWin(Player winner) {
        if (winner == null) {
            System.out.println("No winner found for Game " + game.getId() + "!");
            endGame();
            return;
        }

        game.setWinner(winner.getUniqueId());

        // Gift the winner their new stats
        SkyWarsProfile skyWarsProfile = SkyWarsGame.getInstance().getProfileHandler().getProfile(winner.getUniqueId());
        skyWarsProfile.incrementWins();
        skyWarsProfile.incrementXP(30);
        skyWarsProfile.incrementCoins(150);

        if (game.getGameType() == GameType.RANKED) {
            int kills = game.getKills().get(winner.getUniqueId());
            int elo = skyWarsProfile.calculateFinalRating(skyWarsProfile.getElo(), true, kills);
            skyWarsProfile.incrementElo(elo);
        }

        skyWarsProfile.save();

        Profile profile = SharedAPI.getInstance().getProfileHandler().getProfile(winner.getUniqueId());
        for (String message : this.sendWinningMessage(profile))
            Bukkit.broadcastMessage(message);

        SharedAPI.getInstance().getLevelHook().addExperience(profile, 50, "WON!");
        SkyWarsGame.getInstance().getServerHandler().sendToLobby(winner);

        for (UUID uuid : game.getSpectators()) {
            Player spectator = Bukkit.getPlayer(uuid);
            if (spectator == null)
                continue;

            SkyWarsGame.getInstance().getServerHandler().sendToLobby(spectator);
        }

        endGame();
    }

    public void endGame() {
        game.setState(GameState.ENDED);
        game.setActive(false);
        game.setEndedAt(new Date());

        GameEndEvent event = new GameEndEvent(game);
        Bukkit.getPluginManager().callEvent(event);

        game.getPlayers().clear();
        game.getAlivePlayers().clear();
        game.getSpectators().clear();

        Bukkit.shutdown();
    }

    public void playSound(Sound sound) {
        for (Player player : Bukkit.getOnlinePlayers())
            player.playSound(player.getLocation(), sound, 1.0F, 1.0F);
    }

    public void addPlayer(Player player) {
        if (game.getState() == GameState.LIVE) {
            player.sendMessage("§cYou cannot join this game as it has already started.");
            return;
        }

        PlayerJoinGameEvent event = new PlayerJoinGameEvent(player, game);
        Bukkit.getPluginManager().callEvent(event);

        game.getPlayers().add(player.getUniqueId());
        player.teleport(map.getLobbySpawn());
    }

    public void removePlayer(Player player) {
        if (game.getState() != GameState.LIVE) {
            game.getPlayers().remove(player.getUniqueId());

            map.removeUsedSpawn(player.getLocation());

            SkyWarsGame.getInstance().getServerHandler().sendToLobby(player);
            return;
        }

        onDeath(player, null);
        PlayerLeaveGameEvent event = new PlayerLeaveGameEvent(player, game);
        Bukkit.getPluginManager().callEvent(event);
    }

    public void onDeath(Player player, Player killer) {
        game.getAlivePlayers().remove(player.getUniqueId());
        this.addSpectator(player);

        SkyWarsProfile profile = SkyWarsGame.getGameInstance().getProfileHandler().getProfile(player.getUniqueId());
        profile.incrementDeaths();
        profile.incrementLosses();

        if (game.getGameType() == GameType.RANKED) {
            int kills = game.getKills().get(player.getUniqueId());
            int elo = profile.calculateFinalRating(profile.getElo(), false, kills);
            profile.incrementElo(elo);
        }

        profile.save();

        if (killer != null) {
            SkyWarsProfile killerProfile = SkyWarsGame.getGameInstance().getProfileHandler().getProfile(killer.getUniqueId());
            killerProfile.getTracker("twenty_kills").increment();
            killerProfile.incrementKills();
            killerProfile.save();

            game.getKills().put(killer.getUniqueId(), game.getKills().getOrDefault(killer.getUniqueId(), 0) + 1);

            Profile levelProfile = SharedAPI.getInstance().getProfileHandler().getProfile(killer.getUniqueId());
            SharedAPI.getInstance().getLevelHook().addExperience(levelProfile, 10, "Killed a player in SkyWars Game");
        }

        if (game.getGameTeamType() == GameTeamType.SOLO) {
            if (game.getAlivePlayers().size() <= 1)
                game.getAlivePlayers().stream()
                        .findFirst()
                        .ifPresentOrElse(winner -> this.onWin(Bukkit.getPlayer(winner)), () -> this.onWin(null));
        } else {
            // Team game

        }
    }

    public void addSpectator(Player player) {
        game.getSpectators().add(player.getUniqueId());

        PlayerUtil.resetPlayer(player);

        player.teleport(map.getSpectatorSpawn());
        player.setAllowFlight(true);
        player.setFlying(true);

        for (UUID alive : game.getAlivePlayers()) {
            Player alivePlayer = Bukkit.getPlayer(alive);
            if (alivePlayer == null)
                continue;

            alivePlayer.hidePlayer(player);
        }

        for (UUID spectator : game.getSpectators()) {
            Player spectatorPlayer = Bukkit.getPlayer(spectator);
            if (spectatorPlayer == null)
                continue;

            spectatorPlayer.showPlayer(player);
        }
    }

    public boolean hasStarted() {
        return game.getState() == GameState.LIVE;
    }

    public boolean isSpectating(UUID player) {
        return game.getSpectators().contains(player);
    }

    public List<String> sendWinningMessage(Profile winner) {
        List<Tuple<UUID, String>> topKillers = game.getKills().entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit((Math.min(game.getKills().size(), 3)))
                .map(entry -> new Tuple<>(entry.getKey(), SharedAPI.getInstance().getProfileHandler().getProfile(entry.getKey()).getNameWithColor()))
                .toList();

        Tuple<UUID, String> firstKiller = topKillers.get(0);
        Tuple<UUID, String> secondKiller = topKillers.get(1);
        Tuple<UUID, String> thirdKiller = topKillers.get(2);

        return Color.translate(List.of(
                "&7&m----------------------------------------",
                getCentered("&6&lSkyWars"),
                "",
                getCentered("&eWinner &7- " + winner.getNameWithColor()),
                "",
                getCentered("&e&l1st Killer &7- " + firstKiller.value() + " &7(&e" + game.getKills().get(firstKiller.key()) + "&7)"),
                getCentered("&6&l2nd Killer &7- " + secondKiller.value() + " &7(&e" + game.getKills().get(secondKiller.key()) + "&7)"),
                getCentered("&c&l3rd Killer &7- " + thirdKiller.value() + " &7(&e" + game.getKills().get(thirdKiller.key()) + "&7)"),
                "",
                "&7&m----------------------------------------"
        ));
    }
}
