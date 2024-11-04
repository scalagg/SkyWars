package gg.pixelarcade.skywars.chests.impl.casual.stages

import gg.pixelarcade.skywars.SkyWarsGame
import gg.pixelarcade.skywars.chests.ChestItem
import gg.pixelarcade.skywars.chests.ChestStage
import gg.pixelarcade.skywars.chests.type.RefillType
import io.netty.util.internal.ThreadLocalRandom
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.chests.stage.test
 * @date 17/10/2023 / 16:00
 */
class FirstCasualStage : ChestStage() {
    override val refillType: RefillType
        get() = RefillType.FIRST

    override val spawnLoot: List<ChestItem>
        get() = listOf<ChestItem>(
            createChestItem(Material.IRON_HELMET, 1),
            createChestItem(Material.GOLD_CHESTPLATE, 1),
            createChestItem(Material.LEATHER_LEGGINGS, 1),
            createChestItem(Material.CHAINMAIL_BOOTS, 1),
            createChestItem(Material.WATER_BUCKET, 1),
            createChestItem(Material.LAVA_BUCKET, 1),

            createChestItem(Material.DIAMOND_AXE, 1),
            createChestItem(Material.DIAMOND_PICKAXE, 1),
            createChestItem(Material.STONE_PICKAXE, 1),
            createChestItem(Material.STONE_AXE, 1),
            createChestItem(Material.WOOD_PICKAXE, 1),
            createChestItem(Material.WOOD_AXE, 1),
            createChestItem(Material.STONE_PICKAXE, 1),
            createChestItem(Material.IRON_PICKAXE, 1),
            createChestItem(Material.GOLDEN_APPLE, 1),
            createChestItem(Material.GOLDEN_APPLE, 2),

            createChestItem(
                Material.EXP_BOTTLE,
                ThreadLocalRandom.current().nextInt(8, 12)
            ),
            createChestItem(
                Material.SNOW_BALL,
                ThreadLocalRandom.current().nextInt(12, 16)
            ),
            createChestItem(
                Material.EGG,
                ThreadLocalRandom.current().nextInt(8, 16)
            ),
            createChestItem(
                Material.COOKED_BEEF,
                ThreadLocalRandom.current().nextInt(8, 12)
            ),
            createChestItem(
                Material.COOKED_BEEF,
                ThreadLocalRandom.current().nextInt(8, 12)
            ),
            ChestItem(
                ItemBuilder(
                    Material.WOOD,
                    ThreadLocalRandom.current().nextInt(16, 64)
                ).build(), 100.0
            ),
            ChestItem(
                ItemBuilder(
                    Material.STONE,
                    ThreadLocalRandom.current().nextInt(8, 64)
                ).build(), 100.0
            ),

            ChestItem(
                ItemBuilder(Material.STONE_SWORD)
                    .enchant(Enchantment.DAMAGE_ALL, 2).build(), 75.0
            ),
            ChestItem(
                ItemBuilder(Material.IRON_SWORD)
                    .enchant(Enchantment.DAMAGE_ALL, 2).build(), 75.0
            ),
            ChestItem(
                ItemBuilder(Material.BOW)
                    .enchant(Enchantment.ARROW_DAMAGE, 1).build(), 25.0
            ),
            ChestItem(SkyWarsGame.gameInstance.abilityHandler.getAbilityById("magnet")!!.getItem(), 25.0)
        )

    override val middleLoot: List<ChestItem>
        get() = listOf(
            createChestItem(
                Material.SNOW_BALL,
                ThreadLocalRandom.current().nextInt(6, 32)
            ),
            createChestItem(Material.LAVA_BUCKET, 1),
            createChestItem(
                Material.EXP_BOTTLE,
                ThreadLocalRandom.current().nextInt(8, 64)
            ),
            createChestItem(
                Material.GOLDEN_APPLE,
                ThreadLocalRandom.current().nextInt(1, 2)
            ),
            createChestItem(
                Material.WOOD,
                ThreadLocalRandom.current().nextInt(8, 64)
            ),
            createChestItem(
                Material.TNT,
                ThreadLocalRandom.current().nextInt(8, 10)
            ),
            createChestItem(Material.ENDER_PEARL, 1),

            ChestItem(
                ItemBuilder(Material.DIAMOND_HELMET, 1)
                    .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build(), 95.0
            ),
            ChestItem(
                ItemBuilder(Material.IRON_CHESTPLATE, 1)
                    .enchant(Enchantment.PROTECTION_PROJECTILE, 1).build(), 96.0
            ),
            ChestItem(
                ItemBuilder(Material.IRON_LEGGINGS, 1)
                    .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build(), +7.0
            ),
            ChestItem(
                ItemBuilder(Material.DIAMOND_BOOTS, 1)
                    .enchant(Enchantment.PROTECTION_FIRE, 1).build(), 90.0
            ),
            ChestItem(
                ItemBuilder(Material.IRON_SWORD, 1)
                    .enchant(Enchantment.FIRE_ASPECT, 1).build(), 90.0
            ),
            ChestItem(
                ItemBuilder(Material.POTION, 1)
                    .addCustomPotionEffect(PotionEffect(PotionEffectType.SPEED, 600, 0), true)
                    .build(), 100.0
            ),
            ChestItem(
                ItemBuilder(Material.BOW, 1)
                    .enchant(Enchantment.ARROW_DAMAGE, 1).build(), 90.0
            ),
            ChestItem(
                ItemBuilder(Material.DIAMOND_AXE, 1)
                    .enchant(Enchantment.DIG_SPEED, 1).build(), 90.0
            ),
            ChestItem(
                ItemBuilder(Material.DIAMOND_PICKAXE, 1)
                    .enchant(Enchantment.DIG_SPEED, 1).build(), 90.0
            )
        )

    companion object {
        private fun createChestItem(material: Material, amount: Int): ChestItem {
            return ChestItem(ItemBuilder(material, amount).build(), 70.0)
        }
    }
}