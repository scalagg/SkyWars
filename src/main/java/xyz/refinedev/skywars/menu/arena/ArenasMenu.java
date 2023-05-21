package xyz.refinedev.skywars.menu.arena;

import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.arena.Arena;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.bolt.config.MenuConfig;
import xyz.refinedev.skywars.handler.ArenaHandler;
import xyz.refinedev.bolt.menus.arena.manage.ManageSchematicMenu;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.pagination.PaginatedMenu;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * This Project is the property of Phoenix Development © 2022
 * Redistribution of this Project is not allowed
 *
 * @author Creaxx
 * Created At: 9/11/2022
 * Project: pxPractice
 */
public class ArenasMenu extends PaginatedMenu {
    @Override
    public String getTitle(Player player) {
        return MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.TITLE;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> slots = new HashMap<>();
        for (ArenaSchematic schematic : Bolt.getInstance().getArenaHandler().getSchematics()) {
            if (schematic == null) continue;
            slots.put(slots.size(), new ArenaSlot(schematic));
        }
        return slots;
    }

    @AllArgsConstructor
    private static class ArenaSlot extends Button {
        private ArenaSchematic schematic;

        @Override
        public ItemStack getItem(Player player) {
            ArenaHandler arenaHandler = Bolt.getInstance().getArenaHandler();
            int totalCopies = 0;
            int inUseCopies = 0;

            for (Arena arena : arenaHandler.getArenas(schematic)) {
                totalCopies++;

                if (arena.isInUse()) {
                    inUseCopies++;
                }
            }

            ItemBuilder item = new ItemBuilder(schematic.getIcon().getItemType());
            item.setName(MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.NAME.replace("<schematic>", schematic.getName()));
            item.setDurability(schematic.getIcon().getData());
            for (String s : MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.INFORMATION) {
                item.addLoreLine(s
                        .replace("<file>", schematic.getSchematicFile().getName())
                        .replace("<enabled>", schematic.isEnabled() ? MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.ENABLED : MenuConfig.IMP.ARENA_MENUS.ARENA_ADMIN_MENUS.DISABLED)
                        .replace("<total>", String.valueOf(totalCopies))
                        .replace("<used>", String.valueOf(inUseCopies))
                        .replace("<kits>", StringUtils.join(schematic.getKits(), ", "))
                );
            }
            return item.toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            Phoenix.getInstance().getMenuHandler().openMenu(new ManageSchematicMenu(schematic), player);
        }
    }
}
