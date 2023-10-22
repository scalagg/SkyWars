package gg.pixelarcade.skywars.game.listener;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import gg.pixelarcade.skywars.game.SkyWarsGame;

import java.util.List;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.game.listener
 * @date 08/10/2023 / 06:15
 */

@RequiredArgsConstructor
public class PreventionListener implements Listener {

    private final SkyWarsPlugin plugin;

    private final static List<Material> ALLOWED_INTERACTION = List.of(
            Material.PAPER,
            Material.EMERALD,
            Material.BED
    );

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        SkyWarsGame game = plugin.getGameHandler().getGame(player.getUniqueId());
        if (game == null || game.hasStarted())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        SkyWarsGame game = plugin.getGameHandler().getGame(player.getUniqueId());
        if (game == null || game.hasStarted())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (!(event.getEntity() instanceof Player player))
            return;

        SkyWarsGame game = plugin.getGameHandler().getGame(player.getUniqueId());
        if (game == null || game.hasStarted())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        SkyWarsGame game = plugin.getGameHandler().getGame(player.getUniqueId());
        if (game == null || game.hasStarted())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player))
            return;

        SkyWarsGame game = plugin.getGameHandler().getGame(player.getUniqueId());
        if (game == null || game.hasStarted())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player player))
            return;

        SkyWarsGame game = plugin.getGameHandler().getGame(player.getUniqueId());
        if (game == null || game.hasStarted())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        SkyWarsGame game = plugin.getGameHandler().getGame(player.getUniqueId());
        if (game == null || game.hasStarted())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        SkyWarsGame game = plugin.getGameHandler().getGame(player.getUniqueId());
        if (game == null || game.hasStarted())
            return;

        if (event.getAction() != Action.RIGHT_CLICK_AIR || event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        if (ALLOWED_INTERACTION.contains(event.getMaterial()))
            return;

        event.setCancelled(true);
    }

}
