package xyz.refinedev.skywars.game.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.refinedev.phoenix.utils.general.Tasks;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.game.event.GameEndEvent;
import xyz.refinedev.skywars.game.event.GameStateChangeEvent;
import xyz.refinedev.skywars.game.event.PlayerJoinGameEvent;
import xyz.refinedev.skywars.game.state.GameState;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.utils.SpawnUtil;

import java.util.UUID;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.game.listener
 * @date 26/09/2023 / 22:12
 */

@RequiredArgsConstructor
public class GameListener implements Listener {

    private final pxSkyWars plugin;

    @EventHandler
    public void onPlayerJoinGame(PlayerJoinGameEvent event) {
        SkyWarsGame game = event.getGame();

        if (game.getPlayers().size() >= game.getGameType().getMaxPlayers()) {
            game.startCountdown();
        }
    }

    //TODO: 26/09/2023 Move to movement handler from spigot in future, 10x better performance
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!player.hasMetadata("frozen"))
            return;

        if (event.getFrom().getX() == event.getTo().getX()
                && event.getFrom().getZ() == event.getTo().getZ())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!player.hasMetadata("frozen"))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.hasMetadata("frozen"))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.hasMetadata("frozen"))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (!plugin.getGameHandler().isPlayingGame(player))
            return;

        SkyWarsGame game = plugin.getGameHandler().getGame(player);
        game.removePlayer(player);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        SkyWarsGame game = plugin.getGameHandler().getGame(player);
        if (game == null)
            return;

        if (player.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.VOID)
            event.getDrops().clear();

        event.setDeathMessage(null);
        Tasks.runLater(() -> player.spigot().respawn(), 10L);

        player.teleport(game.getArena().getSpectatorSpawn());

        if (player.getKiller() != null)
            game.onDeath(player, player.getKiller());
        else game.onDeath(player, null);
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();
        SkyWarsGame game = plugin.getGameHandler().getGame(player);
        if (game == null)
            return;

        boolean canHit = game.hasStarted() && game.isSpectating(player);
        event.setCancelled(canHit);
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        if (!(event.getDamager() instanceof Player))
            return;

        Player player = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();
        if (!plugin.getGameHandler().isPlayingGame(player)
                && !plugin.getGameHandler().isPlayingGame(damager))
            return;

        SkyWarsGame game = plugin.getGameHandler().getGame(player);
        if (!game.isInGame(damager)) {
            event.setCancelled(true);
            return;
        }

        boolean canHit = game.hasStarted() && game.isSpectating(player);
        event.setCancelled(canHit);
    }

    @EventHandler
    public void onGameEnd(GameEndEvent event) {
        SkyWarsGame game = event.getGame();

        for (UUID uuid : game.getPlayers()) {
            Player player = plugin.getServer().getPlayer(uuid);
            if (player == null)
                continue;

            if (!plugin.getGameHandler().isPlayingGame(player))
                continue;

            player.setGameMode(GameMode.SURVIVAL);
            player.setAllowFlight(false);
            player.setFlying(false);

            SpawnUtil.teleportToSpawn(player);
        }
    }

}
