package gg.pixelarcade.skywars.chests.impl.insane.stages;

import gg.pixelarcade.skywars.chests.ChestItem;
import gg.pixelarcade.skywars.chests.ChestStage;
import gg.pixelarcade.skywars.chests.type.RefillType;
import io.netty.util.internal.ThreadLocalRandom;
import org.bukkit.Material;
import net.evilblock.cubed.util.bukkit.ItemBuilder;

import java.util.Arrays;
import java.util.List;

public class SecondInsaneStage extends ChestStage {
    @Override
    public RefillType getRefillType() {
        return RefillType.SECOND;
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
                new ChestItem(new ItemBuilder(Material.ENDER_PEARL, ThreadLocalRandom.current().nextInt(1, 6)).build(), 100)
        );
    }
}
