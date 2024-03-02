package gg.pixelarcade.skywars.listeners;

import gg.pixelarcade.skywars.SkyWarsLobby;
import gg.pixelarcade.skywars.SkyWarsLobbyPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
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
    public void onPlayerJoin(PlayerJoinEvent e) {
        plugin.giveItems(e.getPlayer());
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Player player;

        if (event.getEntity() instanceof Player){
            player = (Player) event.getEntity();
        } else {
            return;
        }

        if(event.getCause() == EntityDamageEvent.DamageCause.VOID){
            event.setCancelled(true);
            player.teleport(SkyWarsLobbyPlugin.getInstance().getServer().getWorld("world").getSpawnLocation());
        }

    }
}