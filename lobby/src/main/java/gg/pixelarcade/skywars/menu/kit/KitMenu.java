package gg.pixelarcade.skywars.menu.kit;

import gg.pixelarcade.skywars.game.state.GameType;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;

import java.util.HashMap;
import java.util.Map;

/**
 * @author puugz
 * @since Mar 01, 2024
 */
public class KitMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "Kit Menu";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        // TODO: Add normal, insane, ranked kit items
        // TODO: Add normal, insane, ranked perk items

        return buttons;
    }
}
