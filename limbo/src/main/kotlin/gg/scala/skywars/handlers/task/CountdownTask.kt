package gg.scala.skywars.handlers.task



import net.evilblock.cubed.util.bungee.BungeeUtil.sendToServer
import gg.scala.skywars.Limbo
import gg.scala.skywars.util.LimboState
import gg.scala.skywars.util.StartingState
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.scheduler.BukkitRunnable

class CountdownTask(countdown: Int) : BukkitRunnable() {
    private val lobby = gg.scala.skywars.Limbo.lobbyInstance.lobbyHandler
    private val reminders = intArrayOf(30, 15, 10, 5, 4, 3, 2, 1)

    var stop = false

    var countdown: Int

    init {
        Bukkit.broadcastMessage("Countdown constructed")
        this.countdown = countdown
    }

    override fun run() {
        if (!stop) {
            if (--countdown == 0) {
                Bukkit.broadcastMessage(ChatColor.RED.toString() + "Sending players")
                lobby.lobby.limboState = LimboState.SENDING
                val serverName = gg.scala.skywars.Limbo.lobbyInstance.lobbyHandler.lobby.serverName

                for (player in Bukkit.getOnlinePlayers()) {
                    sendToServer(player, serverName)
                }

                lobby.lobby.limboState = LimboState.STALE
                lobby.lobby.startingState = StartingState.WAITING
                gg.scala.skywars.Limbo.lobbyInstance.lobbyHandler.lobby.serverDeployed = false
                cancel()
                return
            }

            for (reminder in reminders) {
                if (countdown == reminder) {
                    Bukkit.broadcastMessage(
                        (ChatColor.GREEN.toString() + "Game starting in "
                                + ChatColor.RED + reminder + ChatColor.GREEN + " seconds.")
                    )
                }
            }
        }
    }
}
