package gg.pixelarcade.skywars.battlepass.challenge.menu.button

import gg.pixelarcade.skywars.battlepass.challenge.Challenge
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.text.TextSplitter
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class ChallengeWeeklyButton(private val challenge: Challenge) : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val lore = arrayListOf<String>()

        lore.add("")
        lore.addAll(TextSplitter.split(text = "The experience that is rewarded to the player when they complete this challenge.", linePrefix = "${ChatColor.GRAY}"))
        lore.add("")

        if (this.challenge.weekly) {
            lore.add("${ChatColor.GREEN}This challenge is currently")
            lore.add("${ChatColor.GREEN}apart of the weekly challenge set.")
        } else {
            lore.add("${ChatColor.RED}This challenge is currently not")
            lore.add("${ChatColor.RED}apart of the weekly challenge set.")
        }

        lore.add("")
        lore.add("${ChatColor.GREEN}${ChatColor.BOLD}LEFT-CLICK ${ChatColor.GREEN}to toggle weekly")

        return ItemBuilder.of(Material.LEVER)
            .name("${ChatColor.AQUA}${ChatColor.BOLD}Edit Weekly")
            .setLore(lore)
            .build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        this.challenge.weekly = !this.challenge.weekly
    }


}