package gg.scala.skywars.menus.utils

import com.cryptomorin.xseries.XMaterial

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack


class BackButton(private val previousMenu: Menu) : Button() {

    override fun getButtonItem(player: Player): ItemStack {
        return ItemBuilder(XMaterial.ARROW)
            .name(ChatColor.GOLD.toString() + "Go Back!")
            .addToLore(
                "",
                ChatColor.GRAY.toString() + "Click to go back a page!"
            )
            .build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        previousMenu.openMenu(player)
    }
}
