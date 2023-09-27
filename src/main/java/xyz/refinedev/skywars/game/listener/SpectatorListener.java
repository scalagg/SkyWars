package xyz.refinedev.skywars.game.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.game.state.GameState;
import xyz.refinedev.skywars.pxSkyWars;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.game.listener
 * @date 27/09/2023 / 04:11
 */

public class SpectatorListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();
        SkyWarsGame game = pxSkyWars.getInstance().getGameHandler().getGame(player);

        if (game.getState() == GameState.LIVE
                || !game.isSpectating(player))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(!isSpectating(event.getPlayer()));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(!isSpectating(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        event.setCancelled(!isSpectating(event.getPlayer()));
    }

    public boolean isSpectating(Player player) {
        SkyWarsGame game = pxSkyWars.getInstance().getGameHandler().getGame(player);
        if (game == null)
            return false;

        return game.isSpectating(player);
    }

}
