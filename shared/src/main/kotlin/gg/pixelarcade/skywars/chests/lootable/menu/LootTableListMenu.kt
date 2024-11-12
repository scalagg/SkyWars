package gg.pixelarcade.skywars.chests.lootable.menu

import gg.pixelarcade.skywars.chests.lootable.LootTableDataSync
import gg.pixelarcade.skywars.chests.lootable.menu.button.LootTableInfoButton
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.pagination.PaginatedMenu
import org.bukkit.entity.Player


/**
 * @date 11/11/2024
 * @author Moose1301
 */
class LootTableListMenu : PaginatedMenu() {
    override fun getAllPagesButtons(player: Player): Map<Int, Button> {
        val buttons: MutableMap<Int, Button> = HashMap()
        for (table in LootTableDataSync.cached().tables) {
            buttons[buttons.size] = LootTableInfoButton(table)
        }
        return buttons
    }

    override fun getPrePaginatedTitle(player: Player): String {
        return "Loot Table List";
    }
}