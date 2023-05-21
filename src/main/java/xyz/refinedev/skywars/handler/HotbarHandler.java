package xyz.refinedev.skywars.handler;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.impl.settings.SettingsMenu;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.menu.KitSelectorMenu;
import xyz.refinedev.skywars.menu.QueueMenu;
import xyz.refinedev.skywars.profile.hotbar.HotbarItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Creaxx
 * Created At: 4/19/2023
 */
public class HotbarHandler {

    private final List<HotbarItem> items = new ArrayList<>();

    public HotbarHandler() {
        items.add(new HotbarItem(
                0,
                new ItemBuilder(Material.COMPASS).setName("&b&lQueue Skywars").addLoreLine("").addLoreLine("&").toItemStack(),
                player -> Phoenix.getInstance().getMenuHandler().openMenu(new QueueMenu(), player)
        ));
        items.add(new HotbarItem(
                1,

                new ItemBuilder(Material.BOOK).setName("&b&lProfile").toItemStack(),
                player -> player.performCommand("profile")

        ));
        items.add(new HotbarItem(
                4,
                new ItemBuilder(Material.ENDER_CHEST).setName("&b&lCosmetics").toItemStack(),
                player -> player.performCommand("cosmetics")
        ));
        items.add(new HotbarItem(
                7,
                new ItemBuilder(Material.WATCH).setName("&a&lSkywars Loadout").toItemStack(),
                player -> Phoenix.getInstance().getMenuHandler().openMenu(new KitSelectorMenu(), player)
        ));
        items.add(new HotbarItem(
                8,
                new ItemBuilder(Material.WATCH).setName("&a&lSettings").toItemStack(),
                player -> Phoenix.getInstance().getMenuHandler().openMenu(new SettingsMenu(), player)
        ));
    }

    public void addItemsToPlayer(Player player) {
        player.getInventory().clear();

        for (HotbarItem item : items) {
            player.getInventory().setItem(item.getSlot(), item.getItem());
        }

        player.updateInventory();
    }

    public HotbarItem getHotbarItem(ItemStack item) {
        for (HotbarItem hotbarItem : items) {
            if (!hotbarItem.getItem().isSimilar(item)) continue;
            return hotbarItem;
        }

        return null;
    }

}
