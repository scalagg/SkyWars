package xyz.refinedev.skywars.menu.arena;

import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.menu.arena.manage.ManageSchematicMenu;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.arena.Arena;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.skywars.handler.ArenaHandler;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.pagination.PaginatedMenu;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;
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
        return "&b&lArenas Menu";
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> slots = new HashMap<>();
        for (ArenaSchematic schematic : pxSkyWars.getInstance().getArenaHandler().getSchematics()) {
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
            ArenaHandler arenaHandler = pxSkyWars.getInstance().getArenaHandler();
            int totalCopies = 0;
            int inUseCopies = 0;

            for (Arena arena : arenaHandler.getArenas(schematic)) {
                totalCopies++;

                if (arena.isInUse()) {
                    inUseCopies++;
                }
            }

            ItemBuilder item = new ItemBuilder(schematic.getIcon().getItemType());
            item.setName("&3" + schematic.getName());
            item.setDurability(schematic.getIcon().getData());
            for (String s : Arrays.asList(
                    "",
                    "&fFile: &6<file>",
                    "&fEnabled: &6<enabled>",
                    "&fCopies: &6<total>",
                    "&fCopies (Used): &6<used>",
                    ""
            )) {
                item.addLoreLine(s
                        .replace("<file>", schematic.getSchematicFile().getName())
                        .replace("<enabled>", schematic.isEnabled() ? "&aEnabled" : "&cDisabled")
                        .replace("<total>", String.valueOf(totalCopies))
                        .replace("<used>", String.valueOf(inUseCopies))
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
