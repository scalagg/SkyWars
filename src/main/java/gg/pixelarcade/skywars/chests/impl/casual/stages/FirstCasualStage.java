package gg.pixelarcade.skywars.chests.impl.casual.stages;

import gg.pixelarcade.skywars.chests.ChestItem;
import gg.pixelarcade.skywars.chests.ChestStage;
import gg.pixelarcade.skywars.chests.type.RefillType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
                new ChestItem(new ItemStack(Material.BEACON), 100)
        );
    }

    @Override
    public List<ChestItem> getMiddleLoot() {
        return Arrays.asList(
                new ChestItem(new ItemStack(Material.BED), 100)
        );
    }
}
