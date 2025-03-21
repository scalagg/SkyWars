package gg.scala.skywars.items

import gg.scala.skywars.item.Item
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack

/**
 * @author alfie
 * @project Skywars / gg.scala.game.item.impl
 * @date 25/09/2023 / 22:49
 */
class SettingsItem(private val player: Player) : Item(player) {
    override fun getItem(player: Player): ItemStack {
        return ItemBuilder(Material.REDSTONE_COMPARATOR)
            .name(Color.translate("&aSettings &7(Right Click)"))
            .build()
    }

    override fun click(action: Action) {
        player.performCommand("settings")
    }
}
