package gg.pixelarcade.skywars.battlepass.menu.editor.button

import gg.pixelarcade.skywars.battlepass.challenge.menu.ChallengeEditorMenu
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.text.TextSplitter
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class EditChallengesButton : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val lore = arrayListOf<String>()

        lore.add("")
        lore.addAll(TextSplitter.split(text = "Challenges reward players experience to level up their Battle Pass tiers.", linePrefix = "${ChatColor.GRAY}"))
        lore.add("")
        lore.add("${ChatColor.GREEN}${ChatColor.BOLD}LEFT-CLICK ${ChatColor.GREEN}to edit premium challenges")
        lore.add("${ChatColor.AQUA}${ChatColor.BOLD}SHIFT RIGHT-CLICK ${ChatColor.AQUA}to edit weekly challenges")
        lore.add("${ChatColor.AQUA}${ChatColor.BOLD}RIGHT-CLICK ${ChatColor.AQUA}to edit daily challenges")

        return ItemBuilder.of(Material.SADDLE)
            .name("${ChatColor.YELLOW}${ChatColor.BOLD}Edit Challenges")
            .setLore(lore)
            .build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        if (clickType.isLeftClick) {
            ChallengeEditorMenu(false, false).openMenu(player)
        } else if(clickType.isShiftClick && clickType.isRightClick) {
            ChallengeEditorMenu(false, true).openMenu(player)
        } else if (clickType.isRightClick) {
            ChallengeEditorMenu(true, false).openMenu(player)
        }
    }



}