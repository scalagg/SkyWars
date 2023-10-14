package gg.pixelarcade.skywars.item.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.item.Item;
import gg.pixelarcade.skywars.kit.menu.KitSelectorMenu;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.item.impl
 * @date 25/09/2023 / 22:49
 */

public class LoadoutItem extends Item {

    private final Player player;

    public LoadoutItem(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.EMERALD)
                .setName(Color.translate("&aKit Selector &7(Right Click)"))
                .toItemStack();
    }

    @Override
    public void click(Action action) {
        Phoenix.getInstance().getMenuHandler().openMenu(new KitSelectorMenu(), player);
    }
}
