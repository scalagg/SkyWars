package xyz.refinedev.skywars.queue.menu;

import org.bukkit.entity.Player;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.queue.menu.buttons.QueueButton;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.queue.menu
 * @date 01/10/2023 / 21:50
 */

public class QueueMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "Select a Queue";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        for (GameType gameType : GameType.values()) {
            buttons.put(buttons.size(), new QueueButton(gameType));
        }

        return buttons;
    }
}
