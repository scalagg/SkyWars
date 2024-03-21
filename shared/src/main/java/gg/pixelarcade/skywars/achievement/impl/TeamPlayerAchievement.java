package gg.pixelarcade.skywars.achievement.impl;

import gg.pixelarcade.skywars.achievement.Achievement;
import gg.pixelarcade.skywars.game.event.GameEndEvent;
import lombok.Getter;
import org.bukkit.event.EventHandler;


@Getter
public class TeamPlayerAchievement implements Achievement {
    private final String id = "team-player";
    private final String name = "Team Player";
    private final String[] description = new String[] {
            "Win a game of Team Ranked SkyWars",
            "with a full team of players"
    };

    @Override
    public int getGoal() {
        return 1;
    }

    @EventHandler
    public void onGameEnd(GameEndEvent event) {
        //TODO: Implement when Team SkyWars is added
    }
}
