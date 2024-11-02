package gg.pixelarcade.skywars.kit.impl.insane


import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class PyromancerKit : Kit("Pyromancer", Material.FLINT_AND_STEEL, 30000, GameType.INSANE, KitRarity.LEGENDARY) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemBuilder(Material.DIAMOND_CHESTPLATE).build(),
            ItemBuilder(Material.LEATHER_LEGGINGS)
                .color(Color.ORANGE).build(),
        )

    override fun apply(player: Player) {
        player.inventory.addItem(ItemStack(Material.FLINT_AND_STEEL))
        player.inventory.addItem(ItemStack(Material.LAVA_BUCKET, 3))
        player.inventory.addItem(
            ItemBuilder(
                Material.POTION,
                2
            ).addCustomPotionEffect(PotionEffect(PotionEffectType.FIRE_RESISTANCE, 90, 1), true).build()
        )
    }
}
