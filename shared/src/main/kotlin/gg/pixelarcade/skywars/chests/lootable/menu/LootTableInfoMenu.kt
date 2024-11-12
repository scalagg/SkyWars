package gg.pixelarcade.skywars.chests.lootable.menu

import gg.pixelarcade.skywars.chests.lootable.menu.button.LootTableItemButton
import gg.pixelarcade.skywars.chests.lootable.model.LootTable
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.menu.buttons.BackButton
import net.evilblock.cubed.util.CC
import org.bukkit.Material
import org.bukkit.entity.Player

/**
 * @date 11/11/2024
 * @author Moose1301
 */
class LootTableInfoMenu(private val table: LootTable) : Menu() {
    init {
        updateAfterClick = true
    }

    override fun getTitle(player: Player): String {
        return "Loot Table Info (" + table.id + ")"
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: MutableMap<Int, Button> = HashMap()
        for (i in 0..7) {
            buttons[i] = Button.placeholder(Material.STAINED_GLASS_PANE, 7.toByte())
        }
        buttons[4] = BackButton(CC.RED + "Back") { click: Player ->
            LootTableListMenu().openMenu(click)
        }

        var offset = 9
        for (item in table.items) {
            buttons[offset++] = LootTableItemButton(table, item)
        }
        return buttons
    }
}