package gg.pixelarcade.skywars.quests.listener;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import gg.pixelarcade.skywars.quests.Quest;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Map;

/**
 * @author alfie
 * @project SkyWars
 * @date 20/03/2024 / 16:39
 */

@RequiredArgsConstructor
public class DailyQuestListener implements Listener {

    private final SkyWarsShared plugin;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        SkyWarsProfile profile = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId());

        for (Map.Entry<Long, String> entry : profile.getDailyQuests().entrySet()) {
            long time = entry.getKey();
            Quest quest = SkyWarsShared.getInstance().getQuestHandler().getQuest(entry.getValue());

            if (time < System.currentTimeMillis()) {
                profile.getDailyQuests().remove(time);
                profile.getDailyQuests().put(System.currentTimeMillis() + 86400000,
                        plugin.getQuestHandler().getRandomQuest().getId());

                quest.completeQuest(player);
            }
        }


    }

}
