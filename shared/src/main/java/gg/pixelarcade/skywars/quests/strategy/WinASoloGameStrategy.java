package gg.pixelarcade.skywars.quests.strategy;

import gg.pixelarcade.skywars.game.Game;
import gg.pixelarcade.skywars.game.state.GameTeamType;
import gg.pixelarcade.skywars.quests.QuestCompletionStrategy;
import org.bukkit.entity.Player;

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 16:02
 */

public class WinASoloGameStrategy implements QuestCompletionStrategy {

    @Override
    public boolean isQuestCompleted(Player player, Game game) {
        return game.getGameTeamType() == GameTeamType.SOLO
                && game.getWinner() != null
                && game.getWinner() == player.getUniqueId();
    }
}
