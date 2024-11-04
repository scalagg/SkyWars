package gg.pixelarcade.skywars.menus.perks

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.menus.utils.BackButton
import gg.pixelarcade.skywars.menus.utils.CoinsButton
import gg.pixelarcade.skywars.menus.utils.ExitButton
import gg.pixelarcade.skywars.perk.Perk

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

/**
 * @Author: Disunion
 * @Date: 03/09/2024
 * @Description: GUI that allows a player to purchase a perk
 * @Notes: once we add a certain amount of perks, this needs to become a Paginated Menu
 */
class PurchasePerkMenu  //-- Constructor
    : Menu() {
    //-- Instance Variables
    private val perkHandler = SkyWarsShared.instance.perkHandler

    //-- Return title for gui
    override fun getTitle(player: Player): String {
        return "Perk Store"
    }

    //-- Returns a map of "buttons" or display items with functionality
    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: MutableMap<Int, Button> = HashMap()

        perkHandler.getPerks()
            .stream().filter { perk: Perk -> !perk.isGlobalPerk && !perk.isKitPerk }
            .forEach { perk: Perk -> buttons[buttons.size + 10] = PerksButton(perk) }

        //-- Footer
        buttons[getSlot(3, 4)] = BackButton(PerkListMenu())
        buttons[getSlot(4, 4)] = ExitButton()
        buttons[getSlot(5, 4)] = CoinsButton()

        return buttons
    }

    class PerksButton(private val perk: Perk) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            return if (alreadyBought(player)) {
                ItemBuilder(perk.displayMaterial)
                    .name(ChatColor.GREEN.toString() + perk.name)
                    .addToLore(
                        ChatColor.GRAY.toString() + perk.description,
                        "",
                        ChatColor.GRAY.toString() + "Rarity: " + (perk.rarity.color.toString() + perk.rarity.displayName),
                        "",
                        ChatColor.GREEN.toString() + "Already purchased!"
                    )
                    .build()
            } else {
                ItemBuilder(perk.displayMaterial)
                    .name(ChatColor.RED.toString() + perk.name)
                    .addToLore(
                        ChatColor.GRAY.toString() + perk.description,
                        "",
                        ChatColor.GRAY.toString() + "Rarity: " + (perk.rarity.color.toString() + perk.rarity.displayName),
                        "",
                        ChatColor.GRAY.toString() + "Cost: " + ChatColor.GOLD + perk.cost
                    )
                    .build()
            }
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            if (alreadyBought(player)) {
                player.closeInventory()
                player.sendMessage(ChatColor.RED.toString() + "You have already purchased that perk!")
            } else if (SkyWarsShared.instance.getProfile(player.uniqueId).coins >= perk.cost) {
                ConfirmationMenu(perk).openMenu(player)
            } else {
                player.closeInventory()
                player.sendMessage(ChatColor.RED.toString() + "Sorry, but you can't afford that!")
            }
        }

        private fun alreadyBought(player: Player): Boolean {
            val ownedPerks = SkyWarsShared.instance.getProfile(player.uniqueId).ownedPerks
            return ownedPerks.contains(perk.name)
        }
    }
}

