package gg.scala.skywars.kit.impl.normal


import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.kit.Kit
import gg.scala.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class RusherKit : Kit("Rusher", Material.IRON_AXE, 20000, GameType.NORMAL, KitRarity.RARE) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemBuilder(Material.IRON_BOOTS)
                .enchant(Enchantment.PROTECTION_FALL, 2).build()
        )

    override fun apply(player: Player) {
        player.inventory.addItem(ItemStack(Material.IRON_AXE))
        player.inventory.addItem(ItemStack(Material.WOOD, 16))
        player.inventory.addItem(
            ItemBuilder(
                Material.POTION,
                2
            ).addCustomPotionEffect(PotionEffect(PotionEffectType.SPEED, 15, 1), true).build()
        )
    }
}
