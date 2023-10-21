package gg.pixelarcade.skywars.chests.impl.casual.stages;

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

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.chests.stage.test
 * @date 17/10/2023 / 16:00
 */

public class FirstCasualStage extends ChestStage {

    @Override
    public RefillType getRefillType() {
        return RefillType.FIRST;
    }

    @Override
    public List<ChestItem> getSpawnLoot() {
        return Arrays.asList(
                createChestItem(Material.IRON_HELMET, 1, 100),
                createChestItem(Material.GOLD_CHESTPLATE, 1, 100),
                createChestItem(Material.LEATHER_LEGGINGS, 1, 100),
                createChestItem(Material.CHAINMAIL_BOOTS, 1, 100),
                createChestItem(Material.WATER_BUCKET, 1, 100),
                createChestItem(Material.LAVA_BUCKET, 1, 100),

                createChestItem(Material.DIAMOND_AXE, 1, 100),
                createChestItem(Material.DIAMOND_PICKAXE, 1, 100),
                createChestItem(Material.STONE_PICKAXE, 1, 100),
                createChestItem(Material.STONE_AXE, 1, 100),
                createChestItem(Material.WOOD_PICKAXE, 1, 100),
                createChestItem(Material.WOOD_AXE, 1, 100),
                createChestItem(Material.STONE_PICKAXE, 1, 100),
                createChestItem(Material.IRON_PICKAXE, 1, 100),
                createChestItem(Material.GOLDEN_APPLE, 1, 100),
                createChestItem(Material.GOLDEN_APPLE, 2, 100),

                createChestItem(Material.EXP_BOTTLE, ThreadLocalRandom.current().nextInt(8, 12), 100),
                createChestItem(Material.SNOW_BALL, ThreadLocalRandom.current().nextInt(12, 16), 100),
                createChestItem(Material.EGG, ThreadLocalRandom.current().nextInt(8, 16), 100),
                createChestItem(Material.COOKED_BEEF, ThreadLocalRandom.current().nextInt(8, 12), 100),
                createChestItem(Material.COOKED_BEEF, ThreadLocalRandom.current().nextInt(8, 12), 100),
                createChestItem(Material.WOOD, ThreadLocalRandom.current().nextInt(8, 64), 100),
                createChestItem(Material.STONE, ThreadLocalRandom.current().nextInt(8, 64), 100),

                new ChestItem(new ItemBuilder(Material.STONE_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 5).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.STONE_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 6).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 6).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 1).toItemStack(), 25),
                new ChestItem(SkyWarsPlugin.getInstance().getAbilityHandler().getAbilityById("pumpkinpie").getItem(), 65),
                new ChestItem(SkyWarsPlugin.getInstance().getAbilityHandler().getAbilityById("throwabletnt").getItem(), 10),
                new ChestItem(SkyWarsPlugin.getInstance().getAbilityHandler().getAbilityById("throwabledirt").getItem(), 45),
                new ChestItem(SkyWarsPlugin.getInstance().getAbilityHandler().getAbilityById("safeteleport").getItem(), 5),
                new ChestItem(SkyWarsPlugin.getInstance().getAbilityHandler().getAbilityById("zombieguardian").getItem(), 5),
                new ChestItem(SkyWarsPlugin.getInstance().getAbilityHandler().getAbilityById("magnet").getItem(), 10)

        );
    }

    @Override
    public List<ChestItem> getMiddleLoot() {
        return Arrays.asList(
                createChestItem(Material.SNOW_BALL, ThreadLocalRandom.current().nextInt(6, 32), 100),
                createChestItem(Material.LAVA_BUCKET, 1, 100),
                createChestItem(Material.EXP_BOTTLE, ThreadLocalRandom.current().nextInt(8, 64), 100),
                createChestItem(Material.GOLDEN_APPLE, ThreadLocalRandom.current().nextInt(1, 2), 100),
                createChestItem(Material.LOG, ThreadLocalRandom.current().nextInt(8, 64), 100),
                createChestItem(Material.TNT, ThreadLocalRandom.current().nextInt(8, 10), 100),
                createChestItem(Material.ENDER_PEARL, 1, 100),

                new ChestItem(new ItemBuilder(Material.DIAMOND_HELMET, 1).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.IRON_CHESTPLATE, 1).addEnchantment(Enchantment.PROTECTION_PROJECTILE, 3).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.IRON_LEGGINGS, 1).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_BOOTS, 1).addEnchantment(Enchantment.PROTECTION_FIRE, 5).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.IRON_SWORD, 1).addEnchantment(Enchantment.FIRE_ASPECT, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 0), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.BOW, 1).addEnchantment(Enchantment.ARROW_DAMAGE, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.ENDER_PEARL, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_AXE, 1).addEnchantment(Enchantment.DIG_SPEED, 1).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_PICKAXE, 1).addEnchantment(Enchantment.DIG_SPEED, 1).toItemStack(), 100)
        );
    }

    private static ChestItem createChestItem(Material material, int amount, int chance) {
        return new ChestItem(new ItemBuilder(material, amount).toItemStack(), chance);
    }

}