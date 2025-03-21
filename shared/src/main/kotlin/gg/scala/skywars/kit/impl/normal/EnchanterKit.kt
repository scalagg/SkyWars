package gg.scala.skywars.kit.impl.normal

import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.kit.Kit
import gg.scala.skywars.kit.KitRarity
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class EnchanterKit : Kit("Enchanter", Material.ENCHANTMENT_TABLE, 15000, GameType.NORMAL, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOfNulls(0)

    override fun apply(player: Player) {
        player.inventory.addItem(ItemStack(Material.ENCHANTMENT_TABLE))
        player.inventory.addItem(ItemStack(Material.BOOKSHELF, 8))
        player.inventory.addItem(ItemStack(Material.EXP_BOTTLE, 64))
    }
}
