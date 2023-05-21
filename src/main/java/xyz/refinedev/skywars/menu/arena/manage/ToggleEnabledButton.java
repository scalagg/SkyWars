package xyz.refinedev.skywars.menu.arena.manage;

import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.bolt.config.MenuConfig;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

@RequiredArgsConstructor
public final class ToggleEnabledButton extends Button {
    private final ArenaSchematic schematic;

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder item = new ItemBuilder(schematic.isEnabled() ? Material.EMERALD_BLOCK : Material.REDSTONE_BLOCK);
        String status = schematic.isEnabled() ? MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.ARENA_STATUS_ENABLED : MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.ARENA_STATUS_DISABLED;
        item.setName(MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.ARENA_STATUS_NAME
                .replace("<status>", status));
        if (schematic.isEnabled()) {
            for (String l1 : MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.ARENA_STATUS_LORE_ENABLED) {
                item.addLoreLine(l1);
            }
        } else {
            for (String l2 : MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.ARENA_STATUS_LORE_DISABLED) {
                item.addLoreLine(l2);
            }
        }
        return item.toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        schematic.setEnabled(!schematic.isEnabled());
        Bolt.getInstance().getArenaHandler().saveSchematics();
        Phoenix.getInstance().getMenuHandler().openMenu(new ManageSchematicMenu(schematic), player);
    }
}