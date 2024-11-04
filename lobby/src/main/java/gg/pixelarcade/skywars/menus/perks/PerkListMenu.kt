package gg.pixelarcade.skywars.menus.perks

import com.cryptomorin.xseries.XMaterial
import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.menus.SkyWarsMenu
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

class PerkListMenu : Menu() {
    private val perkHandler = SkyWarsShared.instance.perkHandler


    override fun getTitle(player: Player): String {
        return "Perks"
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: MutableMap<Int, Button> = HashMap()

        //-- Personal Perks
        buttons[getSlot(1, 1)] = PersonalPerkDescription()
        for (i in 1..6) {
            buttons[getSlot(i + 1, 1)] = PersonalPerksButton(i)
        }

        //-- Global Perks
        buttons[getSlot(1, 3)] = GlobalPerkDescription()
        buttons[getSlot(2, 3)] = GlobalPerksButton(perkHandler.getPerkByName("Bulldozer Perk")!!)
        buttons[getSlot(3, 3)] = GlobalPerksButton(perkHandler.getPerkByName("Juggernaut Perk")!!)
        buttons[getSlot(6, 3)] = BuyPerkMenu()
        buttons[getSlot(7, 3)] = KitPerkMenu()

        //-- Footer
        buttons[getSlot(3, 4)] = BackButton(SkyWarsMenu())
        buttons[getSlot(4, 4)] = ExitButton()
        buttons[getSlot(5, 4)] = CoinsButton()


        return buttons
    }

    
    private class PersonalPerksButton(private val perkNum: Int) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            // variables

            val playerPerks = SkyWarsShared.instance.getProfile(player.uniqueId).selectedPerks

            if (playerPerks.size >= perkNum) {
                val perk = SkyWarsShared.instance.perkHandler.getPerkByName(playerPerks[perkNum - 1])
                return ItemBuilder(perk!!.displayMaterial)
                    .name(ChatColor.GREEN.toString() + "Perk Slot #" + perkNum + ChatColor.YELLOW + " - " + perk.name)
                    .addToLore(
                        ChatColor.GRAY.toString() + perk.description,
                        "",
                        ChatColor.YELLOW.toString() + "Left-Click to replace"
                    )
                    .build()
            } else {
                return ItemBuilder(XMaterial.OAK_BUTTON)
                    .name(ChatColor.GREEN.toString() + "Perk Slot #" + perkNum + ChatColor.RED + " - None selected")
                    .addToLore(
                        "",
                        ChatColor.YELLOW.toString() + "Left-Click to replace"
                    )
                    .build()
            }
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            if (clickType.isLeftClick) {
                OwnedPerksMenu().openMenu(player)
            }
        }
    }

    private class GlobalPerksButton(private val perk: Perk) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(perk.displayMaterial)
                .name(ChatColor.GOLD.toString() + perk.name)
                .addToLore(ChatColor.GRAY.toString() + perk.description)
                .build()
        }
    }

    class BuyPerkMenu : Button() {
        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(XMaterial.DIAMOND)
                .name(ChatColor.GREEN.toString() + "Buy Perks")
                .addToLore(
                    ChatColor.GRAY.toString() + "Click here to Purchase new Perks!"
                )
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            PurchasePerkMenu().openMenu(player)
        }
    }

    class KitPerkMenu : Button() {
        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(XMaterial.CLOCK)
                .name(ChatColor.GREEN.toString() + "Kit Perks")
                .addToLore(
                    ChatColor.GRAY.toString() + "These Perks are are active for every",
                    ChatColor.GRAY.toString() + "player, depending on the kit they have",
                    ChatColor.GRAY.toString() + "Selected",
                    "",
                    ChatColor.YELLOW.toString() + "Left-Click to view"
                )
                .build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            if (clickType.isLeftClick) {
                KitPerksMenu().openMenu(player)
            }
        }
    }

    class PersonalPerkDescription : Button() {
        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(XMaterial.REDSTONE_TORCH)
                .name(ChatColor.GREEN.toString() + "Perk Slots")
                .addToLore(
                    ChatColor.GRAY.toString() + "You can have up to " + ChatColor.GREEN + "6 " + ChatColor.GRAY + "Perks active",
                    ChatColor.GRAY.toString() + "at any time.",
                    ChatColor.GRAY.toString() + "The slots to " + ChatColor.RED + "the right " + ChatColor.GRAY + "of this icon",
                    ChatColor.GRAY.toString() + "indicate your currently active perks.",
                    ChatColor.YELLOW.toString() + "Right-click " + ChatColor.GRAY + "on a perk to disable it,",
                    ChatColor.GRAY.toString() + "and make room to activate another",
                    ChatColor.GRAY.toString() + "perk.",

                    ChatColor.YELLOW.toString() + "Left-click " + ChatColor.GRAY + "on a perk to replace it",
                    ChatColor.GRAY.toString() + "directly with another perk.",
                    ChatColor.GRAY.toString() + "Click on any of your " + ChatColor.GREEN + "purchased",
                    ChatColor.GREEN.toString() + "perks " + ChatColor.GRAY + "to enable them if you have an",
                    ChatColor.GRAY.toString() + "available slot"
                )
                .build()
        }
    }

    class GlobalPerkDescription : Button() {
        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(XMaterial.REDSTONE_TORCH)
                .name(ChatColor.GREEN.toString() + "Global Perks")
                .addToLore(
                    ChatColor.GRAY.toString() + "Global perks are active for every",
                    ChatColor.GRAY.toString() + "player, on top of the 6 customizable",
                    ChatColor.GRAY.toString() + "perk slots."
                )
                .build()
        }
    }
}
