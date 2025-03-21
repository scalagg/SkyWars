package gg.scala.skywars.kit.impl.normal


import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.kit.Kit
import gg.scala.skywars.kit.KitRarity
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class MedicKit : Kit("Medic", Material.POTION, 20000, GameType.NORMAL, KitRarity.RARE) {
    override val armor: Array<ItemStack?>
        get() = arrayOfNulls(0)

    override fun apply(player: Player) {
        player.inventory.addItem(
            ItemBuilder(
                Material.POTION,
                3
            ).addCustomPotionEffect(PotionEffect(PotionEffectType.HEAL, 1, 1), true).build()
        )
        player.inventory.addItem(
            ItemBuilder(
                Material.POTION,
                2
            ).addCustomPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 15, 1), true).build()
        )
    }
}
