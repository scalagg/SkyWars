package xyz.refinedev.skywars.game.chest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * @author Creaxx
 * Created At: 5/13/2023
 */

@Getter
@RequiredArgsConstructor
public enum RefillType {
    FIRST(Arrays.asList(new ItemStack(Material.ACACIA_DOOR)), Arrays.asList(new ItemStack(Material.ACACIA_DOOR))),
    SECOND(Arrays.asList(new ItemStack(Material.ACACIA_DOOR)), Arrays.asList(new ItemStack(Material.ACACIA_DOOR))),
    THIRD(Arrays.asList(new ItemStack(Material.ACACIA_DOOR)), Arrays.asList(new ItemStack(Material.ACACIA_DOOR)));

    private final List<ItemStack> spawnItems;
    private final List<ItemStack> middleItems;
}
