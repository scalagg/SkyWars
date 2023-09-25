package xyz.refinedev.skywars.item.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.item.Item;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.item.listeners
 * @date 25/09/2023 / 22:30
 */

public class ItemListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Item.HOTBAR_ITEMS.remove(event.getPlayer().getUniqueId());
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Map<String, Item> items = Item.HOTBAR_ITEMS.getOrDefault(player.getUniqueId(),
                new ConcurrentHashMap<>());

        if (player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR)
            return;

        if (event.getAction() == Action.PHYSICAL)
            return;

        for (Item item : items.values()) {
            if (!item.getItem(player).isSimilar(player.getItemInHand()))
                continue;

            event.setCancelled(true);
            item.click(event.getAction());
        }
    }
}
