package gg.pixelarcade.skywars.leaderboard

import gg.pixelarcade.skywars.leaderboard.impl.LevelLeaderboard
import gg.pixelarcade.skywars.leaderboard.impl.RankedLeaderboard
import gg.pixelarcade.skywars.leaderboard.impl.WinsLeaderboard
import gg.pixelarcade.skywars.leaderboard.impl.WinstreakLeaderboard


class LeaderboardHandler {
    /** A map of the registered leaderboards, mapped by their id  */
    private val leaderboards: MutableMap<String, Leaderboard> = HashMap()

    init {
        registerLeaderboard(LevelLeaderboard())
        registerLeaderboard(RankedLeaderboard())
        registerLeaderboard(WinsLeaderboard())
        registerLeaderboard(WinstreakLeaderboard())
    }

    /**
     * Register a leaderboard
     *
     * @param leaderboard the leaderboard to register
     */
    fun registerLeaderboard(leaderboard: Leaderboard) {
        leaderboards[leaderboard.id] = leaderboard
    }

    /**
     * Get a leaderboard by its class
     *
     * @param clazz the class of the leaderboard
     * @return the leaderboard
     */
    fun <T : Leaderboard?> getLeaderboard(clazz: Class<T>): T? {
        return leaderboards.values.stream()
            .filter { it: Leaderboard? -> it!!.javaClass == clazz }
            .findFirst().orElse(null) as T?
    }

    /**
     * Get a leaderboard by its id
     *
     * @param id the id of the leaderboard
     * @return the leaderboard
     */
    fun getLeaderboard(id: String): Leaderboard? {
        return leaderboards[id]
    }

    fun getLeaderboards(): Collection<Leaderboard> {
        return leaderboards.values;
    }
}
