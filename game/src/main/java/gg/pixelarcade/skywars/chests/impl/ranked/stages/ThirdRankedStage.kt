package gg.pixelarcade.skywars.chests.impl.ranked.stages

import gg.pixelarcade.skywars.chests.ChestItem
import gg.pixelarcade.skywars.chests.ChestStage
import gg.pixelarcade.skywars.chests.type.RefillType
import io.netty.util.internal.ThreadLocalRandom
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

class ThirdRankedStage : ChestStage() {
    override val refillType: RefillType
        get() {
            return RefillType.THIRD
        }

    override val spawnLoot: List<ChestItem>
        get() {
            return listOf(
                ChestItem(
                    ItemBuilder(
                        Material.ENDER_PEARL,
                        ThreadLocalRandom.current().nextInt(1, 6)
                    ).build(), 100.0
                )
            )
        }

    override val middleLoot: List<ChestItem>
        get() {
            return listOf(
                ChestItem(
                    ItemBuilder(Material.DIAMOND_HELMET, 3)
                        .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build(), 25.0
                ),
                ChestItem(
                    ItemBuilder(Material.DIAMOND_CHESTPLATE, 3)
                        .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build(), 25.0
                ),
                ChestItem(
                    ItemBuilder(Material.DIAMOND_LEGGINGS, 3)
                        .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build(), 25.0
                ),
                ChestItem(
                    ItemBuilder(Material.DIAMOND_BOOTS, 3)
                        .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build(), 25.0
                ),
                ChestItem(
                    ItemBuilder(
                        Material.ENDER_PEARL,
                        ThreadLocalRandom.current().nextInt(1, 6)
                    ).build(), 100.0
                ),
                ChestItem(
                    ItemBuilder(Material.POTION, 1)
                        .addCustomPotionEffect(PotionEffect(PotionEffectType.HEAL, 0, 1), true)
                        .build(),
                    100.0
                ),
                ChestItem(
                    ItemBuilder(Material.DIAMOND_SWORD, 1)
                        .enchant(Enchantment.FIRE_ASPECT, 2)
                        .enchant(Enchantment.DAMAGE_ALL, 1).build(), 100.0
                )
            )
        }
}
