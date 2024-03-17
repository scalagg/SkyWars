package gg.pixelarcade.skywars.leaderboard;

import gg.pixelarcade.skywars.leaderboard.impl.LevelLeaderboard;
import gg.pixelarcade.skywars.leaderboard.impl.RankedLeaderboard;
import gg.pixelarcade.skywars.leaderboard.impl.WinsLeaderboard;
import gg.pixelarcade.skywars.leaderboard.impl.WinstreakLeaderboard;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class LeaderboardHandler {
    @Getter
    private final Map<String, Leaderboard> leaderboards = new HashMap<>();

    public LeaderboardHandler() {
        registerLeaderboard(new LevelLeaderboard());
        registerLeaderboard(new RankedLeaderboard());
        registerLeaderboard(new WinsLeaderboard());
        registerLeaderboard(new WinstreakLeaderboard());
    }

    /**
     * Register a leaderboard
     *
     * @param leaderboard the leaderboard to register
     */
    public void registerLeaderboard(Leaderboard leaderboard) {
        leaderboards.put(leaderboard.getId(), leaderboard);
    }

    /**
     * Get a leaderboard by its class
     *
     * @param clazz the class of the leaderboard
     * @return the leaderboard
     */
    public <T extends Leaderboard> T getLeaderboard(Class<T> clazz) {
        return (T) leaderboards.values().stream()
                .filter(it -> it.getClass().equals(clazz))
                .findFirst().orElse(null);
    }

    /**
     * Get a leaderboard by its id
     *
     * @param id the id of the leaderboard
     * @return the leaderboard
     */
    public Leaderboard getLeaderboard(String id) {
        return leaderboards.get(id);
    }
}
