package gg.scala.skywars.battlepass.tier.menu.button

import gg.scala.skywars.battlepass.tier.Tier
import gg.scala.skywars.battlepass.tier.TierReward
import gg.scala.skywars.battlepass.tier.menu.TierEditMenu
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.Constants

import net.evilblock.cubed.util.bukkit.ItemBuilder


import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class TierEditButton(private val tier: Tier) : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val lore = arrayListOf<String>()

        lore.add("${ChatColor.GRAY}Required Experience: ${ChatColor.GREEN}${tier.requiredExperience}")
        lore.add("")

        lore.add("${ChatColor.YELLOW}${ChatColor.BOLD}Free Reward")
        renderRewardDetails(lore, tier.freeReward)

        lore.add("")

        lore.add("${ChatColor.YELLOW}${ChatColor.BOLD}Premium Reward")
        renderRewardDetails(lore, tier.premiumReward)

        lore.add("")
        lore.add("${ChatColor.GREEN}${ChatColor.BOLD}LEFT-CLICK ${ChatColor.GREEN}to edit tier")

        return ItemBuilder.of(Material.NETHER_STAR)
            .name("${ChatColor.GOLD}${ChatColor.BOLD}Tier ${tier.number}")
            .setLore(lore)
            .build()
    }


    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView){
        TierEditMenu(this.tier).openMenu(player)
    }

    private fun renderRewardDetails(description: MutableList<String>, reward: TierReward?) {
        if (reward == null) {
            description.add(" ${ChatColor.GRAY}None applied")
        } else {
            for (text in reward.getTextLines()) {
                description.add(" ${ChatColor.GRAY}${Constants.DOT_SYMBOL} $text")
            }
        }
    }
}