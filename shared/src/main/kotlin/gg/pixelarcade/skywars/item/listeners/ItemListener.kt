package gg.pixelarcade.skywars.item.listeners

import gg.pixelarcade.skywars.item.Item
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.concurrent.ConcurrentHashMap

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.item.listeners
 * @date 25/09/2023 / 22:30
 */
class ItemListener : Listener {
    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        Item.HOTBAR_ITEMS.remove(event.player.uniqueId)
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val items: Map<String, Item> = Item.HOTBAR_ITEMS.getOrDefault(player.uniqueId, ConcurrentHashMap())
        if (player.itemInHand == null || player.itemInHand.type == Material.AIR) return

        if (event.action == Action.PHYSICAL) return

        for (item in items.values) {
            if (!item.getItem(player).isSimilar(player.itemInHand)) continue

            event.isCancelled = true
            item.click(event.action)
        }
    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        val items: Map<String, Item> = Item.HOTBAR_ITEMS.getOrDefault(player.uniqueId, ConcurrentHashMap())

        if (player.hasMetadata("BuildMode")) return

        for (item in items.values) {
            if (!item.getItem(player).isSimilar(player.itemInHand)) continue

            event.isCancelled = true
        }
    }
}
