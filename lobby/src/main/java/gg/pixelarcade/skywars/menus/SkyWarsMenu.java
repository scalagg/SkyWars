package gg.pixelarcade.skywars.menus;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.menus.kits.KitListMenu;
import gg.pixelarcade.skywars.menus.perks.PerkListMenu;
import gg.pixelarcade.skywars.menus.utils.CoinsButton;
import gg.pixelarcade.skywars.menus.utils.ExitButton;
import gg.pixelarcade.skywars.menus.utils.FillerButton;
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
 * @author puugz, disunion
 * @since Mar 01, 2024
 */
public class SkyWarsMenu extends Menu {

    public SkyWarsMenu(){
        this.setBordered(true);
    }

    @Override
    public String getTitle(Player player) {
        return "SkyWars Menu";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {

        final Map<Integer, Button> buttons = new HashMap<>();

        //-- Kit Buttons
        buttons.put(this.getSlot(2, 1), new KitCategoryButton(Material.WOOD_SWORD, GameType.NORMAL));
        buttons.put(this.getSlot(4, 1), new KitCategoryButton(Material.IRON_SWORD, GameType.INSANE));
        buttons.put(this.getSlot(6, 1), new KitCategoryButton(Material.DIAMOND_SWORD, GameType.RANKED));

        //-- Perk Buttons
        buttons.put(this.getSlot(2, 2), new PerkCategoryButton(Material.NETHER_STAR));
        buttons.put(this.getSlot(4, 2), new PerkCategoryButton(Material.NETHER_STAR));
        buttons.put(this.getSlot(6, 2), new PerkCategoryButton(Material.NETHER_STAR));

        //-- Footer
        buttons.put(this.getSlot(3, 4), new ExitButton());
        buttons.put(this.getSlot(5, 4), new CoinsButton());

        //-- Filler
        for (int i = 10; i < 35; i++){
            buttons.computeIfAbsent(i, k -> new FillerButton());
        }

        return buttons;
    }

    @RequiredArgsConstructor
    private static class PerkCategoryButton extends Button {

        //-- Instance Variables
        private final Material material;

        //-- Create Buttons with given values
        @Override
        public ItemStack getItem(Player player){
            return new ItemBuilder(material)
                    .setName(ChatColor.DARK_PURPLE + "Perks")
                    .setLore(
                            "",
                            ChatColor.GREEN + "Click to view."
                    )
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType){
            Phoenix.getInstance().getMenuHandler().openMenu(new PerkListMenu(), player);
        }
    }

    @RequiredArgsConstructor
    private static class KitCategoryButton extends Button {

        //-- Instance Variables
        private final Material material;
        private final GameType gameType;

        //-- Create Buttons with given values
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
