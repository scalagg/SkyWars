package gg.scala.skywars.kit.impl.insane


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

class MagicianKit : Kit("Magician", Material.POTION, 20000, GameType.INSANE, KitRarity.RARE) {
    override val armor: Array<ItemStack?>
        get() = arrayOfNulls(0)

    override fun apply(player: Player) {
        player.inventory.addItem(
            ItemBuilder(
                Material.POTION,
                3
            ).addCustomPotionEffect(PotionEffect(PotionEffectType.HARM, 1, 2), true).build()
        )
        player.inventory.addItem(
            ItemBuilder(
                Material.POTION,
                2
            ).addCustomPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, 15, 1), true).build()
        )
        player.inventory.addItem(
            ItemBuilder(
                Material.POTION,
                2
            ).addCustomPotionEffect(PotionEffect(PotionEffectType.POISON, 15, 1), true).build()
        )
        player.inventory.addItem(
            ItemBuilder(
                Material.POTION,
                2
            ).addCustomPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, 10, 1), true).build()
        )
        player.inventory.addItem(ItemBuilder(Material.STICK).enchant(Enchantment.KNOCKBACK, 1).build())
    }
}
