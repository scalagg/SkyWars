package gg.scala.skywars.listeners

import gg.scala.skywars.Limbo
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class QueueListener : Listener {
    @EventHandler
    fun onPlayerChat(event: AsyncPlayerChatEvent) {
        if (!event.player.isOp) return

        if (!event.message.equals("deployskywarsserver", ignoreCase = true)) return

        gg.scala.skywars.Limbo.lobbyInstance.lobbyHandler.deployGame(true)
        event.player.sendMessage("Deploying SkyWars Server...")
        event.isCancelled = true
    }
}
