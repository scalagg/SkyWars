package gg.pixelarcade.skywars.listeners

import gg.pixelarcade.skywars.SkyWarsLobby
import gg.pixelarcade.skywars.profile.SkyWarsProfile
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 18:40
 */
class QuestListener : Listener {
    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        val profile: SkyWarsProfile = SkyWarsLobby.lobbyInstance.getProfile(player.uniqueId)

        if(profile.dailyQuests.isEmpty()) return

        val entry: Map.Entry<Long, String> = profile.dailyQuests.entries.iterator().next()
        val time = 24 * 60 * 60 * 1000 // 24 hours in milliseconds

        if (System.currentTimeMillis() - entry.key >= time) profile.findNewDailyQuest()
    }
}
