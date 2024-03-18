package gg.pixelarcade.skywars.achievement.impl;

import gg.pixelarcade.skywars.achievement.Achievement;
import gg.pixelarcade.skywars.game.event.PlayerKillEvent;
import gg.pixelarcade.skywars.game.state.GameType;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

@Getter
public class FirstBloodAchievement implements Achievement {
    private boolean isFirst = true;

    private final String id = "first-blood";
    private final String name = "First Blood";
    private final String[] description = new String[] {
            "Get the first kill in a game of Ranked SkyWars."
    };

    @Override
    public int getGoal() {
        return 10;
    }

    @EventHandler
    public void onPlayerKill(PlayerKillEvent event) {
        if (!isFirst) return;
        if (event.getGame().getGameType() != GameType.RANKED) return;
        if (event.getKiller() == null) return;

        isFirst = false;
        if (isAchieved(event.getKiller().getUniqueId())) return;
        increaseProgress(event.getKiller().getUniqueId());
    }
}
