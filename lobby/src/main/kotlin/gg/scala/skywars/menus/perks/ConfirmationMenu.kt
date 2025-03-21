package gg.scala.skywars.menus.perks

import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.perk.Perk

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class ConfirmationMenu(private val perk: Perk) : Menu() {
    override fun getTitle(player: Player): String {
        return "Are you sure?"
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: MutableMap<Int, Button> = HashMap()

        buttons[getSlot(2, 1)] = ConfirmButton(perk)
        buttons[getSlot(6, 1)] = CancelButton()

        return buttons
    }

    
    class ConfirmButton(private val perk: Perk) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(Material.STAINED_CLAY, 1)
                .data(13.toShort())
                .name(ChatColor.GREEN.toString() + "Confirm")
                .addToLore(
                    ChatColor.GRAY.toString() + "Purchase " + perk.name + " for",
                    ChatColor.GRAY.toString() + perk.cost.toString() + " coins."
                )
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            if (SkyWarsShared.instance.getProfile(player.uniqueId).coins >= perk.cost) {
                val profile = SkyWarsShared.instance.getProfile(player.uniqueId)

                val originalCoins = profile.coins
                profile.ownedPerks.add(perk.name)
                profile.coins = originalCoins - perk.cost

                PurchasePerkMenu().openMenu(player)
                player.sendMessage(ChatColor.GREEN.toString() + "Purchased!")
            }
        }
    }

    class CancelButton : Button() {
        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(Material.STAINED_CLAY, 1)
                .data(14.toShort())
                .name(ChatColor.RED.toString() + "Cancel")
                .addToLore(ChatColor.GRAY.toString() + "Return to previous menu.")
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            PurchasePerkMenu().openMenu(player)
        }
    }
}
