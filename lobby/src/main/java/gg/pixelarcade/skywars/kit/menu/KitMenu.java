package gg.pixelarcade.skywars.kit.menu;

import gg.pixelarcade.skywars.game.state.GameType;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
public class KitMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "Kit Menu";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        // TODO: Set proper positions
        buttons.put(0, new KitCategoryButton(Material.WOOD_SWORD, GameType.NORMAL));
        buttons.put(1, new KitCategoryButton(Material.IRON_SWORD, GameType.INSANE));
        buttons.put(2, new KitCategoryButton(Material.DIAMOND_SWORD, GameType.RANKED));

        // TODO: Add normal, insane, ranked perk items

        return buttons;
    }

    @RequiredArgsConstructor
    private static class KitCategoryButton extends Button {

        private final Material material;
        private final GameType gameType;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(material)
                    .setName(ChatColor.GOLD + gameType.getDisplayName() + " Kits")
                    .setLore(
                            "",
                            ChatColor.GREEN + "Click to view."
                    )
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            Phoenix.getInstance().getMenuHandler()
                    .openMenu(new KitListMenu(gameType), player);
        }
    }
}
