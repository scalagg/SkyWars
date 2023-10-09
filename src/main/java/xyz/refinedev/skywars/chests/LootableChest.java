package xyz.refinedev.skywars.chests;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.arena.Arena;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.game.state.ChestType;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.game.state.RefillType;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.utils.ChanceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.chests
 * @date 27/09/2023 / 20:18
 */

public abstract class LootableChest {

    public static final int MINIMUM_ITEMS = 3;
    public static final int MAXIMUM_ITEMS = 6;

    public abstract List<GameType> getGameType();

    public abstract List<ChestItem> getSpawnLoot(RefillType refillType);

    public abstract List<ChestItem> getMiddleLoot(RefillType refillType);

    public void fillChests(SkyWarsGame game, RefillType refillType) {
        if (!game.isActive())
            return;

        for (Map.Entry<Location, ChestType> entry : game.getArena().getChests().entrySet()) {
            Block block = game.getArena().getBounds().getWorld().getBlockAt(entry.getKey());

            if (refillType == RefillType.SECOND && entry.getValue() != ChestType.MIDDLE && (!block.hasMetadata("placedByGame")))
                continue;

            if (refillType == RefillType.THIRD && entry.getValue() == ChestType.MIDDLE && block.getType() == Material.AIR)
                block.setType(Material.CHEST);

            if (!(block.getState() instanceof Chest chest))
                continue;

            List<ChestItem> items = entry.getValue() == ChestType.SPAWN
                    ? this.getSpawnLoot(refillType) : this.getMiddleLoot(refillType);

            int itemsToAdd = pxSkyWars.getInstance().getRandom().nextInt(MINIMUM_ITEMS, MAXIMUM_ITEMS);
            Inventory inventory = chest.getBlockInventory();

            for (int i = 0; i < itemsToAdd; i++) {
                int slot = pxSkyWars.getInstance().getRandom().nextInt(27);

                for (ChestItem chestItem : items) {
                    if (ChanceUtil.percent(chestItem.getChance())) {
                        inventory.setItem(slot, chestItem.getItem());
                        break;
                    }
                }
            }
        }

        System.out.println("Filled chests for " + game.getArena().getArenaSchematic().getName() + " with refill type " + refillType);
    }
}
