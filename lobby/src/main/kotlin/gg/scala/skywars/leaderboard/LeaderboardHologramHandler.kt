package gg.scala.skywars.leaderboard


import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.leaderboard.impl.LevelLeaderboard
import gg.scala.skywars.leaderboard.impl.RankedLeaderboard
import gg.scala.skywars.leaderboard.impl.WinsLeaderboard
import gg.scala.skywars.profile.SkyWarsProfile
import net.evilblock.cubed.entity.EntityHandler
import net.evilblock.cubed.entity.hologram.updating.FormatUpdatingHologramEntity

import net.evilblock.cubed.util.bukkit.Tasks.delayed
import org.bukkit.Bukkit
import org.bukkit.Location
import java.util.*
import java.util.function.Consumer
import java.util.function.Supplier

/** The handler that manages the leaderboard holograms  */

class LeaderboardHologramHandler {
    private val linesMap: MutableMap<String, Supplier<List<String>>>

    /** The holograms that shows their respective leaderboards  */
    private val levels: FormatUpdatingHologramEntity
    private val wins: FormatUpdatingHologramEntity
    private val ranked: FormatUpdatingHologramEntity
    private var thread: HologramUpdateThread? = null

    init {
        // Create the holograms
        levels = FormatUpdatingHologramEntity("&6&nLevels Leaderboard", HologramLocation.LEVELS.location)
        wins = FormatUpdatingHologramEntity("&6&nWins Leaderboard", HologramLocation.WINS.location)
        ranked = FormatUpdatingHologramEntity("&6&nRanked Leaderboard", HologramLocation.RANKED.location)
        levels.initializeData()
        wins.initializeData()
        ranked.initializeData()
        EntityHandler.trackEntity(levels)
        EntityHandler.trackEntity(wins)
        EntityHandler.trackEntity(ranked)

        // Add a blank line
        levels.format.add( "")
        wins.format.add( "")
        ranked.format.add( "")

        // Add default "loading" line
        for (i in 0..9) {
            levels.format.add( "&7&oLoading...")
            wins.format.add( "&7&oLoading...")
            ranked.format.add( "&7&oLoading...")
        }

        // Setup lines suppliers
        linesMap = HashMap()
        linesMap["levels"] = Supplier {
            val profiles = GLOBAL_LEADERBOARD!!.top10().join().stream()
                .map { playerId: UUID? ->
                    shared.getProfile(
                        playerId!!
                    )
                }.toList()
            profiles.stream().map { it: SkyWarsProfile ->
                ("&e" + (profiles.indexOf(it) + 1) + ". &7" + it.niceName
                        + " " + it.getPrestige(it.level).color + it.level + " ✯")
            }.toList()
        }

        linesMap["wins"] = Supplier {
            val profiles = WINS_LEADERBOARD!!.top10().join().stream()
                .map { playerId: UUID? ->
                    shared.getProfile(
                        playerId!!
                    )
                }.toList()
            profiles.stream().map { it: SkyWarsProfile ->
                ("&e" + (profiles.indexOf(it) + 1) + ". &7" + it.niceName
                        + " &e" + it.wins.value + " &7Wins")
            }.toList()
        }

        linesMap["ranked"] = Supplier {
            val profiles = RANKED_LEADERBOARD!!.top10().join().stream()
                .map { playerId: UUID? ->
                    shared.getProfile(
                        playerId!!
                    )
                }.toList()
            profiles.stream().map { it: SkyWarsProfile ->
                ("&e" + (profiles.indexOf(it) + 1) + ". &7" + it.niceName
                        + " &e" + it.elo + " &7Ranked")
            }.toList()
        }

        // Start updater thread
        delayed(20L * 5, Runnable { this.thread = HologramUpdateThread() })
    }

    /** Updates the leaderboards  */
    fun update() {
        val levelsLines = linesMap["levels"]!!.get()
        val winsLines = linesMap["wins"]!!.get()
        val rankedLines = linesMap["ranked"]!!.get()

        levelsLines.forEach(Consumer { it: String -> levels.format[levelsLines.indexOf(it) + 2] = it })
        winsLines.forEach(Consumer { it: String ->  wins.format[winsLines.indexOf(it) + 2] = it })
        rankedLines.forEach(Consumer { it: String ->  ranked.format[rankedLines.indexOf(it) + 2] = it })
    }

    /** Removes the leaderboards  */
    fun destroy() {
        levels.destroyForCurrentWatchers()
        wins.destroyForCurrentWatchers()
        ranked.destroyForCurrentWatchers()

        EntityHandler.forgetEntity(levels)
        EntityHandler.forgetEntity(wins)
        EntityHandler.forgetEntity(ranked)
        thread!!.interrupt()
    }


    private inner class HologramUpdateThread : Thread("SkyWars - Hologram Update Thread") {
        init {
            this.isDaemon = true
            this.start()
        }

        override fun run() {
            while (!isInterrupted) {
                try {
                    update()
                    sleep(1000L * 60)
                } catch (ignored: InterruptedException) {
                }
            }
        }
    }


    
    private enum class HologramLocation(val location: Location) {
        LEVELS(Location(Bukkit.getWorld("world"), -31.5, 91.0, -44.5)),
        WINS(Location(Bukkit.getWorld("world"), -31.5, 91.0, -41.5)),
        RANKED(Location(Bukkit.getWorld("world"), -31.5, 91.0, -38.5))
    }

    companion object {
        private val shared = SkyWarsShared.instance
        private val GLOBAL_LEADERBOARD = shared.leaderboardHandler.getLeaderboard(
            LevelLeaderboard::class.java
        )
        private val WINS_LEADERBOARD = shared.leaderboardHandler.getLeaderboard(
            WinsLeaderboard::class.java
        )
        private val RANKED_LEADERBOARD = shared.leaderboardHandler.getLeaderboard(
            RankedLeaderboard::class.java
        )
    }
}
