package gg.pixelarcade.skywars.listeners

import gg.pixelarcade.skywars.SkyWarsLobby
import gg.pixelarcade.skywars.SkyWarsLobbyPlugin
import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.leaderboard.Leaderboard
import net.citizensnpcs.api.event.NPCRightClickEvent
import net.evilblock.cubed.util.bukkit.Tasks.delayed
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.function.Consumer

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 02:50
 */
class LobbyListener(private val plugin: SkyWarsLobby) : Listener {
    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerJoin(e: PlayerJoinEvent) {
        plugin.giveItems(e.player)
        val profile = SkyWarsShared.instance.getProfile(e.player.uniqueId)

        SkyWarsShared.instance.leaderboardHandler.getLeaderboards()
            .forEach(Consumer { leaderboard: Leaderboard -> leaderboard.update(profile) })

        delayed(10L, Runnable { SkyWarsLobby.lobbyInstance.personalStatsNPCHandler.spawnHologram(e.player) })
    }

    @EventHandler
    fun onPlayerLeave(event: PlayerQuitEvent) {
        SkyWarsLobby.lobbyInstance.personalStatsNPCHandler.destroyHologram(event.player)
    }


    @EventHandler
    fun onDamage(event: EntityDamageEvent) {
        val player: Player

        if (event.entity is Player) {
            player = event.entity as Player
        } else {
            return
        }

        if (event.cause == EntityDamageEvent.DamageCause.VOID) {
            event.isCancelled = true
            player.teleport(SkyWarsLobbyPlugin.instance.server.getWorld("world").spawnLocation)
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerInteract(event: NPCRightClickEvent) {
        val player = event.clicker
        val npc = event.npc

        if (SkyWarsLobby.lobbyInstance.queueNPCsHandler.getNpcs().contains(npc)) {
            SkyWarsLobby.lobbyInstance.queueNPCsHandler.getMenu(npc, player)
        } else {
            SkyWarsLobby.lobbyInstance.personalStatsNPCHandler.getMenu(player)
        }
    }

    @EventHandler
    fun onMobSpawn(event: EntitySpawnEvent) {
        if (event.entity is Player) return
        event.isCancelled = true
    }
}