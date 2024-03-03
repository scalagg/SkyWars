package gg.pixelarcade.skywars.kit.menu;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.KitHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author puugz
 * @since Mar 01, 2024
 */
@RequiredArgsConstructor
public class KitListMenu extends Menu {

    private final GameType gameType;
    private final KitHandler kitHandler = SkyWarsShared.getInstance().getKitHandler();

    @Override
    public String getTitle(Player player) {
        return gameType.getDisplayName() + " Kits";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        kitHandler.getKits()
                .stream().filter(kit -> kit.getGameType() == gameType)
                .forEach(kit -> buttons.put(buttons.size(), new KitButton(kit)));

        return buttons;
    }

    @RequiredArgsConstructor
    protected static class KitButton extends Button {
        private final Kit kit;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(kit.getDisplayMaterial())
                    .setName(ChatColor.GOLD + kit.getName() + " Kit")
                    .setLore(
                            "",
                            ChatColor.GREEN + "Click to view kit contents."
                    )
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            Phoenix.getInstance().getMenuHandler()
                    .openMenu(new KitInspectMenu(kit), player);
        }
    }
}
