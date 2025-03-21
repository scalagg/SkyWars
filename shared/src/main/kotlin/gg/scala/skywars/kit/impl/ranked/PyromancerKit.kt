package gg.scala.skywars.kit.impl.ranked


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

class PyromancerKit : Kit("Pyromancer", Material.LAVA_BUCKET, 0, GameType.RANKED, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemStack(
                ItemBuilder(Material.DIAMOND_BOOTS)
                    .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
                    .enchant(Enchantment.PROTECTION_FIRE, 4).build()
            ),
            ItemStack(Material.IRON_CHESTPLATE),
            ItemStack(Material.IRON_LEGGINGS),
            ItemStack(Material.IRON_BOOTS)
        )

    override fun apply(player: Player) {
        player.inventory.addItem(
            ItemBuilder(
                Material.POTION,
                2
            ).addCustomPotionEffect(PotionEffect(PotionEffectType.FIRE_RESISTANCE, 25 * 90, 1), true).build()
        )
        player.inventory.addItem(ItemBuilder(Material.DIAMOND_SWORD).enchant(Enchantment.FIRE_ASPECT, 2).build())
        player.inventory.addItem(ItemBuilder(Material.LAVA_BUCKET, 2).build())
        player.inventory.addItem(ItemBuilder(Material.IRON_AXE).build())
        player.inventory.addItem(ItemBuilder(Material.IRON_PICKAXE).build())
        player.inventory.addItem(ItemBuilder(Material.IRON_HELMET).build())
    }
}
