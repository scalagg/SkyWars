package gg.pixelarcade.skywars.kit.impl.ranked


import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class AthleteKit : Kit("Athlete", Material.ARROW, 0, GameType.RANKED, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemStack(Material.IRON_HELMET),
            ItemStack(Material.IRON_CHESTPLATE),
            ItemStack(Material.IRON_LEGGINGS),
            ItemStack(Material.IRON_BOOTS)
        )

    override fun apply(player: Player) {
        player.inventory.addItem(ItemBuilder(Material.DIAMOND_SWORD).build())
        player.inventory.addItem(ItemBuilder(Material.IRON_AXE).build())
        player.inventory.addItem(ItemBuilder(Material.IRON_PICKAXE).build())
        player.inventory.addItem(ItemBuilder(Material.GOLDEN_APPLE, 2).build())
        player.inventory.addItem(ItemBuilder(Material.FISHING_ROD).build())
        player.inventory.addItem(ItemBuilder(Material.WATER_BUCKET).build())
        player.inventory.addItem(
            ItemBuilder(
                Material.POTION,
                1
            ).addCustomPotionEffect(PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 45, 1), false).build()
        )
        player.inventory.addItem(
            ItemBuilder(
                Material.POTION,
                2
            ).addCustomPotionEffect(PotionEffect(PotionEffectType.SPEED, 20 * 3, 3), true).build()
        )
    }
}
