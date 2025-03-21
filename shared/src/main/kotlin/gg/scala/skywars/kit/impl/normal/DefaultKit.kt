package gg.scala.skywars.kit.impl.normal

import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.kit.Kit
import gg.scala.skywars.kit.KitRarity
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class DefaultKit : Kit("Default", Material.STONE_SWORD, 0, GameType.NORMAL, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOfNulls(0)

    override fun apply(player: Player) {
        player.inventory.addItem(ItemStack(Material.STONE_SWORD))
        player.inventory.addItem(ItemStack(Material.STONE_PICKAXE))
        player.inventory.addItem(ItemStack(Material.STONE_AXE))
        player.inventory.addItem(ItemStack(Material.STONE_SPADE))
    }
}
