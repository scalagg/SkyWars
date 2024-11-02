package gg.pixelarcade.skywars.leaderboard

import eu.decentsoftware.holograms.api.DHAPI
import eu.decentsoftware.holograms.api.holograms.Hologram
import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.leaderboard.impl.LevelLeaderboard
import gg.pixelarcade.skywars.leaderboard.impl.WinsLeaderboard
import gg.pixelarcade.skywars.profile.SkyWarsProfile
import lombok.Getter
import net.evilblock.cubed.util.bukkit.Tasks.delayed
import org.bukkit.Bukkit
import org.bukkit.Location
import java.util.*
import java.util.function.Consumer
import java.util.function.Supplier

/** The handler that manages the leaderboard holograms  */
@Getter
class LeaderboardHologramHandler {
    private val linesMap: MutableMap<String, Supplier<List<String>>>

    /** The holograms that shows their respective leaderboards  */
    private val levels: Hologram
    private val wins: Hologram
    private var thread: HologramUpdateThread? = null

    init {
        // Create the holograms
        levels = DHAPI.createHologram("levels", HologramLocation.LEVELS.location)
        wins = DHAPI.createHologram("wins", HologramLocation.WINS.location)

        // Add titles
        DHAPI.addHologramLine(levels, "&b&nLevels Leaderboard")
        DHAPI.addHologramLine(wins, "&b&nWins Leaderboard")

        // Add a blank line
        DHAPI.addHologramLine(levels, "")
        DHAPI.addHologramLine(wins, "")

        // Add default "loading" line
        for (i in 0..9) {
            DHAPI.addHologramLine(levels, "&7&oLoading...")
            DHAPI.addHologramLine(wins, "&7&oLoading...")
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
                        + " " + it.getPrestige(it.level)!!.color + it.level + " ✯")
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

        levelsLines.forEach(Consumer { it: String -> DHAPI.setHologramLine(levels, levelsLines.indexOf(it) + 2, it) })
        winsLines.forEach(Consumer { it: String -> DHAPI.setHologramLine(wins, winsLines.indexOf(it) + 2, it) })

        levels.updateAll()
        wins.updateAll()
    }

    /** Removes the leaderboards  */
    fun destroy() {
        levels.destroy()
        wins.destroy()
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


    @Getter
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
