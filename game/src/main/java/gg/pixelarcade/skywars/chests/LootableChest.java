package gg.pixelarcade.skywars.chests;

import gg.pixelarcade.skywars.SkyWarsGame;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.chests.type.RefillType;
import gg.pixelarcade.skywars.game.Game;
import gg.pixelarcade.skywars.game.GameHandler;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.map.GameMap;
import gg.pixelarcade.skywars.util.ChanceUtil;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import xyz.refinedev.phoenix.utils.general.Tasks;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.chests
 * @date 27/09/2023 / 20:18
 */

@Getter
public abstract class LootableChest {

    public static final int MINIMUM_ITEMS = 3;
    public static final int MAXIMUM_ITEMS = 6;

    public abstract GameType getGameType();
    public abstract List<ChestStage> getStages();

    public void fillChests(Game game, RefillType refillType) {
        if (!game.isActive())
            return;

        GameMap map = SkyWarsGame.getGameInstance().getGameHandler().getMap();
        Tasks.runAsync(() -> {
            for (Map.Entry<Location, String> entry : map.getChests().entrySet()) {
                Block block = map
                        .getBounds().getWorld().getBlockAt(entry.getKey());

                if (refillType == RefillType.SECOND
                        && entry.getValue().equalsIgnoreCase("MIDDLE")
                        && (!block.hasMetadata("placedByGame")))
                    continue;

                if (refillType == RefillType.THIRD
                        && entry.getValue().equalsIgnoreCase("MIDDLE")
                        && block.getType() == Material.AIR)
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

                        int randomIndex = ThreadLocalRandom.current().nextInt(items.size());
                        ChestItem chestItem = items.get(randomIndex);

                        if (ChanceUtil.percent(chestItem.chance()))
                            inventory.setItem(slot, chestItem.item());
                    }
                }
            }
        });
    }
}
