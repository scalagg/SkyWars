package gg.pixelarcade.skywars.achievement.impl;

import gg.pixelarcade.skywars.achievement.Achievement;
import gg.pixelarcade.skywars.game.Game;
import gg.pixelarcade.skywars.game.event.GameEndEvent;
import gg.pixelarcade.skywars.game.event.PlayerDamagePlayerEvent;
import gg.pixelarcade.skywars.game.state.GameType;
import lombok.Getter;
import org.bukkit.event.EventHandler;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter
public class TacticalTacticianAchievement implements Achievement {
    private final Set<UUID> hasHit = new HashSet<>();

    private final String id = "tactical-tactician";
    private final String name = "Tactical Tactician";
    private final String[] description = new String[] {
            "Win a game of Ranked SkyWars without actively attacking anyone,",
            "relying solely on strategic positioning, trapping, and",
            "your tactical skills to outsmart your opponents."
    };

    @Override
    public int getGoal() {
        return 1;
    }

    @EventHandler
    public void onHit(PlayerDamagePlayerEvent event) {
        if (event.getDamager() == null) return;
        hasHit.add(event.getDamager().getUniqueId());
    }

    @EventHandler
    public void onGameEnd(GameEndEvent event) {
        Game game = event.getGame();

        for (UUID winner : game.getAlivePlayers()) {
            if (isAchieved(winner)) continue;
            if (hasHit.contains(winner)) continue;
            increaseProgress(winner);
        }
    }
}
