package gg.pixelarcade.skywars.kit.menu

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.kit.Kit
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.pagination.PaginatedMenu
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import java.util.*

class KitSelectorMenu : PaginatedMenu() {
    init {
        updateAfterClick = true
    }

    override fun getAllPagesButtons(player: Player): Map<Int, Button> {
        val buttons: MutableMap<Int, Button> = HashMap()

        for (value in SkyWarsShared.instance.kitHandler.getKits())
            buttons[buttons.size] = KitButton(value)


        return buttons
    }

    override fun getPrePaginatedTitle(player: Player): String {
        return "Kit Selector"
    }

    private class KitButton(val kit: Kit) : Button() {

        override fun getButtonItem(player: Player): ItemStack {
            val profile = SkyWarsShared.instance.getProfile(player);

            // TODO:
            val item = ItemBuilder(kit.displayMaterial)
                .name(CC.RESET + kit.name)

            if (profile.selectedKit.equals(kit.name, ignoreCase = true))
                item.enchant(Enchantment.DURABILITY, 10)


            return item.build()
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            val profile = SkyWarsShared.instance.getProfile(player);

            profile.selectedKit = kit.name.lowercase()
            profile.save()
        }
    }

}
