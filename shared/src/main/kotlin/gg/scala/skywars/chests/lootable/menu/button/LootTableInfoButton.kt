package gg.scala.skywars.chests.lootable.menu.button

import gg.scala.skywars.chests.lootable.menu.LootTableInfoMenu
import gg.scala.skywars.chests.lootable.model.LootTable
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack


/**
 * @date 11/11/2024
 * @author Moose1301
 */
class LootTableInfoButton(private val table: LootTable) : Button() {
    override fun getButtonItem(player: Player): ItemStack {
        val builder = ItemBuilder(Material.PAPER)
            .name(table.displayName)
            .addToLore(
                ChatColor.GOLD.toString() + "ID" + ChatColor.WHITE + " - " + table.id,
                ChatColor.GOLD.toString() + "Items" + ChatColor.WHITE + " - " + table.items.size,
                ChatColor.GOLD.toString() + "Min" + ChatColor.WHITE + " - " + table.minItems,
                ChatColor.GOLD.toString() + "Max" + ChatColor.WHITE + " - " + table.maxItems
            )
        return builder.build()
    }


    fun click(player: Player, slot: Int, clickType: ClickType?, hotbarButton: Int) {
        LootTableInfoMenu(table).openMenu(player)
    }
}