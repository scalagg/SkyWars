package net.mccade.skywars.listeners;

import lombok.RequiredArgsConstructor;
import net.mccade.skywars.Limbo;
import net.mccade.skywars.LimboPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.general.Tasks;

@RequiredArgsConstructor
public class LobbyListener implements Listener {

    private final Limbo plugin;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Variables
        Player player = event.getPlayer();

        plugin.getLobbyHandler().addPlayer(player);

        // We want to give the player time to see their join message
        Tasks.runLater(() -> {
            // Broadcast that the player has joined, as well as their position in the queue
            Bukkit.broadcastMessage(
                    Color.translate(Phoenix.getInstance().getApi().getProfileHandler().getProfile(player.getUniqueId()).getNameWithColor()) +
                            ChatColor.YELLOW + " has joined (" +
                            ChatColor.AQUA + plugin.getLobbyHandler().getLobby().getPlayers().size() +
                            ChatColor.YELLOW + "/" +
                            ChatColor.AQUA + plugin.getLobbyHandler().getLobby().getGameTeamType().getMaxPlayers() +
                            ChatColor.YELLOW + ")!");
        }, 2L);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerLeave(PlayerQuitEvent event) {
        //Variables
        Player player = event.getPlayer();

        plugin.getLobbyHandler().removePlayer(player);

        // Broadcast that the player has left
        Bukkit.broadcastMessage(player.getDisplayName() + ChatColor.YELLOW + " has quit!");
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player))
            return;

        if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
            player.teleport(LimboPlugin.getInstance().getServer().getWorld("world").getSpawnLocation());
            event.setCancelled(true);
        }
    }

}
