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
import org.bukkit.inventory.ItemStack

class KitPerksMenu : Menu() {
    private val perkHandler = SkyWarsShared.instance.perkHandler


    override fun getTitle(player: Player): String {
        return "Kit Perks"
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: MutableMap<Int, Button> = HashMap()

        perkHandler.getPerks()
            .stream().filter(Perk::isKitPerk)
            .forEach { perk: Perk ->
                buttons[buttons.size + 10] = PerkButton(perk)
            }

        //-- Footer
        buttons[getSlot(3, 4)] = BackButton(PerkListMenu())
        buttons[getSlot(4, 4)] = ExitButton()
        buttons[getSlot(5, 4)] = CoinsButton()

        return buttons
    }

    private class PerkButton(private val perk: Perk) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            return ItemBuilder(perk.displayMaterial)
                .name(ChatColor.GOLD.toString() + perk.name)
                .addToLore(
                    "",
                    ChatColor.GRAY.toString() + perk.description
                )
                .build()
        }
    }
}
