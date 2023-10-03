package xyz.refinedev.skywars.game.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import xyz.refinedev.skywars.pxSkyWars;

public class BuildModeListener implements Listener {

    private final pxSkyWars instance;

    public BuildModeListener(pxSkyWars instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (event.isCancelled()) {
            return;
        }

        if (player.hasMetadata("BuildMode")) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (event.isCancelled()) {
            return;
        }

        if (player.hasMetadata("BuildMode")) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onPhysicalInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.hasMetadata("BuildMode")) {
            event.setCancelled(false);
            return;
        }

        if (!event.getAction().equals(Action.PHYSICAL)) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if (event.isCancelled()) {
            return;
        }

        if (player.hasMetadata("BuildMode")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();

        if (event.isCancelled()) {
            return;
        }

        if (player.hasMetadata("BuildMode")) {
            event.setCancelled(true);
        }
    }
//TODO: add more stuff and finish it for it doesnt happen in arenas probs

}