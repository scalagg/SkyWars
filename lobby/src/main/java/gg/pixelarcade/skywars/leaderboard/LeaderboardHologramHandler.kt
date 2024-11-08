package gg.pixelarcade.skywars.leaderboard


import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.leaderboard.impl.LevelLeaderboard
import gg.pixelarcade.skywars.leaderboard.impl.WinsLeaderboard
import gg.pixelarcade.skywars.profile.SkyWarsProfile
import net.evilblock.cubed.entity.EntityHandler
import net.evilblock.cubed.entity.hologram.updating.FormatUpdatingHologramEntity
import net.evilblock.cubed.entity.hologram.updating.UpdatingHologramEntity

import net.evilblock.cubed.util.bukkit.Tasks.delayed
import net.minecraft.server.v1_8_R3.EntityTracker
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
    private var thread: HologramUpdateThread? = null

    init {
        // Create the holograms
        levels = FormatUpdatingHologramEntity("levels", HologramLocation.LEVELS.location)
        wins = FormatUpdatingHologramEntity("wins", HologramLocation.WINS.location)
        levels.initializeData()
        wins.initializeData()
        EntityHandler.trackEntity(levels)
        EntityHandler.trackEntity(wins)

        // Add titles
       levels.format.add("&b&nLevels Leaderboard")
        wins.format.add("&b&nWins Leaderboard")

        // Add a blank line
        levels.format.add( "")
        wins.format.add( "")

        // Add default "loading" line
        for (i in 0..9) {
            levels.format.add( "&7&oLoading...")
            wins.format.add( "&7&oLoading...")
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
                        + " &e" + it.wins + " &7Wins")
            }.toList()
        }

        // Start updater thread
        delayed(20L * 5, Runnable { this.thread = HologramUpdateThread() })
    }

    /** Updates the leaderboards  */
    fun update() {
        val levelsLines = linesMap["levels"]!!.get()
        val winsLines = linesMap["wins"]!!.get()

        levelsLines.forEach(Consumer { it: String -> levels.format[levelsLines.indexOf(it) + 2] = it })
        winsLines.forEach(Consumer { it: String ->  wins.format[winsLines.indexOf(it) + 2] = it })
    }

    /** Removes the leaderboards  */
    fun destroy() {
        levels.destroyForCurrentWatchers()
        wins.destroyForCurrentWatchers()
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
        LEVELS(Location(Bukkit.getWorld("world"), -14.5, 104.0, 2.5)),
        WINS(Location(Bukkit.getWorld("world"), -14.5, 104.0, -1.5))
    }

    companion object {
        private val shared = SkyWarsShared.instance
        private val GLOBAL_LEADERBOARD = shared.leaderboardHandler.getLeaderboard(
            LevelLeaderboard::class.java
        )
        private val WINS_LEADERBOARD = shared.leaderboardHandler.getLeaderboard(
            WinsLeaderboard::class.java
        )
    }
}
