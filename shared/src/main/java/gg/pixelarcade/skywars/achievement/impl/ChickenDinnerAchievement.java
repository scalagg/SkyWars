package gg.pixelarcade.skywars.achievement.impl;

import gg.pixelarcade.skywars.achievement.Achievement;
import gg.pixelarcade.skywars.game.Game;
import gg.pixelarcade.skywars.game.event.GameEndEvent;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import lombok.Getter;
import org.bukkit.event.EventHandler;

import java.util.UUID;


@Getter
public class ChickenDinnerAchievement implements Achievement {
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
    public void onGameEnd(GameEndEvent event) {
        Game game = event.getGame();
        if (game.getGameType() != GameType.RANKED) return;

        for (UUID winner : game.getAlivePlayers()) {
            if (isAchieved(winner)) continue;
            increaseProgress(winner);
        }
    }
}
