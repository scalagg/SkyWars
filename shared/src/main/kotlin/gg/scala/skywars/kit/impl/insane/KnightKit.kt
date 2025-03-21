package gg.scala.skywars.kit.impl.insane

import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.kit.Kit
import gg.scala.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class KnightKit : Kit("Knight", Material.IRON_SWORD, 30000, GameType.INSANE, KitRarity.LEGENDARY) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemBuilder(Material.IRON_HELMET)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .enchant(Enchantment.DURABILITY, 2).build()
        )

    override fun apply(player: Player) {
        player.inventory.addItem(
            ItemBuilder(Material.IRON_SWORD).enchant(Enchantment.DAMAGE_ALL, 2).enchant(Enchantment.DURABILITY, 5)
                .build()
        )
    }
}
