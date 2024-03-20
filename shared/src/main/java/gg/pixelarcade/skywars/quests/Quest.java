package gg.pixelarcade.skywars.quests;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.game.Game;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
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
public abstract class Quest implements Listener {

    // This is used to track the quest across different systems and for saving/loading quest data.
    private final String id;
    // This is used to determine what command should be run when the quest is completed.
    private final QuestRewardType rewardType;

    public abstract String getDisplayName();
    public abstract ItemStack getDisplayIcon();
    public abstract List<String> getDescription();

    public Quest(String id, QuestRewardType rewardType) {
        this.id = id;
        this.rewardType = rewardType;

        SkyWarsShared.getInstance().getPlugin().getServer().getPluginManager().registerEvents(
                this, SkyWarsShared.getInstance().getPlugin());
    }

    /**
     * Complete the quest for the player
     *
     * @param player the player to complete the quest for
     */
    public void completeQuest(Player player) {
        SkyWarsProfile profile = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId());

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), rewardType.getCommand()
                .replace("%player%", player.getName()));

        //Send a message to the player to let them know they have completed the quest
        player.sendMessage("You have completed the quest " + this.getDisplayName() + " and have been rewarded!");
    }

}
