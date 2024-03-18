package gg.pixelarcade.skywars.achievement.impl;

import gg.pixelarcade.skywars.achievement.Achievement;
import gg.pixelarcade.skywars.game.event.PlayerKillEvent;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

@Getter
public class NoWeaponAchievement implements Achievement {
    private final String id = "no-weapon";
    private final String name = "Who needs a weapon?";
    private final String[] description = new String[] {
            "Get 10 kills without using a weapon"
    };

    @Override
    public int getGoal() {
        return 10;
    }

    @EventHandler
    public void onPlayerKill(PlayerKillEvent event) {
        if (event.getKiller() == null) return;

        if (event.getKiller().getItemInHand() != null &&
            event.getKiller().getItemInHand().getType() != Material.AIR) return;

        if (isAchieved(event.getKiller().getUniqueId())) return;
        increaseProgress(event.getKiller().getUniqueId());
    }
}
