package gg.scala.skywars.game.task

import gg.scala.skywars.SkyWarsGame
import gg.scala.skywars.SkyWarsPlugin

import net.evilblock.cubed.util.Color
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.scheduler.BukkitRunnable

/**
 * @author alfie
 * @project Skywars / gg.scala.game.game.task
 * @date 26/09/2023 / 21:36
 */

class GameCountdownTask : BukkitRunnable() {
    private val game = SkyWarsGame.gameInstance.gameHandler
    private val reminders = intArrayOf(30, 15, 10, 5, 4, 3, 2, 1)

    private var countdown = (if (SkyWarsPlugin.instance.debug) 6 else 31)

    override fun run() {
        if (--countdown == 0) {
            Bukkit.broadcastMessage(Color.translate("&eGame started."))
            game.startGame()
            cancel()
            return
        }

        for (reminder in reminders) {
            if (countdown == reminder) {
                Bukkit.broadcastMessage(Color.translate("&eGame starting in &c$reminder &eseconds."))
                game.playSound(Sound.NOTE_PLING)
            }
        }
    }
}
