package gg.pixelarcade.skywars.kit.impl.insane


import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.kit.Kit
import gg.pixelarcade.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class RusherKit : Kit("Rusher", Material.IRON_AXE, 20000, GameType.INSANE, KitRarity.RARE) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemBuilder(Material.IRON_BOOTS)
                .enchant(Enchantment.PROTECTION_FALL, 2).build(),
            ItemBuilder(Material.IRON_CHESTPLATE).build()
        )

    override fun apply(player: Player) {
        player.inventory.addItem(
            ItemBuilder(Material.DIAMOND_AXE).enchant(Enchantment.DAMAGE_ALL, 1).enchant(
                Enchantment.DIG_SPEED, 1
            ).build()
        )
        player.inventory.addItem(ItemStack(Material.WOOD, 16))
        player.inventory.addItem(
            ItemBuilder(
                Material.POTION,
                2
            ).addCustomPotionEffect(PotionEffect(PotionEffectType.SPEED, 20, 2), true).build()
        )
        player.inventory.addItem(
            ItemBuilder(
                Material.POTION,
                2
            ).addCustomPotionEffect(PotionEffect(PotionEffectType.JUMP, 20, 2), true).build()
        )
    }
}
