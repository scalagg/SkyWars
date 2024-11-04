package gg.pixelarcade.skywars.menus.kits

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.menus.SkyWarsMenu
import gg.pixelarcade.skywars.menus.utils.BackButton
import gg.pixelarcade.skywars.menus.utils.CoinsButton
import gg.pixelarcade.skywars.menus.utils.ExitButton

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

/**
 * @author puugz
 * @since Mar 01, 2024
 */
class KitListMenu(private val gameType: GameType) : Menu() {
    private val kitHandler = SkyWarsShared.instance.kitHandler

    override fun getTitle(player: Player): String {
        return gameType.displayName + " Kits"
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: MutableMap<Int, Button> = HashMap()

        kitHandler.getKits()
            .stream().filter { kit: Kit -> kit.gameType == gameType }
            .forEach { kit: Kit -> buttons[buttons.size] = KitButton(kit) }

        //-- Footer
        buttons[getSlot(3, 4)] = BackButton(SkyWarsMenu())
        buttons[getSlot(4, 4)] = ExitButton()
        buttons[getSlot(5, 4)] = CoinsButton()

        return buttons
    }

    
    private class KitButton( private val kit: Kit) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(kit.displayMaterial)
                .name(ChatColor.GOLD.toString() + kit.name + " Kit")
                .addToLore(
                    "",
                    ChatColor.GREEN.toString() + "Click to manage kit."
                )
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            KitManageMenu(kit).openMenu(player)
        }
    }
}
