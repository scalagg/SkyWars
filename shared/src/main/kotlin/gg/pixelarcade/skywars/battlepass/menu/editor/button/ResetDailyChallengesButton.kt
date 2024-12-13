package gg.pixelarcade.skywars.battlepass.menu.editor.button

import gg.pixelarcade.skywars.battlepass.daily.DailyChallengeHandler
import gg.pixelarcade.skywars.battlepass.menu.editor.BattlePassEditorMenu
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.menus.ConfirmMenu
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.text.TextSplitter
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class ResetDailyChallengesButton : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val lore = arrayListOf<String>()

        lore.add("")
        lore.addAll(TextSplitter.split(text = "Re-generate the daily challenges. All currently tracked user progress for the day will be lost.", linePrefix = "${ChatColor.GRAY}"))
        lore.add("")
        lore.add("${ChatColor.GREEN}${ChatColor.BOLD}LEFT-CLICK ${ChatColor.GREEN}to reset daily changes")

        return ItemBuilder.of(Material.COMMAND)
            .name("${ChatColor.YELLOW}${ChatColor.BOLD}Reset Daily Challenges")
            .setLore(lore)
            .build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        ConfirmMenu("Reset Daily Challenges?", callback = { confirmed ->
            if (confirmed) {
                DailyChallengeHandler.refresh()

                player.sendMessage("${ChatColor.GREEN}You have reset the BattlePass's daily challenges!")
            }

            BattlePassEditorMenu().openMenu(player)
        }).openMenu(player)
    }
}