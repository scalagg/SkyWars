package gg.pixelarcade.skywars.kit.impl.ranked


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

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.kit.impl
 * @date 26/09/2023 / 18:32
 */
class ScoutKit : Kit("Scout", Material.POTION, 0, GameType.RANKED, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemStack(Material.IRON_HELMET),
            ItemStack(Material.IRON_CHESTPLATE),
            ItemStack(Material.IRON_LEGGINGS),
            ItemStack(Material.IRON_BOOTS)
        )

    override fun apply(player: Player) {
        player.inventory.addItem(
            ItemBuilder(Material.DIAMOND_AXE)
                .enchant(Enchantment.DIG_SPEED, 1)
                .enchant(Enchantment.DAMAGE_ALL, 1)
                .build()
        )

        player.inventory.addItem(
            ItemBuilder(Material.DIAMOND_PICKAXE)
                .enchant(Enchantment.DIG_SPEED, 1)
                .build()
        )

        player.inventory.addItem(
            ItemBuilder(Material.POTION, 3)
                .addCustomPotionEffect(PotionEffect(PotionEffectType.SPEED, 25 * 20, 1), true)
                .build()
        )
    }
}
