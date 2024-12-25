package net.mccade.skywars.listeners

import gg.scala.lemon.util.QuickAccess
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.Tasks
import net.mccade.skywars.Limbo
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent


class LobbyListener(private val plugin: Limbo) : Listener {

    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerJoin(event: PlayerJoinEvent) {
        // Variables
        val player: Player = event.player
        plugin.lobbyHandler.addPlayer(player)

        // We want to give the player time to see their join message
        Tasks.delayed(2L,  {
            // Broadcast that the player has joined, as well as their position in the queue
            Bukkit.broadcastMessage(
                (Color.translate(
                    QuickAccess.coloredName(player.uniqueId),
                ) +
                        org.bukkit.ChatColor.YELLOW) + " has joined (" +
                        org.bukkit.ChatColor.AQUA + plugin.lobbyHandler.lobby.players.size +
                        org.bukkit.ChatColor.YELLOW + "/" +
                        org.bukkit.ChatColor.AQUA + plugin.lobbyHandler.lobby.gameTeamType.maxPlayers +
                        org.bukkit.ChatColor.YELLOW + ")!"
            )
        })
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerLeave(event: PlayerQuitEvent) {
        //Variables
        val player: Player = event.player

        plugin.lobbyHandler.removePlayer(player)

        // Broadcast that the player has left
        Bukkit.broadcastMessage((QuickAccess.coloredName(player.uniqueId) +  ChatColor.YELLOW) + " has quit!")
    }

    @EventHandler
    fun onDamage(event: EntityDamageEvent) {
        if (event.entity !is Player) return

        if (event.cause == EntityDamageEvent.DamageCause.VOID) {
            event.entity.teleport(Bukkit.getServer().getWorld("world").spawnLocation)
            event.isCancelled = true
        }
    }
}
