package gg.pixelarcade.skywars.quests

import gg.pixelarcade.skywars.SkyWarsShared
import lombok.Getter
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 15:27
 */
@Getter
abstract class Quest(// This is used to track the quest across different systems and for saving/loading quest data.
    val id: String, // This is used to determine what command should be run when the quest is completed.
    val rewardType: QuestRewardType
) : Listener {
    abstract fun getDisplayName(): String
    abstract fun getDisplayIcon(): ItemStack
    abstract fun getDescription(): List<String>

    init {
        Bukkit.getPluginManager().registerEvents(this, SkyWarsShared.instance.plugin)
    }

    /**
     * Complete the quest for the player
     *
     * @param player the player to complete the quest for
     */
    fun completeQuest(player: Player) {
        val profile = SkyWarsShared.instance.getProfile(player)

        Bukkit.dispatchCommand(
            Bukkit.getConsoleSender(), rewardType.command
                .replace("%player%", player.name)
        )

        //Send a message to the player to let them know they have completed the quest
        player.sendMessage("You have completed the quest " + this.getDisplayName() + " and have been rewarded!")
    }
}
