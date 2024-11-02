package gg.pixelarcade.skywars.menus

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.menus.kits.KitListMenu
import gg.pixelarcade.skywars.menus.perks.PerkListMenu
import gg.pixelarcade.skywars.menus.utils.CoinsButton
import gg.pixelarcade.skywars.menus.utils.ExitButton
import gg.pixelarcade.skywars.menus.utils.FillerButton
import lombok.RequiredArgsConstructor
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

/**
 * @author puugz, disunion
 * @since Mar 01, 2024
 */
class SkyWarsMenu : Menu() {
    override fun getTitle(player: Player): String {
        return "SkyWars Menu"
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: MutableMap<Int, Button> = HashMap()

        //-- Kit Buttons
        buttons[getSlot(2, 1)] = KitCategoryButton(Material.WOOD_SWORD, GameType.NORMAL)
        buttons[getSlot(4, 1)] = KitCategoryButton(Material.IRON_SWORD, GameType.INSANE)
        buttons[getSlot(6, 1)] = KitCategoryButton(Material.DIAMOND_SWORD, GameType.RANKED)

        //-- Perk Buttons
        buttons[getSlot(2, 2)] = PerkCategoryButton(Material.NETHER_STAR)
        buttons[getSlot(4, 2)] = PerkCategoryButton(Material.NETHER_STAR)
        buttons[getSlot(6, 2)] = PerkCategoryButton(Material.NETHER_STAR)

        //-- Footer
        buttons[getSlot(3, 4)] = ExitButton()
        buttons[getSlot(5, 4)] = CoinsButton()

        //-- Filler
        for (i in 10..34) {
            buttons.computeIfAbsent(i) { k: Int? -> FillerButton() }
        }

        return buttons
    }

    @RequiredArgsConstructor
    private class PerkCategoryButton(private val material: Material): Button() {


        //-- Create Buttons with given values
        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(material)
                .name(ChatColor.DARK_PURPLE.toString() + "Perks")
                .addToLore(
                    "",
                    ChatColor.GREEN.toString() + "Click to view."
                )
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            PerkListMenu().openMenu(player)
        }
    }

    @RequiredArgsConstructor
    private class KitCategoryButton(private val material: Material, private val gameType: GameType) : Button() {

        //-- Create Buttons with given values
        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(material)
                .name(ChatColor.GOLD.toString() + gameType.displayName + " Kits")
                .addToLore(
                    "",
                    ChatColor.GREEN.toString() + "Click to view."
                )
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            KitListMenu(gameType).openMenu(player)
        }
    }
}
