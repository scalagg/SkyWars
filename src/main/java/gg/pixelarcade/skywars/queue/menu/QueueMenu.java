package gg.pixelarcade.skywars.queue.menu;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.queue.menu.buttons.ProfileButton;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alfie
 * @project Skywars
 * @date 22/10/2023 / 01:39
 */

public class QueueMenu extends Menu {

    private final static int[] BORDER_SLOTS = new int[]{0, 1, 2, 3, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
    private final static int[] YELLOW_BORDER_SLOTS = new int[]{10, 12, 14, 16};

    @Override
    public String getTitle(Player player) {
        return "Select A Queue";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        for (int border : BORDER_SLOTS)
            buttons.put(border, Button.placeholder());

        for (int yellow : YELLOW_BORDER_SLOTS)
            buttons.put(yellow, Button.placeholder(new ItemStack(160, 1, (byte) 4)));

        buttons.put(4, new ProfileButton());
        buttons.put(11, new CasualButton(GameType.CASUAL_SOLO));
        buttons.put(13, new ExperimentalButton(null));
        buttons.put(15, new InsaneButton(GameType.INSANE_SOLO));

        return buttons;
    }

    @RequiredArgsConstructor
    private static class CasualButton extends Button {

        private final GameType type;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.EMERALD)
                    .setName("&b&lCasual")
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.performCommand("game join " + type.name());
        }
    }

    @RequiredArgsConstructor
    private static class InsaneButton extends Button {

        private final GameType type;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.NETHER_STAR)
                    .setName("&a&lInsane")
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.performCommand("game join " + type.name());
        }
    }

    @RequiredArgsConstructor
    private static class ExperimentalButton extends Button {

        private final GameType type;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.DIAMOND)
                    .setName("&c&lExperimental &7(Coming Soon)")
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1.0F, 1.0F);
            player.sendMessage("&c&lComing soon...");
        }
    }

}
