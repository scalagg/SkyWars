package gg.pixelarcade.skywars.quests;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.game.Game;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import gg.pixelarcade.skywars.quests.progression.ProgressionTracker;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 15:27
 */

@Getter
@RequiredArgsConstructor
public abstract class Quest {

    // This is used to track the quest across different systems and for saving/loading quest data.
    private final String id;
    // This can be used to sort quests in a list or to determine the order in which quests should be completed.
    private final int priority;
    // This is used to determine what command should be run when the quest is completed.
    private final QuestRewardType rewardType;
    // This is used to determine if the quest has been completed.
    private final QuestCompletionStrategy strategy;

    public abstract String getDisplayName();
    public abstract ItemStack getDisplayIcon();
    public abstract List<String> getDescription();

    /**
     * Complete the quest for the player
     *
     * @param player the player to complete the quest for
     */
    public void completeQuest(Player player, Game game) {
        SkyWarsProfile profile = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId());
        if (!strategy.isQuestCompleted(player, game))
            return;

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), rewardType.getCommand()
                .replace("%player%", player.getName()));

        //Send a message to the player to let them know they have completed the quest
        player.sendMessage("You have completed the quest " + this.getDisplayName() + " and have been rewarded!");
    }

}
