package gg.pixelarcade.skywars.battlepass.challenge.menu.button

import gg.pixelarcade.skywars.battlepass.challenge.Challenge
import gg.pixelarcade.skywars.battlepass.challenge.ChallengeHandler
import gg.pixelarcade.skywars.battlepass.challenge.menu.ChallengeEditMenu
import gg.pixelarcade.skywars.battlepass.challenge.menu.ChallengeEditorMenu
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.menus.ConfirmMenu
import net.evilblock.cubed.util.text.TextSplitter
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class ChallengeRewardButton(private val daily: Boolean, private val weekly: Boolean, private val challenge: Challenge) : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val lore = arrayListOf<String>()

        lore.add("${ChatColor.GRAY}(ID: ${this.challenge.id})")
        lore.add("")
        lore.addAll(TextSplitter.split(text = this.challenge.getText(), linePrefix = "${ChatColor.GRAY}"))
        lore.add("")
        lore.add("${ChatColor.GRAY}Reward XP: ${ChatColor.GREEN}+${this.challenge.rewardXp}")
        lore.add("")
        lore.add("${ChatColor.GREEN}${ChatColor.BOLD}LEFT-CLICK ${ChatColor.GREEN}to edit challenge")
        lore.add("${ChatColor.RED}${ChatColor.BOLD}RIGHT-CLICK ${ChatColor.RED}to delete challenge")

        return ItemBuilder.of(Material.SADDLE)
            .name(this.challenge.name)
            .setLore(lore)
            .build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {

        if (clickType.isLeftClick) {
            ChallengeEditMenu(this.daily, this.weekly, this.challenge).openMenu(player)
            return
        }

        ConfirmMenu("Are you sure?", callback = { confirmed ->
            if (confirmed) {
                ChallengeHandler.destroy(this@ChallengeRewardButton.challenge)
            }

            ChallengeEditorMenu(this@ChallengeRewardButton.daily, this@ChallengeRewardButton.weekly).openMenu(player)
        }).openMenu(player)
    }

}