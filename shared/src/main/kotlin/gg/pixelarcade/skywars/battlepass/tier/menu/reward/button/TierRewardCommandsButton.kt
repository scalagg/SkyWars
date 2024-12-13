package gg.pixelarcade.skywars.battlepass.tier.menu.reward.button

import gg.pixelarcade.skywars.battlepass.tier.TierHandler
import gg.pixelarcade.skywars.battlepass.tier.TierReward
import gg.pixelarcade.skywars.battlepass.tier.menu.reward.TierRewardMenu
import gg.pixelarcade.skywars.util.PlayerUtil
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.menu.menus.TextEditorMenu

import net.evilblock.cubed.util.text.TextSplitter
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.bukkit.Tasks.delayed
import net.evilblock.cubed.util.bukkit.prompt.EzPrompt
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.conversations.ConversationContext
import org.bukkit.conversations.Prompt
import org.bukkit.conversations.StringPrompt
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class TierRewardCommandsButton(private val reward: TierReward) : TextEditorMenu(reward.getCommands()) {
    override fun getPrePaginatedTitle(player: Player): String {
        return "Edit Reward Commands";
    }

    override fun onClose(player: Player) {
        delayed(1L) {
            TierRewardMenu(reward).openMenu(player)
        }
    }

    override fun onSave(player: Player, list: List<String>) {
        reward.commands = lines.toMutableList()
        TierHandler.saveData()
    }

    override fun getPromptBuilder(player: Player, index: Int): EzPrompt.Builder {
        return EzPrompt.Builder()
            .promptText("${ChatColor.GREEN}Please enter the new command.")
            .charLimit(100)
    }



}