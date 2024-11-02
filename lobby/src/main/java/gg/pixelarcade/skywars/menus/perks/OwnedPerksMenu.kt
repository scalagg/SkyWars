package gg.pixelarcade.skywars.menus.perks

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.menus.utils.BackButton
import gg.pixelarcade.skywars.menus.utils.CoinsButton
import gg.pixelarcade.skywars.menus.utils.ExitButton
import gg.pixelarcade.skywars.perk.Perk
import lombok.RequiredArgsConstructor
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class OwnedPerksMenu : Menu() {
    private val perkHandler = SkyWarsShared.instance.perkHandler


    override fun getTitle(player: Player): String {
        return "Owned Perks"
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: MutableMap<Int, Button> = HashMap()

        perkHandler.getPerks()
            .stream().filter { perk: Perk -> !perk.isGlobalPerk && !perk.isKitPerk }
            .forEach { perk: Perk ->
                val ownedPerks = SkyWarsShared.instance.getProfile(player.uniqueId).ownedPerks
                if (!ownedPerks.contains(perk.name))
                    return@forEach

                buttons[buttons.size + 10] = PerkButton(perk)
            }

        //-- Footer
        buttons[getSlot(3, 4)] = BackButton(PerkListMenu())
        buttons[getSlot(4, 4)] = ExitButton()
        buttons[getSlot(5, 4)] = CoinsButton()

        return buttons
    }

    @RequiredArgsConstructor
    private class PerkButton(private val perk: Perk) : Button() {

        override fun getButtonItem(player: Player): ItemStack {


            //-- If the player doesn't own any perks, or if they already have it selected, return nothing


            return if (SkyWarsShared.instance.getProfile(player.uniqueId).selectedPerks.contains(perk.name)) {
                ItemBuilder(perk.displayMaterial)
                    .name(ChatColor.GREEN.toString() + perk.name)
                    .addToLore(
                        ChatColor.GRAY.toString() + perk.description,
                        "",
                        ChatColor.GREEN.toString() + "This perk is already active."
                    )
                    .build()
            } else {
                ItemBuilder(perk.displayMaterial)
                    .name(ChatColor.RED.toString() + perk.name)
                    .addToLore(
                        ChatColor.GRAY.toString() + perk.description,
                        "",
                        ChatColor.YELLOW.toString() + "Click to Select!"
                    )
                    .build()
            }
        }


        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            val selectedPerks = SkyWarsShared.instance.getProfile(player.uniqueId).selectedPerks

            if (!selectedPerks.contains(perk.name)) {
                SkyWarsShared.instance.getProfile(player.uniqueId).addSelectedPerk(perk)
            } else {
                player.sendMessage(ChatColor.RED.toString() + "This perk is already active!")
            }

            PerkListMenu().openMenu(player)
        }
    }
}
