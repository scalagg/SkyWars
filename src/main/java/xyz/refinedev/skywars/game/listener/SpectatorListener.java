package xyz.refinedev.skywars.game.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.pxSkyWars;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.game.listener
 * @date 05/10/2023 / 03:30
 */

@RequiredArgsConstructor
public class SpectatorListener implements Listener {

    private final pxSkyWars plugin;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        SkyWarsGame game = plugin.getGameHandler().getGame(event.getPlayer());

        if (game == null)
            return;

        if (!game.isSpectating(player.getUniqueId()))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        SkyWarsGame game = plugin.getGameHandler().getGame(event.getPlayer());

        if (game == null)
            return;

        if (!game.isSpectating(player.getUniqueId()))
            return;

        event.setCancelled(true);
    }

}
