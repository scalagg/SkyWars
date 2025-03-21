package gg.scala.skywars.kit.impl.ranked


import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.kit.Kit
import gg.scala.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class MedicKit : Kit("Medic", Material.POTION, 0, GameType.RANKED, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemStack(Material.IRON_HELMET),
            ItemStack(Material.IRON_CHESTPLATE),
            ItemStack(Material.IRON_LEGGINGS),
            ItemStack(Material.IRON_BOOTS)
        )

    override fun apply(player: Player) {
        player.inventory.addItem(
            ItemBuilder(
                Material.POTION,
                2
            ).addCustomPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 25 * 12, 1), true).build()
        )
        player.inventory.addItem(
            ItemBuilder(
                Material.POTION,
                2
            ).addCustomPotionEffect(PotionEffect(PotionEffectType.HEAL, 1, 1), true).build()
        )
        player.inventory.addItem(ItemBuilder(Material.IRON_SWORD).build())
        player.inventory.addItem(ItemBuilder(Material.IRON_AXE).build())
        player.inventory.addItem(ItemBuilder(Material.IRON_PICKAXE).build())
        player.inventory.addItem(ItemBuilder(Material.GOLDEN_APPLE, 2).build())
    }
}
