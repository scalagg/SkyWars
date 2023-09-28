package xyz.refinedev.skywars.chests.impl.insane;

import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.chests.LootableChest;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.game.state.RefillType;

import java.util.Arrays;
import java.util.List;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.chests.impl.insane
 * @date 27/09/2023 / 20:39
 */

public class InsaneChest extends LootableChest {

    @Override
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.INSANE_SOLO, GameType.INSANE_DOUBLES);
    }

    @Override
    public List<ItemStack> getSpawnLoot(RefillType type) {
        return null;
    }

    @Override
    public List<ItemStack> getMiddleLoot(RefillType type) {
        return null;
    }
}
