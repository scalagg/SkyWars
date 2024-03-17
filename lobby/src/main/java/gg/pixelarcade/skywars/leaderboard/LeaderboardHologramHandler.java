package gg.pixelarcade.skywars.leaderboard;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.leaderboard.impl.LevelLeaderboard;
import gg.pixelarcade.skywars.leaderboard.impl.WinsLeaderboard;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import xyz.refinedev.phoenix.utils.general.Tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Getter
public class LeaderboardHologramHandler {
    private static final SkyWarsShared shared = SkyWarsShared.getInstance();
    private static final LevelLeaderboard GLOBAL_LEADERBOARD
            = shared.getLeaderboardHandler().getLeaderboard(LevelLeaderboard.class);
    private static final WinsLeaderboard WINS_LEADERBOARD
            = shared.getLeaderboardHandler().getLeaderboard(WinsLeaderboard.class);

    private final Map<String, Supplier<List<String>>> linesMap;
    private final Hologram levels, wins;
    private HologramUpdateThread thread;

    public LeaderboardHologramHandler() {
        levels = DHAPI.createHologram("levels", HologramLocation.LEVELS.getLocation());
        wins = DHAPI.createHologram("wins", HologramLocation.WINS.getLocation());

        DHAPI.addHologramLine(levels, "&b&nLevels Leaderboard");
        DHAPI.addHologramLine(wins, "&b&nWins Leaderboard");

        DHAPI.addHologramLine(levels, "");
        DHAPI.addHologramLine(wins, "");

        for (int i = 0; i < 10; i++) {
            DHAPI.addHologramLine(levels, "&7&oLoading...");
            DHAPI.addHologramLine(wins, "&7&oLoading...");
        }

        linesMap = new HashMap<>();
        linesMap.put("levels", () -> {
            List<SkyWarsProfile> profiles = GLOBAL_LEADERBOARD.top10().join().stream()
                    .map(it -> shared.getProfileHandler().getProfile(it)).toList();

            return profiles.stream().map(it ->
                    "&e" + (profiles.indexOf(it) +1) + ". &7" + it.getNiceName()
                            + " " + it.getPrestige(it.getLevel()).getColor() + it.getLevel() + " ✯"
            ).toList();
        });

        linesMap.put("wins", () -> {
            List<SkyWarsProfile> profiles = WINS_LEADERBOARD.top10().join().stream()
                    .map(it -> shared.getProfileHandler().getProfile(it)).toList();

            return profiles.stream().map(it ->
                    "&e" + (profiles.indexOf(it) +1) + ". &7" + it.getNiceName()
                            + " &e" + it.getWins() + " &7Wins"
            ).toList();
        });

        Tasks.runLater(() -> this.thread = new HologramUpdateThread(), 20L * 5);
    }

    public void update() {
        List<String> levelsLines = linesMap.get("levels").get();
        List<String> winsLines = linesMap.get("wins").get();

        levelsLines.forEach(it -> DHAPI.setHologramLine(levels, levelsLines.indexOf(it) +2, it));
        winsLines.forEach(it -> DHAPI.setHologramLine(wins, winsLines.indexOf(it) +2, it));

        levels.updateAll();
        wins.updateAll();
    }

    public void destroy() {
        levels.destroy();
        wins.destroy();
        thread.interrupt();
    }


    private class HologramUpdateThread extends Thread {
        public HologramUpdateThread() {
            super("SkyWars - Hologram Update Thread");
            this.setDaemon(true);
            this.start();
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    update();
                    Thread.sleep(1_000L * 60);
                } catch (InterruptedException ignored) {}
            }
        }
    }


    @Getter
    private enum HologramLocation {
        LEVELS(new Location(Bukkit.getWorld("world"), -14.5, 104, 2.5)),
        WINS(new Location(Bukkit.getWorld("world"), -14.5, 104, -1.5));

        private final Location location;

        HologramLocation(Location location) {
            this.location = location;
        }
    }
}
