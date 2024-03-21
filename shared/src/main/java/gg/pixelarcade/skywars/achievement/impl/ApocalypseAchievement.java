package gg.pixelarcade.skywars.achievement.impl;

import gg.pixelarcade.skywars.achievement.Achievement;
import gg.pixelarcade.skywars.game.event.PlayerKillEvent;
import gg.pixelarcade.skywars.game.state.GameType;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

@Getter
public class ApocalypseAchievement implements Achievement {
    private final String id = "apocalypse";
    private final String name = "Apocalypse";
    private final String[] description = new String[] {
            "Kill 50 zombies"
    };

    @Override
    public int getGoal() {
        return 50;
    }

    @EventHandler
    public void onPlayerKill(EntityDeathEvent event) {
        if (!isGameServer()) return;

        if (!(event.getEntity() instanceof Zombie)) return;
        if (event.getEntity().getKiller() == null) return;

        Player killer = event.getEntity().getKiller();

        if (isAchieved(killer.getUniqueId())) return;
        increaseProgress(killer.getUniqueId());
    }
}
