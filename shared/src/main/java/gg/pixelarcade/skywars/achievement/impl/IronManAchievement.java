package gg.pixelarcade.skywars.achievement.impl;

import gg.pixelarcade.skywars.achievement.Achievement;
import gg.pixelarcade.skywars.game.Game;
import gg.pixelarcade.skywars.game.event.GameEndEvent;
import gg.pixelarcade.skywars.game.event.PlayerDamagePlayerEvent;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter
public class IronManAchievement implements Achievement {
    private final Set<UUID> damaged = new HashSet<>();

    private final String id = "iron-man";
    private final String name = "Iron Man";
    private final String[] description = new String[] {
            "Win a game of Ranked SkyWars without",
            "taking any damage"
    };

    @Override
    public int getGoal() {
        return 1;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onHit(EntityDamageEvent event) {
        if (event.isCancelled()) return;
        if (event.getEntity() instanceof Player player) {
            damaged.add(player.getUniqueId());
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;
        if (event.getEntity() instanceof Player player) {
            damaged.add(player.getUniqueId());
        }
    }

    @EventHandler
    public void onGameEnd(GameEndEvent event) {
        Game game = event.getGame();

        for (UUID winner : game.getAlivePlayers()) {
            if (isAchieved(winner)) continue;
            if (damaged.contains(winner)) continue;
            increaseProgress(winner);
        }
    }
}
