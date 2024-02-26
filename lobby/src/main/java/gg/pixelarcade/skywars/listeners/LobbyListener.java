package gg.pixelarcade.skywars.listeners;

import gg.pixelarcade.skywars.SkyWarsLobby;
import gg.pixelarcade.skywars.SkyWarsLobbyPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 02:50
 */

@RequiredArgsConstructor
public class LobbyListener implements Listener {

    private final SkyWarsLobby plugin;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.giveItems(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClick(final InventoryClickEvent e){
        // TODO: When I finish the ability to make a GUI, make it so that specific gui is made a non exception
//        if (!e.getInventory().equals(gameStarterGUI))
    }


}
