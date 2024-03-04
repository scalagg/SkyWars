package gg.pixelarcade.skywars.menu.mapSelector;

import gg.pixelarcade.skywars.game.state.GameTeamType;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.menu.utils.FillerButton;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.pagination.PaginatedMenu;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class SoloOrDoublesMenu extends PaginatedMenu{

    private final GameType gameType;

    @Override
    public String getTitle(Player player){
        return ("Play SkyWars");
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player){
        final Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(2, new TeamTypeButton(GameTeamType.SOLO));
        buttons.put(4, new TeamTypeButton(GameTeamType.DOUBLES));

        for (int i = 0; i < 7; i++){
            buttons.computeIfAbsent(i, k -> new FillerButton());
        }

        return buttons;
    }

    @RequiredArgsConstructor
    public static class TeamTypeButton extends Button{
        private final GameTeamType gameTeamType;

        @Override
        public ItemStack getItem(Player player){
            switch (gameTeamType){
                case SOLO -> {
                    return new ItemBuilder(XMaterial.ENDER_PEARL)
                            .setName("&6&lSolo")
                            .addLoreLine("")
                            .addLoreLine("&e(Click to Open)")
                            .toItemStack();
                }
                case DOUBLES -> {
                    return new ItemBuilder(XMaterial.ENDER_EYE)
                            .setName("&6&lDoubles")
                            .addLoreLine("")
                            .addLoreLine("&e(Click to Open)")
                            .toItemStack();
                }
                default -> {
                    return null;
                }
            }
        }

        @Override
        public void clicked(Player player, ClickType clickType){
            player.sendMessage("You clicked: " + gameTeamType);
        }
    }

}
