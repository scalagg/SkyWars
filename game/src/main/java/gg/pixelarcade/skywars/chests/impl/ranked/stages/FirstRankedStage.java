package gg.pixelarcade.skywars.chests.impl.ranked.stages;

import gg.pixelarcade.skywars.chests.ChestItem;
import gg.pixelarcade.skywars.chests.ChestStage;
import gg.pixelarcade.skywars.chests.type.RefillType;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import io.netty.util.internal.ThreadLocalRandom;

import net.evilblock.cubed.util.bukkit.ItemBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.chests.stage.test
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
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 33, 1), true).build(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 67, 2), true).build(), 100),
                new ChestItem(new ItemBuilder(Material.SNOW_BALL, ThreadLocalRandom.current().nextInt(8, 16)).build(), 100),
                new ChestItem(new ItemBuilder(Material.SNOW_BALL, ThreadLocalRandom.current().nextInt(8, 16)).build(), 100),
                new ChestItem(new ItemBuilder(Material.FISHING_ROD, 1).build(), 100),
                new ChestItem(new ItemBuilder(Material.LAVA_BUCKET, 1).build(), 100),
                new ChestItem(new ItemBuilder(Material.WATER_BUCKET, 1).build(), 100)
        );
    }

    @Override
    public List<ChestItem> getMiddleLoot() {
        return Arrays.asList(
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 33, 1), true).build(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 67, 2), true).build(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 33, 2), true).build(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 1), false).build(), 100),
                new ChestItem(new ItemBuilder(Material.GOLDEN_APPLE, 8).build(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_AXE, 1).enchant(Enchantment.DIG_SPEED, 1).build(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_PICKAXE, 1).enchant(Enchantment.DIG_SPEED, 1).build(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_SWORD, 1).enchant(Enchantment.FIRE_ASPECT, 1).build(), 50),
                new ChestItem(new ItemBuilder(Material.DIAMOND_SWORD, 1).enchant(Enchantment.FIRE_ASPECT, 1).build(), 50),
                new ChestItem(new ItemBuilder(Material.SNOW_BALL, ThreadLocalRandom.current().nextInt(8, 16)).build(), 100),
                new ChestItem(new ItemBuilder(Material.SNOW_BALL, ThreadLocalRandom.current().nextInt(8, 16)).build(), 100),
                new ChestItem(new ItemBuilder(Material.FISHING_ROD, 1).build(), 100),
                new ChestItem(new ItemBuilder(Material.LAVA_BUCKET, 1).build(), 100),
                new ChestItem(new ItemBuilder(Material.WATER_BUCKET, 1).build(), 100),
                new ChestItem(new ItemBuilder(Material.LOG, 32).build(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_HELMET, 1).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build(), 25),
                new ChestItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build(), 25),
                new ChestItem(new ItemBuilder(Material.DIAMOND_LEGGINGS, 1).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build(), 25),
                new ChestItem(new ItemBuilder(Material.DIAMOND_BOOTS, 1).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build(), 25),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 45, 1), false).build(), 100),
                new ChestItem(new ItemBuilder(Material.BOW, 1).enchant(Enchantment.ARROW_DAMAGE, 1).build(), 100),
                new ChestItem(new ItemBuilder(Material.ARROW, 16).build(), 100),
                new ChestItem(new ItemBuilder(Material.ARROW, 32).build(), 100),
                new ChestItem(new ItemBuilder(Material.EXP_BOTTLE, 12).build(), 100),
                new ChestItem(new ItemBuilder(Material.EXP_BOTTLE, 8).build(), 100)
        );
    }
}
