package gg.pixelarcade.skywars.game.listener;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.game.SkyWarsGame;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.List;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.game.listener
 * @date 05/10/2023 / 03:30
 */

@RequiredArgsConstructor
public class SpectatorListener implements Listener {

    private final SkyWarsPlugin plugin;

    private final List<Material> ALLOWED_ITEMS = List.of(
            Material.BOOK,
            Material.PAPER,
            Material.COMPASS
    );

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        SkyWarsGame game = plugin.getGameHandler().getGame(event.getPlayer());
        if (game == null)
            return;

        event.setCancelled(game.isSpectating(player.getUniqueId()));
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        SkyWarsGame game = plugin.getGameHandler().getGame(event.getPlayer());

        if (game == null)
            return;

        event.setCancelled(game.isSpectating(player.getUniqueId()));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        SkyWarsGame game = plugin.getGameHandler().getGame(event.getPlayer());
        if (game == null)
            return;

        if (event.getAction() == Action.PHYSICAL)
            return;

        boolean cancel = game.isSpectating(player.getUniqueId()) && ALLOWED_ITEMS.contains(player.getItemInHand().getType());
        event.setCancelled(cancel);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();
        SkyWarsGame game = plugin.getGameHandler().getGame(player);

        if (game == null)
            return;

        event.setCancelled(game.isSpectating(player.getUniqueId()));
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();
        SkyWarsGame game = plugin.getGameHandler().getGame(player);

        if (game == null)
            return;


        event.setCancelled(game.isSpectating(player.getUniqueId()));
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        SkyWarsGame game = plugin.getGameHandler().getGame(event.getPlayer());

        if (game == null)
            return;

        event.setCancelled(game.isSpectating(player.getUniqueId()));
    }

    @EventHandler
    public void onPlayerPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        SkyWarsGame game = plugin.getGameHandler().getGame(event.getPlayer());

        if (game == null)
            return;

        event.setCancelled(game.isSpectating(player.getUniqueId()));
    }

}
