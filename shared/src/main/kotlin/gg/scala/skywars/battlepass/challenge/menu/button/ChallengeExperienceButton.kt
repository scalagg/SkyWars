package gg.scala.skywars.battlepass.challenge.menu.button

import gg.scala.skywars.battlepass.challenge.Challenge
import gg.scala.skywars.battlepass.challenge.menu.ChallengeEditMenu
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.text.TextSplitter
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.bukkit.prompt.NumberPrompt
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class ChallengeExperienceButton(private val daily: Boolean, private val weekly: Boolean, private val challenge: Challenge) : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val lore = arrayListOf<String>()

        lore.add("${ChatColor.GRAY}Current Reward: ${ChatColor.GREEN}+${challenge.rewardXp} XP")
        lore.add("")
        lore.addAll(TextSplitter.split(text = "The experience that is rewarded to the player when they complete this challenge.", linePrefix = "${ChatColor.GRAY}"))
        lore.add("")
        lore.add("${ChatColor.GREEN}${ChatColor.BOLD}LEFT-CLICK ${ChatColor.GREEN}to edit reward experience")

        return ItemBuilder.of(Material.EXP_BOTTLE)
            .name("${ChatColor.AQUA}${ChatColor.BOLD}Edit Reward Experience")
            .setLore(lore)
            .build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        player.closeInventory()
        NumberPrompt().acceptInput { number ->
            assert(number.toInt() > 0) { "Number must be above 0" }

            this.challenge.rewardXp = number.toInt()

            ChallengeEditMenu(this.daily, this.weekly, this.challenge).openMenu(player)
        }.start(player)
    }

}