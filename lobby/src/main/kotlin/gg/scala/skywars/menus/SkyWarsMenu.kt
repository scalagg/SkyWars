package gg.scala.skywars.menus

import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.menus.kits.KitListMenu
import gg.scala.skywars.menus.perks.PerkListMenu
import gg.scala.skywars.menus.utils.CoinsButton

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

    init {
        placeholder = true
    }

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
        buttons[getSlot(2, 2)] = PerkCategoryButton(Material.NETHER_STAR, GameType.NORMAL)
        buttons[getSlot(4, 2)] = PerkCategoryButton(Material.NETHER_STAR, GameType.INSANE)
        buttons[getSlot(6, 2)] = PerkCategoryButton(Material.NETHER_STAR, GameType.RANKED)

        //-- Footer
        buttons[getSlot(4, 4)] = CoinsButton()

        return buttons
    }

    
    private class PerkCategoryButton(private val material: Material, private val gameType: GameType): Button() {


        //-- Create Buttons with given values
        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(material)
                .name(ChatColor.DARK_PURPLE.toString() + gameType.displayName + "Perks")
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
