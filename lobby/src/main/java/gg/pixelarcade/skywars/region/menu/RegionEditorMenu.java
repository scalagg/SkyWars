package gg.pixelarcade.skywars.region.menu;

import gg.pixelarcade.skywars.SkyWarsLobby;
import gg.pixelarcade.skywars.SkyWarsLobbyPlugin;
import gg.pixelarcade.skywars.region.Region;
import gg.pixelarcade.skywars.region.menu.buttons.RegionsButton;
import org.bukkit.entity.Player;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.pagination.PaginatedMenu;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.region.menu
 * @date 19/10/2023 / 19:20
 */

public class RegionEditorMenu extends PaginatedMenu {

    @Override
    public String getTitle(Player player) {
        return "Regions";
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        for (Region regions : SkyWarsLobby.getLobbyInstance().getRegionHandler().getRegions()) {
            buttons.put(buttons.size(), new RegionsButton(regions));
        }

        return buttons;
    }
}
