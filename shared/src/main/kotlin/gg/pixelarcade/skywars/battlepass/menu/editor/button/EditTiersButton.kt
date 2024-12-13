package gg.pixelarcade.skywars.battlepass.menu.editor.button


import gg.pixelarcade.skywars.battlepass.tier.menu.TierEditorMenu
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.text.TextSplitter
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class EditTiersButton : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val lore = arrayListOf<String>()

        lore.add("")
        lore.addAll(TextSplitter.split(text = "Tiers are BattlePass milestones that give rewards as you gain experience through completing challenges.", linePrefix = "${ChatColor.GRAY}"))
        lore.add("")
        lore.add("${ChatColor.GREEN}${ChatColor.BOLD}Left click to edit")

        return ItemBuilder.of(Material.NETHER_STAR)
            .name("${ChatColor.YELLOW}${ChatColor.BOLD}Edit Tiers")
            .setLore(lore)
            .build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        TierEditorMenu().openMenu(player)
    }

}