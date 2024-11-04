package gg.pixelarcade.skywars.items

import gg.pixelarcade.skywars.item.Item
import gg.pixelarcade.skywars.kit.menu.KitSelectorMenu
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.item.impl
 * @date 25/09/2023 / 22:49
 */
class LoadoutItem(private val player: Player) : Item(player) {
    override fun getItem(player: Player): ItemStack {
        return ItemBuilder(Material.EMERALD)
            .name(Color.translate("&aKit Selector &7(Right Click)"))
            .build()
    }

    override fun click(action: Action) {
        KitSelectorMenu().openMenu(player)
    }
}
