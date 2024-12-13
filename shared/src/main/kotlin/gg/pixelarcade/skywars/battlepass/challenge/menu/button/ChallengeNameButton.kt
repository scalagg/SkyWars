package gg.pixelarcade.skywars.battlepass.challenge.menu.button

import gg.pixelarcade.skywars.battlepass.challenge.Challenge
import gg.pixelarcade.skywars.battlepass.challenge.menu.ChallengeEditMenu
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.text.TextSplitter
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.bukkit.prompt.InputPrompt
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class ChallengeNameButton(private val daily: Boolean,private val weekly: Boolean, private val challenge: Challenge) : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val lore = arrayListOf<String>()

        lore.add("")
        lore.addAll(TextSplitter.split(text = "The name is how you want the reward to appear in chat and menu text.", linePrefix = "${ChatColor.GRAY}"))
        lore.add("")
        lore.add("${ChatColor.GREEN}${ChatColor.BOLD}LEFT-CLICK ${ChatColor.GREEN}to edit name")

        return ItemBuilder.of(Material.NAME_TAG)
            .name("${ChatColor.AQUA}${ChatColor.BOLD}Edit Name")
            .setLore(lore)
            .build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        player.closeInventory()
        InputPrompt().acceptInput{_, input ->
            this.challenge.name = ChatColor.translateAlternateColorCodes('&', input)
            player.sendMessage("${ChatColor.GREEN}Successfully updated the name of the challenge.")
            ChallengeEditMenu(this.daily, this.weekly, this.challenge).openMenu(player)
        }
            .withText("${ChatColor.GREEN}Please input a new name for the challenge.")
            .start(player)
    }

}