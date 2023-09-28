package xyz.refinedev.skywars.item.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.item.Item;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.item.impl
 * @date 25/09/2023 / 22:45
 */

public class QueueItem extends Item {

    private final Player player;

    public QueueItem(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.BOOK, 1)
                .setName(Color.translate("&aQueue Skywars &7(Right Click)"))
                .toItemStack();
    }

    @Override
    public void click(Action action) {

    }
}
