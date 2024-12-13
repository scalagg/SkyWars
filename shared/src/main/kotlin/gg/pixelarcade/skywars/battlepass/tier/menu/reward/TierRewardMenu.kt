package gg.pixelarcade.skywars.battlepass.tier.menu.reward

import gg.pixelarcade.skywars.battlepass.tier.TierReward
import gg.pixelarcade.skywars.battlepass.tier.menu.TierEditMenu
import gg.pixelarcade.skywars.battlepass.tier.menu.reward.button.TierRewardCommandsButton
import gg.pixelarcade.skywars.battlepass.tier.menu.reward.button.TierRewardTextButton
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.menu.buttons.BackButton
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.text.TextSplitter
import net.evilblock.cubed.util.text.TextSplitter.split
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack


class TierRewardMenu(private val reward: TierReward) : Menu() {
    init {
        updateAfterClick = true
    }

    override fun getTitle(player: Player): String {
        return "Edit Reward - Tier ${this.reward.tier.number} (${if (this.reward.isFreeReward()) "Free" else "Premium"})"
    }

    override fun getButtons(player: Player): MutableMap<Int,Button> {
        return mutableMapOf(
            1 to EditTextButton(this.reward),
            4 to BackButton(callback = { TierEditMenu(this.reward.tier).openMenu(it) }),
            3 to EditCommandsButton(this.reward)
        )
    }

    private class EditTextButton(private val reward: TierReward) : Button() {
        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder.of(Material.SIGN)
                .name(ChatColor.AQUA.toString() + ChatColor.BOLD + "Edit Rewards Text")
                .setLore(getLore(player))
                .build()
        }

        
        fun getLore(player: Player): List<String> {
            val description: MutableList<String> = ArrayList()
            description.add("")
            description.addAll(
                split(
                    32, 32,
                    "The text that appears in the `rewards` section of the button representing this challenge.",
                    ChatColor.GRAY.toString(), " "
                )
            )
            description.add("")
            description.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "LEFT-CLICK " + ChatColor.GREEN + "to edit rewards text")

            return description
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            if (clickType.isLeftClick) {
                TierRewardTextButton(reward).openMenu(player)
            }
        }
    }

    private class EditCommandsButton(private val reward: TierReward) : Button() {
        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder.of( Material.COMMAND)
                .name("${ChatColor.AQUA}${ChatColor.BOLD}Edit Rewards Commands")
                .setLore(getLore(player))
                .build()
        }

        fun getLore(player: Player): List<String> {
            val description = arrayListOf<String>()

            description.add("")
            description.addAll(TextSplitter.split(text = "The commands that are executed when a player claims this reward.", linePrefix = "${ChatColor.GRAY}"))
            description.add("")
            description.add("${ChatColor.GREEN}${ChatColor.BOLD}LEFT-CLICK ${ChatColor.GREEN}to edit commands")

            return description
        }



        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            if (clickType.isLeftClick) {
                TierRewardCommandsButton(reward).openMenu(player)
            }
        }
    }
}