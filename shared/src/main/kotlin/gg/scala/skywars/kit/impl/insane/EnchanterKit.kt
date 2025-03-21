package gg.scala.skywars.kit.impl.insane

import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.kit.Kit
import gg.scala.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class EnchanterKit : Kit("Enchanter", Material.ENCHANTMENT_TABLE, 15000, GameType.INSANE, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemBuilder(Material.DIAMOND_HELMET).build()
        )

    override fun apply(player: Player) {
        player.inventory.addItem(ItemStack(Material.ENCHANTMENT_TABLE, 2))
        player.inventory.addItem(ItemStack(Material.BOOKSHELF, 12))
        player.inventory.addItem(ItemStack(Material.EXP_BOTTLE, 64))
        player.inventory.addItem(ItemStack(Material.BOOK, 4))
    }
}
