package gg.pixelarcade.skywars.battlepass.menu.editor.button

import gg.pixelarcade.skywars.battlepass.menu.editor.BattlePassEditorMenu
import gg.pixelarcade.skywars.battlepass.weekly.WeeklyChallengeHandler
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

class ResetWeeklyChallengesButton : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val lore = arrayListOf<String>()

        lore.add("")
        lore.addAll(TextSplitter.split(text = "Re-generate the weekly challenges. All currently tracked user progress for the week will be lost.", linePrefix = "${ChatColor.GRAY}"))
        lore.add("")
        lore.add("${ChatColor.GREEN}${ChatColor.BOLD}LEFT-CLICK ${ChatColor.GREEN}to reset weekly changes")

        return ItemBuilder.of(Material.COMMAND)
            .name("${ChatColor.YELLOW}${ChatColor.BOLD}Reset Weekly Challenges")
            .setLore(lore)
            .build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        ConfirmMenu("Reset Weekly Challenges?", callback = { confirmed ->
            if (confirmed) {
                WeeklyChallengeHandler.refresh()

                player.sendMessage("${ChatColor.GREEN}You have reset the Battle Pass's weekly challenges!")
            }

            BattlePassEditorMenu().openMenu(player)
        }).openMenu(player)
    }
}