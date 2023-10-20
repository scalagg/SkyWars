package gg.pixelarcade.skywars.chests;

import gg.pixelarcade.skywars.chests.type.RefillType;
import gg.pixelarcade.skywars.game.SkyWarsGame;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.utils.ChanceUtil;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.chests
 * @date 27/09/2023 / 20:18
 */

@Getter
public abstract class LootableChest {

    public static final int MINIMUM_ITEMS = 3;
    public static final int MAXIMUM_ITEMS = 6;

    public abstract List<GameType> getGameType();
    public abstract List<ChestStage> getStages();

    public void fillChests(SkyWarsGame game, RefillType refillType) {
        if (!game.isActive())
            return;

        for (Map.Entry<Location, String> entry : game.getArena().getChests().entrySet()) {
            Block block = game.getArena().getBounds().getWorld().getBlockAt(entry.getKey());

            if (refillType == RefillType.SECOND && entry.getValue().equalsIgnoreCase("MIDDLE") && (!block.hasMetadata("placedByGame")))
                continue;

            if (refillType == RefillType.THIRD && entry.getValue().equalsIgnoreCase("MIDDLE") && block.getType() == Material.AIR)
                block.setType(Material.CHEST);

            if (!(block.getState() instanceof Chest chest))
                continue;

            int itemsToAdd = ThreadLocalRandom.current().nextInt(MINIMUM_ITEMS, MAXIMUM_ITEMS);
            Inventory inventory = chest.getBlockInventory();

            for (ChestStage stage : this.getStages()) {
                if (stage.getRefillType().isHigherThan(refillType))
                    continue;

                List<ChestItem> items = entry.getValue().equalsIgnoreCase("SPAWN")
                        ? stage.getSpawnLoot() : stage.getMiddleLoot();

                for (int i = 0; i < itemsToAdd; i++) {
                    int slot = ThreadLocalRandom.current().nextInt(27);

                    for (ChestItem chestItem : items) {
                        if (ChanceUtil.percent(chestItem.chance())) {
                            inventory.setItem(slot, chestItem.item());
                            break;
                        }
                    }
                }
            }
        }
    }
}
