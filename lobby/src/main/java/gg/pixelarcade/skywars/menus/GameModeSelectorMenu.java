package gg.pixelarcade.skywars.menus;

import gg.pixelarcade.skywars.SkyWarsLobby;
import gg.pixelarcade.skywars.game.state.GameTeamType;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.menus.utils.FillerButton;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.pagination.PaginatedMenu;
import xyz.refinedev.phoenix.packet.api.PacketHandler;
import xyz.refinedev.phoenix.packet.impl.profile.BungeePlayerSendPacket;
import xyz.refinedev.phoenix.server.ServerData;
import xyz.refinedev.phoenix.utils.Utilities;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.*;

/**
 * @Author: Disunion
 * Name: GameModeSelectorMenu
 * Project SkyWarsLobby
 * Created At: 03/02/2024
 */

@RequiredArgsConstructor
public class GameModeSelectorMenu extends PaginatedMenu {

    private final GameTeamType gameTeamType;

    @Override
    public String getTitle(Player player){
        return "Play SkyWars";
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        switch (gameTeamType) {
            case SOLO -> {
                buttons.put(1, new GameModeButton(GameType.NORMAL, GameTeamType.SOLO));
                buttons.put(3, new GameModeButton(GameType.INSANE, GameTeamType.SOLO));
                buttons.put(5, new GameModeButton(GameType.RANKED, GameTeamType.SOLO));
            }
            case DOUBLES -> {
                buttons.put(1, new GameModeButton(GameType.NORMAL, GameTeamType.DOUBLES));
                buttons.put(5, new GameModeButton(GameType.INSANE, GameTeamType.DOUBLES));
            }
        }

        for (int i = 0; i < 7; i++){
            buttons.computeIfAbsent(i, k -> new FillerButton());
        }

        return buttons;
    }

    @RequiredArgsConstructor
    public static class GameModeButton extends Button {
        private final GameType gameType;
        private final GameTeamType gameTeamType;

        @Override
        public ItemStack getItem(Player player){
            switch(gameType){
                case NORMAL -> {
                    return new ItemBuilder(XMaterial.WOODEN_SWORD)
                            .setName("&6&lNormal Mode")
                            .addLoreLine("")
                            .addLoreLine("&eClick to join")
                            .addLoreLine("&ea random game!")
                            .toItemStack();
                }
                case INSANE -> {
                    return new ItemBuilder(XMaterial.IRON_SWORD)
                            .setName("&6&lInsane Mode")
                            .addLoreLine("")
                            .addLoreLine("&eClick to join")
                            .addLoreLine("&ea random game!")
                            .toItemStack();
                }
                case RANKED -> {
                    return new ItemBuilder(XMaterial.DIAMOND_SWORD)
                            .setName("&6&lRanked Mode")
                            .addLoreLine("")
                            .addLoreLine("&eClick to join")
                            .addLoreLine("&ea random game!")
                            .toItemStack();
                }
                default -> {
                    return null;
                }
            }
        }

        @Override
        public void clicked(Player player, ClickType clickType){

            //-- Switch case for the team type: Solo or Doubles
            switch (gameTeamType) {

                //-- If the team type is solo:
                case SOLO -> {

                    //-- Switch case for the game type: Ranked, Normal, Insane
                    switch (gameType) {
                        case RANKED -> {
                            player.closeInventory();
                            player.sendMessage("Server hasn't been created yet");
                        }
                        case INSANE -> {
                            player.closeInventory();
                            player.sendMessage("Server hasn't been created yet");
                        }
                        case NORMAL -> {
                            player.closeInventory();
                            Utilities.sendPlayer(player, "skywars-limbo-01", SkyWarsLobby.getLobbyInstance().getPlugin());
                        }
                    }
                }
                case DOUBLES -> {

                    switch (gameType) {
                        case INSANE -> {
                            player.closeInventory();
                            player.sendMessage("Server hasn't been created yet");
                        }
                        case NORMAL -> {
                            player.closeInventory();
                            player.sendMessage("Server hasn't been created yet");
                        }
                    }
                }
            }
        }
    }
}