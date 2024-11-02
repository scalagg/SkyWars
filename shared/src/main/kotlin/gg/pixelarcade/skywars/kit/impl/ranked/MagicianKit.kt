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

class MagicianKit : Kit("Magician", Material.STICK, 0, GameType.RANKED, KitRarity.COMMON) {
    override val armor: Array<ItemStack?>
        get() = arrayOf(
            ItemStack(Material.IRON_HELMET),
            ItemBuilder(Material.DIAMOND_CHESTPLATE)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                .build(),
            ItemStack(Material.IRON_LEGGINGS),
            ItemStack(Material.IRON_BOOTS)
        )

    override fun apply(player: Player) {
        player.inventory.addItem(
            ItemBuilder(Material.MILK_BUCKET, 2)
                .build()
        )
        player.inventory.addItem(
            ItemBuilder(Material.POTION, 3)
                .addCustomPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, 25 * 10, 1), true)
                .build()
        )
        player.inventory.addItem(
            ItemBuilder(Material.POTION, 3)
                .addCustomPotionEffect(PotionEffect(PotionEffectType.HARM, 25 * 10, 1), true)
                .build()
        )
    }
}
