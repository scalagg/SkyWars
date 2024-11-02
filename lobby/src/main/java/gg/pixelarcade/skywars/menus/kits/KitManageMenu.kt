package gg.pixelarcade.skywars.menus.kits

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.profile.SkyWarsProfile
import lombok.RequiredArgsConstructor
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.menu.menus.ConfirmMenu
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

/**
 * @author puugz
 * @since Mar 01, 2024
 */
class KitManageMenu //        this.setBordered(true);
    (private val kit: Kit) : Menu() {
    override fun getTitle(player: Player): String {
        return "Managing " + kit.name + " Kit"
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: MutableMap<Int, Button> = HashMap()

        val profile = SkyWarsShared.instance
            .getProfile(player.uniqueId)

        buttons[getSlot(3, 1)] = KitInfoButton()
        buttons[getSlot(4, 1)] = BuyKitButton(profile)
        buttons[getSlot(5, 1)] = EditKitButton()

        return buttons
    }

    private fun purchaseKit(player: Player, profile: SkyWarsProfile, kit: Kit) {
        profile.ownedKits.add(kit.name)
        profile.coins = profile.coins - kit.cost
        profile.save()

        player.sendMessage(ChatColor.GREEN.toString() + "You have purchased the " + kit.name + " Kit!")
    }

    private inner class KitInfoButton : Button() {
        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(kit.displayMaterial)
                .name(ChatColor.GOLD.toString() + kit.name + " Kit")
                .build()
        }
    }

    @RequiredArgsConstructor
    private inner class BuyKitButton(private val profile: SkyWarsProfile) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            val hasKit = false /*profile.getOwnedKits().contains(kit.getName())*/
            val canAffordKit = profile.coins >= kit.cost

            val lore: MutableList<String> = ArrayList()

            lore.add("")
            if (hasKit) {
                lore.add("&aYou already own this kit.")
            } else {
                lore.add("&fPrice: &e" + kit.cost)
                lore.add("")
                lore.add(
                    if (canAffordKit)
                        "&aClick to purchase for " + kit.cost + " coins."
                    else
                        "&cYou cannot afford this kit."
                )
            }

            return ItemBuilder(Material.STAINED_GLASS_PANE)
                .data(if (hasKit) STAINED_GLASS_YELLOW else (if (canAffordKit) STAINED_GLASS_GREEN else STAINED_GLASS_RED))
                .name("&a" + (if (hasKit) "Owned" else "Purchase") + " " + kit.name + " Kit")
                .setLore(lore)
                .build()
        }


        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            val hasKit = false /*profile.getOwnedKits().contains(kit.getName())*/
            val canAffordKit = profile!!.coins >= kit.cost

            if (hasKit || !canAffordKit) return

            val confirmMenu: Menu = ConfirmMenu(
                "Confirm purchase of " + kit.name + " Kit",
                mutableListOf(), true
            ) { response: Boolean ->
                if (response) {
                    purchaseKit(player, profile, kit)
                }
            }
            confirmMenu.openMenu(player)
        }


    }

    private class EditKitButton : Button() {
        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(Material.ANVIL)
                .name("&6Edit kit")
                .addToLore(
                    "",
                    "&aClick to edit kit."
                )
                .build()
        }
    }

    companion object {
        private const val STAINED_GLASS_YELLOW: Short = 4
        private const val STAINED_GLASS_GREEN: Short = 5
        private const val STAINED_GLASS_RED: Short = 14
    }
}
