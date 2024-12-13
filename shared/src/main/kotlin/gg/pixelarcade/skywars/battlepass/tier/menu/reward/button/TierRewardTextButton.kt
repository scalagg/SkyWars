package gg.pixelarcade.skywars.battlepass.tier.menu.reward.button

import gg.pixelarcade.skywars.battlepass.tier.TierHandler
import gg.pixelarcade.skywars.battlepass.tier.TierReward
import gg.pixelarcade.skywars.battlepass.tier.menu.reward.TierRewardMenu
import gg.pixelarcade.skywars.battlepass.tier.sync.TierDataSync
import net.evilblock.cubed.menu.menus.TextEditorMenu
import net.evilblock.cubed.util.bukkit.Tasks.delayed
import net.evilblock.cubed.util.bukkit.prompt.EzPrompt
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.stream.Collectors


class TierRewardTextButton(private val reward: TierReward) : TextEditorMenu(reward.getTextLines()) {
    override fun getPrePaginatedTitle(player: Player): String {
        return "Edit Reward Text";
    }

    override fun onClose(player: Player) {
        delayed(1L) {
            TierRewardMenu(reward).openMenu(player)
        }
    }

    override fun onSave(player: Player, list: List<String>) {
        reward.text = lines.map { ChatColor.translateAlternateColorCodes('&', it) }.toMutableList()
        TierHandler.saveData()
    }

    override fun getPromptBuilder(player: Player, index: Int): EzPrompt.Builder {
        return EzPrompt.Builder()
            .promptText("${ChatColor.GREEN}Please enter the new text.")
            .charLimit(100)
    }



}