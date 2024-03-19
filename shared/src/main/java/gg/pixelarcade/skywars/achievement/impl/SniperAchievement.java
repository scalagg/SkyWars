package gg.pixelarcade.skywars.achievement.impl;

import gg.pixelarcade.skywars.achievement.Achievement;
import gg.pixelarcade.skywars.game.event.PlayerKillEvent;
import gg.pixelarcade.skywars.game.state.GameType;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

@Getter
public class SniperAchievement implements Achievement {
    private final String id = "sniper";
    private final String name = "Sniper";
    private final String[] description = new String[] {
            "Get a kill from at least 20 blocks away"
    };

    @Override
    public int getGoal() {
        return 10;
    }

    @EventHandler
    public void onPlayerKill(PlayerKillEvent event) {
        if (event.getKiller() == null) return;
        Player killer = event.getKiller();
        Player victim = event.getPlayer();

        if (killer.getLocation().distanceSquared(victim.getLocation()) < 400) return;

        if (isAchieved(event.getKiller().getUniqueId())) return;
        increaseProgress(event.getKiller().getUniqueId());
    }
}
