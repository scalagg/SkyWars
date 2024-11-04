package gg.pixelarcade.skywars.quests.listener

import gg.pixelarcade.skywars.SkyWarsShared

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

/**
 * @author alfie
 * @project SkyWars
 * @date 20/03/2024 / 16:39
 */

class DailyQuestListener(val plugin: SkyWarsShared) : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        val profile = plugin.getProfile(player)

        for ((time, value) in profile.dailyQuests) {
            val quest = plugin.questHandler.getQuest(value)

            if (time < System.currentTimeMillis()) {
                profile.dailyQuests.remove(time)
                profile.dailyQuests[System.currentTimeMillis() + 86400000] =
                    plugin.questHandler.randomQuest.id

                quest?.completeQuest(player)
            }
        }
    }
}
