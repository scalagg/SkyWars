package gg.pixelarcade.skywars.chests.impl.ranked.stages;

import gg.pixelarcade.skywars.chests.ChestItem;
import gg.pixelarcade.skywars.chests.ChestStage;
import gg.pixelarcade.skywars.chests.type.RefillType;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.lib.netty.util.internal.ThreadLocalRandom;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.chests.stage.test
 * @date 17/10/2023 / 16:00
 */

public class FirstRankedStage extends ChestStage {

    @Override
    public RefillType getRefillType() {
        return RefillType.FIRST;
    }

    @Override
    public List<ChestItem> getSpawnLoot() {
        return Arrays.asList(
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 33, 1), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 67, 2), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.SNOW_BALL, ThreadLocalRandom.current().nextInt(8, 16)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.SNOW_BALL, ThreadLocalRandom.current().nextInt(8, 16)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.FISHING_ROD, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.LAVA_BUCKET, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.WATER_BUCKET, 1).toItemStack(), 100)
        );
    }

    @Override
    public List<ChestItem> getMiddleLoot() {
        return Arrays.asList(
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 33, 1), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 67, 2), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 33, 2), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 1), false).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.GOLDEN_APPLE, 8).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_AXE, 1).addEnchantment(Enchantment.DIG_SPEED, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_PICKAXE, 1).addEnchantment(Enchantment.DIG_SPEED, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_SWORD, 1).addEnchantment(Enchantment.FIRE_ASPECT, 1).toItemStack(), 50),
                new ChestItem(new ItemBuilder(Material.DIAMOND_SWORD, 1).addEnchantment(Enchantment.FIRE_ASPECT, 1).toItemStack(), 50),
                new ChestItem(new ItemBuilder(Material.SNOW_BALL, ThreadLocalRandom.current().nextInt(8, 16)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.SNOW_BALL, ThreadLocalRandom.current().nextInt(8, 16)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.FISHING_ROD, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.LAVA_BUCKET, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.WATER_BUCKET, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.LOG, 32).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_HELMET, 1).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack(), 25),
                new ChestItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack(), 25),
                new ChestItem(new ItemBuilder(Material.DIAMOND_LEGGINGS, 1).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack(), 25),
                new ChestItem(new ItemBuilder(Material.DIAMOND_BOOTS, 1).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack(), 25),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 45, 1), false).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.BOW, 1).addEnchantment(Enchantment.ARROW_DAMAGE, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.ARROW, 16).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.ARROW, 32).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.EXP_BOTTLE, 12).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.EXP_BOTTLE, 8).toItemStack(), 100)
        );
    }
}
