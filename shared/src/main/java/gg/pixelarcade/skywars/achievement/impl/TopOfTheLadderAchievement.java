package gg.pixelarcade.skywars.achievement.impl;

import gg.pixelarcade.skywars.achievement.Achievement;
import gg.pixelarcade.skywars.leveling.enums.LevelEnum;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

@Getter
public class TopOfTheLadderAchievement implements Achievement {
    private final String id = "chicken-dinner";
    private final String name = "Chicken Dinner";
    private final String[] description = new String[] {
            "Win a game of Ranked SkyWars"
    };

    @Override
    public int getGoal() {
        return 1;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (isAchieved(player.getUniqueId())) return;

        SkyWarsProfile profile = getProfile(player.getUniqueId());
        if (profile.getPrestige(profile.getLevel()) != LevelEnum.Celestial) return;

        increaseProgress(player.getUniqueId());
    }
}
