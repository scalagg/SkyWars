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

public class SecondRankedStage extends ChestStage {
    @Override
    public RefillType getRefillType() {
        return RefillType.SECOND;
    }


    @Override
    public List<ChestItem> getSpawnLoot() {
        return Arrays.asList(
                new ChestItem(new ItemBuilder(Material.ENDER_PEARL, ThreadLocalRandom.current().nextInt(1, 6)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 1), true).toItemStack(), 100)
        );
    }

    @Override
    public List<ChestItem> getMiddleLoot() {
        return Arrays.asList(
                new ChestItem(new ItemBuilder(Material.ENDER_PEARL, ThreadLocalRandom.current().nextInt(1, 12)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_HELMET, 3).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(), 25),
                new ChestItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE, 3).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(), 25),
                new ChestItem(new ItemBuilder(Material.DIAMOND_LEGGINGS, 3).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(), 25),
                new ChestItem(new ItemBuilder(Material.DIAMOND_BOOTS, 3).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(), 25),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 1), true).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_SWORD, 1).addEnchantment(Enchantment.FIRE_ASPECT, 2).addEnchantment(Enchantment.DAMAGE_ALL, 1).toItemStack(), 100)

        );
    }
}
