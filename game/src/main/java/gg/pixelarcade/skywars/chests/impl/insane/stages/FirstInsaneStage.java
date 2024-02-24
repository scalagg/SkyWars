package gg.pixelarcade.skywars.chests.impl.insane.stages;

import gg.pixelarcade.skywars.SkyWarsGame;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.chests.ChestItem;
import gg.pixelarcade.skywars.chests.ChestStage;
import gg.pixelarcade.skywars.chests.type.RefillType;
import io.netty.util.internal.ThreadLocalRandom;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
                new ChestItem(new ItemBuilder(Material.DIAMOND_HELMET, 1).toItemStack(), 65),
                new ChestItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).toItemStack(), 65),
                new ChestItem(new ItemBuilder(Material.DIAMOND_LEGGINGS, 1).toItemStack(), 65),
                new ChestItem(new ItemBuilder(Material.DIAMOND_BOOTS, 1).toItemStack(), 65),
                new ChestItem(new ItemBuilder(Material.IRON_HELMET, 1).toItemStack(), 70),
                new ChestItem(new ItemBuilder(Material.IRON_CHESTPLATE, 1).toItemStack(), 70),
                new ChestItem(new ItemBuilder(Material.IRON_BOOTS, 1).toItemStack(), 70),
                new ChestItem(new ItemBuilder(Material.IRON_LEGGINGS, 1).toItemStack(), 70),
                new ChestItem(new ItemBuilder(Material.IRON_SWORD, 1).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack(), 95),
                new ChestItem(new ItemBuilder(Material.DIAMOND_SWORD, 1).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack(), 64),
                new ChestItem(new ItemBuilder(Material.BOW, 1).addEnchant(Enchantment.ARROW_DAMAGE, 1).toItemStack(), 65),
                new ChestItem(new ItemBuilder(Material.ARROW, ThreadLocalRandom.current().nextInt(15, 20)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 33, 0), true).toItemStack(), 95),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 16, 1), true).toItemStack(), 95),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.POISON, 16, 0), true).toItemStack(), 95),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 64, 1), true).toItemStack(), 95),
                new ChestItem(new ItemBuilder(Material.EGG, ThreadLocalRandom.current().nextInt(8, 12)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.SNOW_BALL, ThreadLocalRandom.current().nextInt(8, 12)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 180, 0), true).toItemStack(), 95),
                new ChestItem(new ItemBuilder(Material.FISHING_ROD, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.COOKED_BEEF, 16).toItemStack(), 100),
                new ChestItem(SkyWarsGame.getGameInstance().getAbilityHandler().getAbilityById("pumpkinpie").getItem(), 75),
                new ChestItem(SkyWarsGame.getGameInstance().getAbilityHandler().getAbilityById("throwabletnt").getItem(), 15),
                new ChestItem(SkyWarsGame.getGameInstance().getAbilityHandler().getAbilityById("throwabledirt").getItem(), 50),
                new ChestItem(SkyWarsGame.getGameInstance().getAbilityHandler().getAbilityById("safeteleport").getItem(), 10),
                new ChestItem(SkyWarsGame.getGameInstance().getAbilityHandler().getAbilityById("zombieguardian").getItem(), 10),
                new ChestItem(SkyWarsGame.getGameInstance().getAbilityHandler().getAbilityById("magnet").getItem(), 15),
                new ChestItem(new ItemBuilder(Material.EXP_BOTTLE, ThreadLocalRandom.current().nextInt(8, 32)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_AXE, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_PICKAXE, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.WATER_BUCKET, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.LAVA_BUCKET, 1).toItemStack(), 56),
                new ChestItem(new ItemBuilder(Material.LOG, ThreadLocalRandom.current().nextInt(8, 64)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.STONE, ThreadLocalRandom.current().nextInt(8, 64)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.ENCHANTMENT_TABLE, 1).toItemStack(), 94)
        );
    }

    @Override
    public List<ChestItem> getMiddleLoot() {
        return Arrays.asList(
                new ChestItem(new ItemBuilder(Material.DIAMOND_HELMET, 1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(), 95),
                new ChestItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).addEnchant(Enchantment.PROTECTION_PROJECTILE, 2).toItemStack(), 95),
                new ChestItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(), 96),
                new ChestItem(new ItemBuilder(Material.DIAMOND_BOOTS, 1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.PROTECTION_FIRE, 5).toItemStack(), 98),
                new ChestItem(new ItemBuilder(Material.DIAMOND_SWORD, 1).addEnchant(Enchantment.DAMAGE_ALL, 1).addEnchant(Enchantment.FIRE_ASPECT, 2).toItemStack(), 96),
                new ChestItem(new ItemBuilder(Material.BOW, 1).addEnchant(Enchantment.ARROW_DAMAGE, 5).toItemStack(), 96),
                new ChestItem(new ItemBuilder(Material.ARROW, 16).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.ARROW, 64).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 16, 1), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 64, 1), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.POISON, 16, 1), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.SNOW_BLOCK, ThreadLocalRandom.current().nextInt(1, 32)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.EXP_BOTTLE, ThreadLocalRandom.current().nextInt(32, 64)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_AXE, 1).addEnchant(Enchantment.DIG_SPEED, 3).toItemStack(), 76),
                new ChestItem(new ItemBuilder(Material.DIAMOND_PICKAXE, 1).addEnchant(Enchantment.DIG_SPEED, 3).toItemStack(), 76),
                new ChestItem(new ItemBuilder(Material.ENDER_PEARL, ThreadLocalRandom.current().nextInt(1, 3)).toItemStack(), 50),
                new ChestItem(new ItemBuilder(Material.GOLDEN_APPLE, ThreadLocalRandom.current().nextInt(5, 8)).toItemStack(), 60),
                new ChestItem(new ItemBuilder(Material.TNT, 10).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.FLINT_AND_STEEL, 1).toItemStack(), 100),

                new ChestItem(SkyWarsGame.getGameInstance().getAbilityHandler().getAbilityById("pumpkinpie").getItem(), 75),
                new ChestItem(SkyWarsGame.getGameInstance().getAbilityHandler().getAbilityById("throwabletnt").getItem(), 15),
                new ChestItem(SkyWarsGame.getGameInstance().getAbilityHandler().getAbilityById("throwabledirt").getItem(), 50),
                new ChestItem(SkyWarsGame.getGameInstance().getAbilityHandler().getAbilityById("safeteleport").getItem(), 10),
                new ChestItem(SkyWarsGame.getGameInstance().getAbilityHandler().getAbilityById("zombieguardian").getItem(), 10),
                new ChestItem(SkyWarsGame.getGameInstance().getAbilityHandler().getAbilityById("magnet").getItem(), 15),
                new ChestItem(new ItemBuilder(Material.WOOD, ThreadLocalRandom.current().nextInt(15, 54)).toItemStack(), 100)
        );
    }
}
