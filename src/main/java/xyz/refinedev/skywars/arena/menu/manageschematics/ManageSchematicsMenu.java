package xyz.refinedev.skywars.arena.menu.manageschematics;

import org.bukkit.entity.Player;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.button.BackButton;
import xyz.refinedev.phoenix.menu.api.pagination.PaginatedMenu;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.skywars.arena.menu.manage.ManageMenu;
import xyz.refinedev.skywars.handler.ArenaHandler;
import xyz.refinedev.skywars.pxSkyWars;

import java.util.HashMap;
import java.util.Map;

public final class ManageSchematicsMenu extends PaginatedMenu {

    public ManageSchematicsMenu() {
        setAutoUpdate(true);
    }

    @Override
    public String getTitle(Player player) {
        return "Manage Schematics";
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        buttons.put(4, new BackButton(new ManageMenu()));
        return buttons;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        ArenaHandler arenaHandler = pxSkyWars.getInstance().getArenaHandler();
        Map<Integer, Button> buttons = new HashMap<>();
        int index = 0;

        for (ArenaSchematic schematic : arenaHandler.getSchematics()) {
            buttons.put(index++, new ManageSchematicButton(schematic));
        }

        return buttons;
    }

    @Override
    public int getMaxItemsPerPage(Player player) {
        return 9 * 4;
    }
}