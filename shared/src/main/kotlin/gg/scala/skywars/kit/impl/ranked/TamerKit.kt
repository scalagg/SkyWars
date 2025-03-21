package gg.scala.skywars.kit.impl.ranked

import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.kit.Kit
import gg.scala.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class TamerKit : Kit("Tamer", Material.BONE, 0, GameType.RANKED, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemStack(Material.IRON_HELMET),
            ItemStack(Material.IRON_CHESTPLATE),
            ItemStack(Material.IRON_LEGGINGS),
            ItemStack(
                ItemBuilder(Material.DIAMOND_BOOTS)
                    .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build()
            )
        )

    override fun apply(player: Player) {
        player.inventory.addItem(ItemStack(Material.DIAMOND_SWORD))
        player.inventory.addItem(ItemStack(Material.IRON_AXE))
        player.inventory.addItem(ItemStack(Material.IRON_PICKAXE))
        player.inventory.addItem(ItemStack(Material.BONE, 5))
        player.inventory.addItem(ItemStack(Material.MONSTER_EGG, 5, 95.toShort()))
    }
}
