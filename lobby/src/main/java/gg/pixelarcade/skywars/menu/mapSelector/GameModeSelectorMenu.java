package gg.pixelarcade.skywars.menu.mapSelector;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import gg.pixelarcade.skywars.SkyWarsLobbyPlugin;
import gg.pixelarcade.skywars.game.state.GameTeamType;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.menu.utils.FillerButton;
import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.phoenix.menu.api.pagination.PaginatedMenu;
import xyz.refinedev.phoenix.packet.api.PacketHandler;
import xyz.refinedev.phoenix.packet.impl.profile.BungeePlayerSendPacket;
import xyz.refinedev.phoenix.server.ServerData;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
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
                buttons.put(1, new GameModeButton(GameType.NORMAL));
                buttons.put(3, new GameModeButton(GameType.INSANE));
                buttons.put(5, new GameModeButton(GameType.RANKED));
            }
            case DOUBLES -> {
                buttons.put(1, new GameModeButton(GameType.NORMAL));
                buttons.put(5, new GameModeButton(GameType.INSANE));
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

            player.closeInventory();
            List<ServerData> serverList = new ArrayList<ServerData>(Phoenix.getInstance().getApi().getNetworkHandler().getServersInGroup("GAME-SERVER"));

            Random rand = new Random();
            int serverNumber = rand.nextInt(serverList.size());
            String selectedServer = serverList.get(serverNumber).getServerName();

            PacketHandler.send(new BungeePlayerSendPacket(player.getUniqueId(), selectedServer));



            //Utilities.SendPlayer();

        }
    }
}
