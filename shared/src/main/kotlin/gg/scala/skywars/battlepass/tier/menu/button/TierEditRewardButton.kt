package gg.scala.skywars.battlepass.tier.menu.button

import gg.scala.skywars.battlepass.tier.Tier
import gg.scala.skywars.battlepass.tier.TierReward
import gg.scala.skywars.battlepass.tier.menu.TierEditMenu
import gg.scala.skywars.battlepass.tier.menu.reward.TierRewardMenu
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.menus.ConfirmMenu
import net.evilblock.cubed.util.bukkit.Constants
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.text.TextSplitter
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class TierEditRewardButton(private val tier: Tier, private val free: Boolean) : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val lore = arrayListOf<String>()

        lore.add("")

        if (this.free) {
            lore.addAll(
                TextSplitter.split(
                    text = "A free reward can be collected by any user.",
                    linePrefix = "${ChatColor.GRAY}"
                )
            )
        } else {
            lore.addAll(
                TextSplitter.split(
                    text = "A premium reward can only be collected by users that have purchased the Premium BattlePass.",
                    linePrefix = "${ChatColor.GRAY}"
                )
            )
        }

        lore.add("")

        val reward: TierReward? = if (this.free) {
            this.tier.freeReward
        } else {
            this.tier.premiumReward
        }

        val paidContext = if (this.free) "free" else "premium"

        if (reward == null) {
            lore.addAll(
                TextSplitter.split(
                    text = "There is currently no $paidContext reward applied to this tier.",
                    linePrefix = "${ChatColor.RED}"
                )
            )
        } else {
            lore.addAll(
                TextSplitter.split(
                    text = "There is currently a $paidContext reward applied to this tier.",
                    linePrefix = "${ChatColor.GREEN}"
                )
            )
            lore.add("")

            for (line in reward.getTextLines()) {
                lore.add("${ChatColor.GRAY}${Constants.DOT_SYMBOL} $line")
            }

        }

        lore.add("")

        if (reward == null) {
            lore.add("${ChatColor.GREEN}${ChatColor.BOLD}LEFT-CLICK ${ChatColor.GREEN}to add reward")
        } else {
            lore.add("${ChatColor.GREEN}${ChatColor.BOLD}LEFT-CLICK ${ChatColor.GREEN}to edit reward")
            lore.add("${ChatColor.RED}${ChatColor.BOLD}RIGHT-CLICK ${ChatColor.RED}to unset reward")
        }


        return ItemBuilder.of(if (this.free) Material.COAL else Material.EMERALD)
            .name("${ChatColor.AQUA}${ChatColor.BOLD}Edit ${if (this.free) "Free" else "Premium"} Reward")
            .setLore(lore)
            .build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        if (clickType.isLeftClick) {

            if (this.free) {

                if (this.tier.freeReward == null) {
                    this.tier.freeReward = TierReward(this.tier)
                }

                TierRewardMenu(tier.freeReward!!).openMenu(player)
            } else {

                if (this.tier.premiumReward == null) {
                    this.tier.premiumReward = TierReward(this.tier)
                }

                TierRewardMenu(tier.premiumReward!!).openMenu(player)
            }
            return
        }

        if ((this.free && this.tier.freeReward == null) || !this.free && this.tier.premiumReward == null) {
            return
        }
        ConfirmMenu("Are you sure?", callback = { value ->

            if (value) {

                if (this@TierEditRewardButton.free) {
                    this@TierEditRewardButton.tier.freeReward = null
                } else {
                    this@TierEditRewardButton.tier.premiumReward = null
                }

            }

            TierEditMenu(this@TierEditRewardButton.tier).openMenu(player)
        }).openMenu(player)
    }


}