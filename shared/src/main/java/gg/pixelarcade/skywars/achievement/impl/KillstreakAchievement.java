package gg.pixelarcade.skywars.achievement.impl;

import gg.pixelarcade.skywars.achievement.Achievement;
import gg.pixelarcade.skywars.game.Game;
import gg.pixelarcade.skywars.game.event.GameEndEvent;
import gg.pixelarcade.skywars.game.event.PlayerDamagePlayerEvent;
import gg.pixelarcade.skywars.game.event.PlayerKillEvent;
import lombok.Getter;
import org.bukkit.event.EventHandler;

import java.util.*;


@Getter
public class KillstreakAchievement implements Achievement {
    private final Map<UUID, Integer> kills = new HashMap<>();

    private final String id = "killstreak";
    private final String name = "Killstreak";
    private final String[] description = new String[] {
            "Get 10 kills in a single game of Ranked SkyWars"
    };

    @Override
    public int getGoal() {
        return 10;
    }

    @EventHandler
    public void onKill(PlayerKillEvent event) {
        if (event.getKiller() == null) return;
        UUID killer = event.getKiller().getUniqueId();

        increaseProgress(killer);
    }

    @EventHandler
    public void onGameEnd(GameEndEvent event) {
        kills.forEach((uuid, integer) -> {
            if (isAchieved(uuid)) return;
            resetProgress(uuid);
        });
    }
}
