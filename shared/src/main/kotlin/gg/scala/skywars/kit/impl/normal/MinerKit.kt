package gg.scala.skywars.kit.impl.normal

import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.kit.Kit
import gg.scala.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class MinerKit : Kit("Miner", Material.DIAMOND_PICKAXE, 15000, GameType.NORMAL, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOfNulls(0)

    override fun apply(player: Player) {
        player.inventory.addItem(
            ItemBuilder(Material.IRON_PICKAXE).enchant(Enchantment.DIG_SPEED, 3).enchant(
                Enchantment.DAMAGE_ALL, 1
            ).enchant(Enchantment.DURABILITY, 3).build()
        )
        player.inventory.addItem(ItemStack(Material.STONE, 16))
    }
}
