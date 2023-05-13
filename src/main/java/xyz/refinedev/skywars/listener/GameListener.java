package xyz.refinedev.skywars.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.handler.GameHandler;
import xyz.refinedev.skywars.pxSkyWars;

/**
 * @author Creaxx
 * Created At: 5/13/2023
 */

@RequiredArgsConstructor
public class GameListener implements Listener {
    private final pxSkyWars plugin;
    private final GameHandler gameHandler;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (!gameHandler.isInGame(player)) return;

        event.setDeathMessage(null);
        player.spigot().respawn();

        SkyWarsGame game = gameHandler.getGame(player);

        if (player.getKiller() != null) {
            game.onDeath(player, player.getKiller());
        } else {
            game.onDeath(player);
        }
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        if (!gameHandler.isInGame(player)) return;

        SkyWarsGame game = gameHandler.getGame(player);

        event.setCancelled(!game.hasStarted());
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        if (!gameHandler.isInGame(player)) return;

        Player damager = (Player) event.getDamager();
        if (!gameHandler.isInGame(damager)) return;

        SkyWarsGame game = gameHandler.getGame(player);

        if (!game.isInGame(damager)) {
            event.setCancelled(true);
            return;
        }

        event.setCancelled(!game.hasStarted());
    }

}
