package gg.pixelarcade.skywars.chests.impl.casual.stages;

import gg.pixelarcade.skywars.SkyWarsGame;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.chests.ChestItem;
import gg.pixelarcade.skywars.chests.ChestStage;
import gg.pixelarcade.skywars.chests.type.RefillType;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.lib.netty.util.internal.ThreadLocalRandom;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.List;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.chests.stage.test
 * @date 17/10/2023 / 16:00
 */

public class FirstCasualStage extends ChestStage {

    @Override
    public RefillType getRefillType() {
        return RefillType.FIRST;
    }

    @Override
    public List<ChestItem> getSpawnLoot() {
        return List.of(
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

                createChestItem(Material.EXP_BOTTLE, ThreadLocalRandom.current().nextInt(8, 12)),
                createChestItem(Material.SNOW_BALL, ThreadLocalRandom.current().nextInt(12, 16)),
                createChestItem(Material.EGG, ThreadLocalRandom.current().nextInt(8, 16)),
                createChestItem(Material.COOKED_BEEF, ThreadLocalRandom.current().nextInt(8, 12)),
                createChestItem(Material.COOKED_BEEF, ThreadLocalRandom.current().nextInt(8, 12)),
                new ChestItem(new ItemBuilder(Material.WOOD, ThreadLocalRandom.current().nextInt(16, 64)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.STONE, ThreadLocalRandom.current().nextInt(8, 64)).toItemStack(), 100),

                new ChestItem(new ItemBuilder(Material.STONE_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 2).toItemStack(), 75),
                new ChestItem(new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 2).toItemStack(), 75),
                new ChestItem(new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1).toItemStack(), 25),
                new ChestItem(SkyWarsGame.getGameInstance().getAbilityHandler().getAbilityById("magnet").getItem(), 25)
        );
    }

    @Override
    public List<ChestItem> getMiddleLoot() {
        return List.of(
                createChestItem(Material.SNOW_BALL, ThreadLocalRandom.current().nextInt(6, 32)),
                createChestItem(Material.LAVA_BUCKET, 1),
                createChestItem(Material.EXP_BOTTLE, ThreadLocalRandom.current().nextInt(8, 64)),
                createChestItem(Material.GOLDEN_APPLE, ThreadLocalRandom.current().nextInt(1, 2)),
                createChestItem(Material.WOOD, ThreadLocalRandom.current().nextInt(8, 64)),
                createChestItem(Material.TNT, ThreadLocalRandom.current().nextInt(8, 10)),
                createChestItem(Material.ENDER_PEARL, 1),

                new ChestItem(new ItemBuilder(Material.DIAMOND_HELMET, 1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack(), 95),
                new ChestItem(new ItemBuilder(Material.IRON_CHESTPLATE, 1).addEnchant(Enchantment.PROTECTION_PROJECTILE, 1).toItemStack(), 96),
                new ChestItem(new ItemBuilder(Material.IRON_LEGGINGS, 1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack(), +7),
                new ChestItem(new ItemBuilder(Material.DIAMOND_BOOTS, 1).addEnchant(Enchantment.PROTECTION_FIRE, 1).toItemStack(), 90),
                new ChestItem(new ItemBuilder(Material.IRON_SWORD, 1).addEnchant(Enchantment.FIRE_ASPECT, 1).toItemStack(), 90),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 0), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.BOW, 1).addEnchant(Enchantment.ARROW_DAMAGE, 1).toItemStack(), 90),
                new ChestItem(new ItemBuilder(Material.DIAMOND_AXE, 1).addEnchant(Enchantment.DIG_SPEED, 1).toItemStack(), 90),
                new ChestItem(new ItemBuilder(Material.DIAMOND_PICKAXE, 1).addEnchant(Enchantment.DIG_SPEED, 1).toItemStack(), 90)
        );
    }

    private static ChestItem createChestItem(Material material, int amount) {
        return new ChestItem(new ItemBuilder(material, amount).toItemStack(), 70);
    }

}