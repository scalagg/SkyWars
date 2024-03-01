package gg.pixelarcade.skywars.menu.kit;

import gg.pixelarcade.skywars.kit.Kit;
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
@RequiredArgsConstructor
public class KitViewMenu extends Menu {

    private final Kit kit;

    @Override
    public String getTitle(Player player) {
        return "Viewing " + kit.getName() + " Kit";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        // TODO: Display kit contents

        return buttons;
    }
}
