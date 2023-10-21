package gg.pixelarcade.skywars.chests.impl.insane.stages;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.ability.impl.Magnet;
import gg.pixelarcade.skywars.chests.ChestItem;
import gg.pixelarcade.skywars.chests.ChestStage;
import gg.pixelarcade.skywars.chests.type.RefillType;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.lib.netty.util.internal.ThreadLocalRandom;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;
import java.util.List;

public class FirstInsaneStage extends ChestStage {
    @Override
    public RefillType getRefillType() {
        return RefillType.FIRST;
    }

    public List<ChestItem> getSpawnLoot() {
        return Arrays.asList(
                new ChestItem(new ItemBuilder(Material.DIAMOND_HELMET, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_LEGGINGS, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_BOOTS, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.IRON_HELMET, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.IRON_CHESTPLATE, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.IRON_BOOTS, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.IRON_LEGGINGS, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.IRON_SWORD, 1).addEnchantment(Enchantment.DAMAGE_ALL, 7).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_SWORD, 1).addEnchantment(Enchantment.DAMAGE_ALL, 7).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_SWORD, 1).addEnchantment(Enchantment.DAMAGE_ALL, 8).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.BOW, 1).addEnchantment(Enchantment.ARROW_DAMAGE, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.BOW, 1).addEnchantment(Enchantment.ARROW_DAMAGE, 3).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.ARROW, ThreadLocalRandom.current().nextInt(15, 20)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 33, 0), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 16, 1), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.POISON, 16, 0), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 64, 1), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.EGG, ThreadLocalRandom.current().nextInt(8, 12)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.SNOW_BALL, ThreadLocalRandom.current().nextInt(8, 12)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 180, 0), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.FISHING_ROD, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.COOKED_BEEF, 16).toItemStack(), 100),
                new ChestItem(SkyWarsPlugin.getInstance().getAbilityHandler().getAbilityById("pumpkinpie").getItem(), 75),
                new ChestItem(SkyWarsPlugin.getInstance().getAbilityHandler().getAbilityById("throwabletnt").getItem(), 15),
                new ChestItem(SkyWarsPlugin.getInstance().getAbilityHandler().getAbilityById("throwabledirt").getItem(), 50),
                new ChestItem(SkyWarsPlugin.getInstance().getAbilityHandler().getAbilityById("safeteleport").getItem(), 10),
                new ChestItem(SkyWarsPlugin.getInstance().getAbilityHandler().getAbilityById("zombieguardian").getItem(), 10),
                new ChestItem(SkyWarsPlugin.getInstance().getAbilityHandler().getAbilityById("magnet").getItem(), 15),
                new ChestItem(new ItemBuilder(Material.EXP_BOTTLE, ThreadLocalRandom.current().nextInt(8, 32)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_AXE, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_PICKAXE, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.WATER_BUCKET, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.LAVA_BUCKET, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.LOG, ThreadLocalRandom.current().nextInt(8, 64)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.STONE, ThreadLocalRandom.current().nextInt(8, 64)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.ENCHANTMENT_TABLE, 1).toItemStack(), 100)
        );
    }

    @Override
    public List<ChestItem> getMiddleLoot() {
        return Arrays.asList(
                new ChestItem(new ItemBuilder(Material.DIAMOND_HELMET, 1).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).addEnchantment(Enchantment.PROTECTION_PROJECTILE, 3).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_BOOTS, 1).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchantment(Enchantment.PROTECTION_FIRE, 5).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_SWORD, 1).addEnchantment(Enchantment.DAMAGE_ALL, 1).addEnchantment(Enchantment.FIRE_ASPECT, 2).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.BOW, 1).addEnchantment(Enchantment.ARROW_DAMAGE, 5).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.ARROW, 16).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.ARROW, 64).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 16, 1), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 64, 1), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.POISON, 16, 1), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.SNOW_BLOCK, ThreadLocalRandom.current().nextInt(1, 32)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.EXP_BOTTLE, ThreadLocalRandom.current().nextInt(32, 64)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_AXE, 1).addEnchantment(Enchantment.DIG_SPEED, 3).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_PICKAXE, 1).addEnchantment(Enchantment.DIG_SPEED, 3).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.ENDER_PEARL, ThreadLocalRandom.current().nextInt(1, 3)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.GOLDEN_APPLE, ThreadLocalRandom.current().nextInt(5, 8)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.TNT, 10).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.FLINT_AND_STEEL, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.WOOD, ThreadLocalRandom.current().nextInt(15, 54)).toItemStack(), 100)
        );
    }
}
