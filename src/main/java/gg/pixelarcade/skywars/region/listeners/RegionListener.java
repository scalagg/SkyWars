package gg.pixelarcade.skywars.region.listeners;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.item.Item;
import gg.pixelarcade.skywars.region.Region;
import gg.pixelarcade.skywars.region.RegionHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.region.listeners
 * @date 19/10/2023 / 16:00
 */

@RequiredArgsConstructor
public class RegionListener implements Listener {

    private final RegionHandler handler;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Region region = handler.getRegion(player.getLocation());
        if (region == null)
            return;

        if (!region.isEnabled() || !region.isBreakBlocks())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Region region = handler.getRegion(player.getLocation());
        if (region == null)
            return;

        if (!region.isEnabled() || !region.isPlaceBlocks())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        Region region = handler.getRegion(event.getBlock().getLocation());
        if (region == null)
            return;

        if (!region.isEnabled() || !region.isLeavesDecay())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player))
            return;

        Region region = handler.getRegion(player.getLocation());
        if (region == null)
            return;

        if (!region.isEnabled() || !region.isDamage())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onHungerLoss(FoodLevelChangeEvent event) {
        if (!(event.getEntity() instanceof Player player))
            return;

        Region region = handler.getRegion(player.getLocation());
        if (region == null)
            return;

        if (!region.isEnabled() || !region.isHunger())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        Region region = handler.getRegion(player.getLocation());
        if (region == null)
            return;

        if (!region.isEnabled() || !region.isDropItems())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        Region region = handler.getRegion(player.getLocation());
        if (region == null)
            return;

        if (!region.isEnabled() || !region.isPickupItems())
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Region region = handler.getRegion(player.getLocation());
        if (region == null)
            return;

        if (!region.isEnabled() || !region.isInteract())
            return;

        // allows hotbar items to be used
        for (Item item : Item.HOTBAR_ITEMS.get(player.getUniqueId()).values()) {
            if (item == null)
                continue;

            if (!player.getItemInHand().isSimilar(item.getItem(player)))
                continue;

            item.click(event.getAction());
        }

        event.setCancelled(true);
    }

}
