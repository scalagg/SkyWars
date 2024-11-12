package gg.pixelarcade.skywars.chests.lootable.menu.button

import gg.pixelarcade.skywars.chests.lootable.model.LootTable
import gg.pixelarcade.skywars.chests.lootable.model.LootTableItem
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack


/**
 * @date 11/11/2024
 * @author Moose1301
 */
class LootTableItemButton(val table: LootTable, val item: LootTableItem) : Button() {
    override fun getButtonItem(player: Player): ItemStack {
        return ItemBuilder(item.stack.clone())
            .addToLore(
                "",
                "${CC.GREEN} Chance: ${item.chance}%",
                "",
                "${CC.GRAY} Left click to increase the chance",
                "${CC.GRAY} Right click to decrease the chance",
                "${CC.GRAY} Shift Right click to ${CC.RED} DELETE ${CC.GRAY} this item"
            )
            .build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        if(clickType == ClickType.SHIFT_RIGHT) {
            table.items.remove(item);
        } else if(clickType == ClickType.LEFT) {
            item.chance++;
        } else if(clickType == ClickType.RIGHT) {
            item.chance--
        }
    }
}