package gg.pixelarcade.skywars.listeners;

import gg.pixelarcade.skywars.SkyWarsLobby;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Map;

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 18:40
 */

public class QuestListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        SkyWarsProfile profile = SkyWarsLobby.getInstance().getProfileHandler().getProfile(player.getUniqueId());

        Map.Entry<Long, String> entry = profile.getDailyQuests().entrySet().iterator().next();
        int time = 24 * 60 * 60 * 1000; // 24 hours in milliseconds

        if (System.currentTimeMillis() - entry.getKey() >= time)
            profile.findNewDailyQuest();
    }
}
