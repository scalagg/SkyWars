package gg.pixelarcade.skywars.chests.impl.insane.stages;

import gg.pixelarcade.skywars.chests.ChestItem;
import gg.pixelarcade.skywars.chests.ChestStage;
import gg.pixelarcade.skywars.chests.type.RefillType;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import xyz.refinedev.lib.netty.util.internal.ThreadLocalRandom;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;
import java.util.List;

public class ThirdInsaneStage extends ChestStage {
    @Override
    public RefillType getRefillType() {
        return null;
    }

    @Override
    public List<ChestItem> getSpawnLoot() {
        return Arrays.asList(
                new ChestItem(new ItemBuilder(Material.ENDER_PEARL, ThreadLocalRandom.current().nextInt(1, 6)).toItemStack(), 100)
        );
    }

    @Override
    public List<ChestItem> getMiddleLoot() {
        return Arrays.asList(
                new ChestItem(new ItemBuilder(Material.ENDER_PEARL, ThreadLocalRandom.current().nextInt(1, 6)).toItemStack(), 100),
                new ChestItem(new ItemBuilder(Material.DIAMOND_LEGGINGS, 1).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack(), 100)

                );
    }
}
