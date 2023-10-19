package gg.pixelarcade.skywars.item;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author alfie
 * @project Skywars
 * @date 25/09/2023 / 22:24
 */

public abstract class Item {

    public static final ConcurrentHashMap<UUID, ConcurrentHashMap<String, Item>> HOTBAR_ITEMS = new ConcurrentHashMap<>();

    public Item(Player player) {
        if (!HOTBAR_ITEMS.containsKey(player.getUniqueId())) {
            HOTBAR_ITEMS.put(player.getUniqueId(), new ConcurrentHashMap<>());
        }

        HOTBAR_ITEMS.get(player.getUniqueId()).put(this.getClass().getSimpleName(), this);
    }

    public Item(Player player, String id) {
        if (!HOTBAR_ITEMS.containsKey(player.getUniqueId())) {
            HOTBAR_ITEMS.put(player.getUniqueId(), new ConcurrentHashMap<>());
        }

        HOTBAR_ITEMS.get(player.getUniqueId()).put(id, this);
    }

    public abstract ItemStack getItem(Player player);
    public abstract void click(Action action);

    public static void unregisterItem(Player player, String id) {
        if (!HOTBAR_ITEMS.containsKey(player.getUniqueId())) {
            return;
        }

        HOTBAR_ITEMS.get(player.getUniqueId()).remove(id);
    }
}
