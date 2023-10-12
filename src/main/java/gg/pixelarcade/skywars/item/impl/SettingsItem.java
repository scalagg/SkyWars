package gg.pixelarcade.skywars.item.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.impl.settings.SettingsMenu;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.item.Item;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.item.impl
 * @date 25/09/2023 / 22:49
 */

public class SettingsItem extends Item {

    private final Player player;

    public SettingsItem(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.WATCH)
                .setName(Color.translate("&aSettings &7(Right Click)"))
                .toItemStack();
    }

    @Override
    public void click(Action action) {
        Phoenix.getInstance().getMenuHandler().openMenu(new SettingsMenu(), player);
    }
}
