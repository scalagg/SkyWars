package gg.pixelarcade.skywars.quests.strategy;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.game.Game;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import gg.pixelarcade.skywars.quests.QuestCompletionStrategy;
import gg.pixelarcade.skywars.quests.progression.ProgressionTracker;
import org.bukkit.entity.Player;

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 16:51
 */

public class TwentyKillsStrategy implements QuestCompletionStrategy {

    @Override
    public boolean isQuestCompleted(Player player, Game game) {
        SkyWarsProfile profile = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId());
        ProgressionTracker tracker = profile.getTracker("twenty_kills");
        return tracker != null && tracker.isGoalReached();
    }
}
