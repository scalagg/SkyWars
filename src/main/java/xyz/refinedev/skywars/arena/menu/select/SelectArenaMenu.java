package xyz.refinedev.skywars.arena.menu.select;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.bukkit.entity.Player;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.skywars.arena.menu.ToggleAllButton;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.utils.Callback;

import java.util.Map;
import java.util.Set;

public class SelectArenaMenu extends Menu {

    private Callback<Set<String>> mapsCallback;
    private String title;
    Set<String> allMaps;
    Set<String> enabledSchematics = Sets.newHashSet();

    public SelectArenaMenu(Callback<Set<String>> mapsCallback, String title) {
        this.mapsCallback = mapsCallback;
        this.title = title;

        for (ArenaSchematic schematic : pxSkyWars.getInstance().getArenaHandler().getSchematics()) {
            enabledSchematics.add(schematic.getName());
        }

        this.allMaps = ImmutableSet.copyOf(enabledSchematics);
    }

    @Override
    public String getTitle(Player player) {
        return title;
    }

    @Override
    public Map<Integer, Button> getButtons(Player arg0) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        int i = 0;
        for (String mapName : allMaps) {
            buttons.put(i++, new ArenaButton(mapName, enabledSchematics));
        }

        int bottomRight = 8;
        while (buttons.get(bottomRight) != null) {
            bottomRight += 9;
        }

        bottomRight += 9;

        buttons.put(bottomRight, new ToggleAllButton(allMaps, enabledSchematics));

        return buttons;
    }

}
