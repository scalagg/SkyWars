package xyz.refinedev.skywars.chests.impl.casual;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.chests.LootableChest;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.game.state.RefillType;

import java.util.Arrays;
import java.util.List;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.chests.impl
 * @date 27/09/2023 / 20:22
 */

public class CasualChest extends LootableChest {

    @Override
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.CASUAL_SOLO, GameType.CASUAL_DOUBLES);
    }

    @Override
    public List<ItemStack> getSpawnLoot(RefillType type) {
        return Arrays.asList(
                new ItemBuilder(Material.DIAMOND_SWORD)
                        .toItemStack()
        );
    }

    @Override
    public List<ItemStack> getMiddleLoot(RefillType type) {
        return Arrays.asList(
                new ItemBuilder(Material.IRON_AXE)
                        .toItemStack()
        );
    }
}
