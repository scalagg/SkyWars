package gg.pixelarcade.skywars.chests.impl;

import gg.pixelarcade.skywars.chests.LootableChest;
import org.bukkit.Material;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.chests.ChestItem;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.game.state.RefillType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.chests.impl
 * @date 06/10/2023 / 02:22
 */

public class TestChest extends LootableChest {

    @Override
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.CASUAL_SOLO, GameType.CASUAL_DOUBLES);
    }

    @Override
    public List<ChestItem> getSpawnLoot(RefillType refillType) {
        List<ChestItem> items = new ArrayList<>();

        switch (refillType) {
            case FIRST -> items.addAll(FIRST_REFILL);
            case SECOND -> items.addAll(SECOND_REFILL);
            case THIRD -> items.addAll(THIRD_REFILL);
        }

        return items;
    }

    @Override
    public List<ChestItem> getMiddleLoot(RefillType refillType) {
        return THIRD_REFILL;
    }

    private final static List<ChestItem> FIRST_REFILL = List.of(
            new ChestItem(new ItemBuilder(Material.ARROW).toItemStack(), 100),
            new ChestItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE).toItemStack(), 10),
            new ChestItem(new ItemBuilder(Material.DIAMOND_LEGGINGS).toItemStack(), 20),
            new ChestItem(new ItemBuilder(Material.DIAMOND_BOOTS).toItemStack(), 20)
    );

    private final static List<ChestItem> SECOND_REFILL = List.of(
            new ChestItem(new ItemBuilder(Material.IRON_AXE).toItemStack(), 50),
            new ChestItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE).toItemStack(), 20),
            new ChestItem(new ItemBuilder(Material.DIAMOND_LEGGINGS).toItemStack(), 20),
            new ChestItem(new ItemBuilder(Material.DIAMOND_BOOTS).toItemStack(), 10)
    );

    private final static List<ChestItem> THIRD_REFILL = List.of(
            new ChestItem(new ItemBuilder(Material.GOLD_AXE).toItemStack(), 50),
            new ChestItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE).toItemStack(), 10),
            new ChestItem(new ItemBuilder(Material.DIAMOND_LEGGINGS).toItemStack(), 20),
            new ChestItem(new ItemBuilder(Material.DIAMOND_BOOTS).toItemStack(), 20)
    );

}
