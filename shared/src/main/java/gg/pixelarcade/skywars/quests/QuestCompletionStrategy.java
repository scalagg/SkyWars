package gg.pixelarcade.skywars.quests;

import gg.pixelarcade.skywars.game.Game;
import org.bukkit.entity.Player;

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 16:00
 */

public interface QuestCompletionStrategy {

    boolean isQuestCompleted(Player player, Game game);

}
