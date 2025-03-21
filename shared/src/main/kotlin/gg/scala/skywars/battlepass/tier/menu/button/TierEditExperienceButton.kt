package gg.scala.skywars.battlepass.tier.menu.button

import gg.scala.skywars.battlepass.tier.Tier
import gg.scala.skywars.battlepass.tier.menu.TierEditMenu
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.text.TextSplitter
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.bukkit.prompt.NumberPrompt

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.conversations.ConversationContext
import org.bukkit.conversations.Prompt
import org.bukkit.conversations.StringPrompt
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class TierEditExperienceButton(private val tier: Tier) : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val lore = arrayListOf<String>()

        lore.add("${ChatColor.GRAY}Current Requirement: ${ChatColor.GREEN}${tier.requiredExperience} XP")
        lore.add("")
        lore.addAll(TextSplitter.split(text = "The amount of experience required to unlock this tier's challenges.", linePrefix = "${ChatColor.GRAY}"))
        lore.add("")
        lore.add("${ChatColor.GREEN}${ChatColor.BOLD}LEFT-CLICK ${ChatColor.GREEN}to edit required experience")

        return ItemBuilder.of(Material.EXP_BOTTLE)
            .name("${ChatColor.AQUA}${ChatColor.BOLD}Edit Required Experience")
            .setLore(lore)
            .build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView){
        player.closeInventory()
        NumberPrompt().acceptInput{
                this@TierEditExperienceButton.tier.requiredExperience = it.toInt()
                TierEditMenu(this@TierEditExperienceButton.tier).openMenu(player)
            }
            .start(player)
    }
}