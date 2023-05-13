package xyz.refinedev.skywars.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.profile.hotbar.HotbarItem;
import xyz.refinedev.skywars.pxSkyWars;

@RequiredArgsConstructor
public
class HotbarListener implements Listener {
    private final pxSkyWars plugin;

    @EventHandler
    public void onItems(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if (isBuildMode(player)) return;

        if (event.getItem() == null ||
                event.getItem().getType() == Material.AIR ||
                !event.getItem().hasItemMeta() ||
                event.getItem().getItemMeta() == null) {
            return;
        }

        if (!event.getAction().name().contains("RIGHT")) return;

        HotbarItem item = plugin.getHotbarHandler().getHotbarItem(event.getItem());
        if (item == null) return;

        item.getOnClick().accept(player);
    }
    private boolean isBuildMode(Player player) {
        return player.hasMetadata("BuildMode");
    }

    @EventHandler
    public void onPlayerTryRemoveTntHelmet(InventoryInteractEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();

        if (isBuildMode(player)) return;

        ItemStack item = player.getItemInHand();

        HotbarItem hotbarItem = plugin.getHotbarHandler().getHotbarItem(item);
        if (hotbarItem == null) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        if (e.getInventory().getType() != InventoryType.CRAFTING)
            return;

        Player p = (Player) e.getPlayer();

        e.getInventory().setItem(0, null);
        e.getInventory().setItem(1, null);
        e.getInventory().setItem(2, null);
        e.getInventory().setItem(3, null);
        e.getInventory().setItem(4, null);
    }

}
