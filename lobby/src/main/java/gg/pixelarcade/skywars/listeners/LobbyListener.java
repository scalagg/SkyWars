package gg.pixelarcade.skywars.listeners;

import gg.pixelarcade.skywars.SkyWarsLobby;
import gg.pixelarcade.skywars.SkyWarsLobbyPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
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

}
