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

public class ThirdRankedStage extends ChestStage {
    @Override
    public RefillType getRefillType() {
        return RefillType.THIRD;
    }

    @Override
    public List<ChestItem> getSpawnLoot() {
        return Arrays.asList(
                new ChestItem(new ItemBuilder(Material.ENDER_PEARL, ThreadLocalRandom.current().nextInt(1, 6)).build(), 100)
        );
    }

    @Override
    public List<ChestItem> getMiddleLoot() {
        return Arrays.asList(
                new ChestItem(new ItemBuilder(Material.DIAMOND_HELMET, 3).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build(), 25),
                new ChestItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE, 3).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build(), 25),
                new ChestItem(new ItemBuilder(Material.DIAMOND_LEGGINGS, 3).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build(), 25),
                new ChestItem(new ItemBuilder(Material.DIAMOND_BOOTS, 3).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).build(), 25),
                new ChestItem(new ItemBuilder(Material.ENDER_PEARL, ThreadLocalRandom.current().nextInt(1, 6)).build(), 100),
                new ChestItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 1), true).build(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_SWORD, 1).enchant(Enchantment.FIRE_ASPECT, 2).enchant(Enchantment.DAMAGE_ALL, 1).build(), 100)
                );
    }
}
