package gg.pixelarcade.skywars.chests.impl.casual.stages;

import gg.pixelarcade.skywars.chests.ChestItem;
import gg.pixelarcade.skywars.chests.ChestStage;
import gg.pixelarcade.skywars.chests.type.RefillType;
import io.netty.util.internal.ThreadLocalRandom;
import org.bukkit.Material;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.List;

public class ThirdCasualStage extends ChestStage {
    @Override
    public RefillType getRefillType() {
        return RefillType.THIRD;
    }

    @Override
    public List<ChestItem> getSpawnLoot() {
        return List.of(
                new ChestItem(new ItemBuilder(Material.ENDER_PEARL, ThreadLocalRandom.current().nextInt(1, 6)).toItemStack(), 100)
        );
    }

    @Override
    public List<ChestItem> getMiddleLoot() {
        return List.of(
                new ChestItem(new ItemBuilder(Material.ENDER_PEARL, ThreadLocalRandom.current().nextInt(1, 6)).toItemStack(), 100)
        );
    }
}
