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

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.chests
 * @date 27/09/2023 / 20:18
 */

public abstract class LootableChest {

    public abstract List<GameType> getGameType();
    public abstract List<ItemStack> getSpawnLoot(RefillType refillType);
    public abstract List<ItemStack> getMiddleLoot(RefillType refillType);

    public void fillChests(SkyWarsGame game, Arena arena, RefillType refillType) {
        if (!game.isActive())
            return;

        for (Map.Entry<Location, ChestType> entry : arena.getChests().entrySet()) {
            Block block = arena.getBounds().getWorld().getBlockAt(entry.getKey());

            if (refillType == RefillType.SECOND && entry.getValue() != ChestType.MIDDLE && (!block.hasMetadata("placedByGame")))
                continue;

            if (refillType == RefillType.THIRD && entry.getValue() == ChestType.MIDDLE && block.getType() == Material.AIR)
                block.setType(Material.CHEST);

            if (!(block.getState() instanceof Chest))
                continue;

            Chest chest = (Chest) block.getState();
            List<ItemStack> items = entry.getValue() == ChestType.SPAWN
                    ? this.getSpawnLoot(refillType) : this.getMiddleLoot(refillType);
            Collections.shuffle(items);

            int itemsToAdd = pxSkyWars.getInstance().getRandom().nextInt(1, 6);
            Inventory inventory = chest.getBlockInventory();

            for (int i = 0; i < itemsToAdd; i++) {
                int slot = pxSkyWars.getInstance().getRandom().nextInt(27);
                inventory.setItem(slot, items.get(pxSkyWars.getInstance().getRandom().nextInt(items.size())));
            }
        }

        System.out.println("Filled chests for " + arena.getArenaSchematic().getName() + " with refill type " + refillType);
    }
}
