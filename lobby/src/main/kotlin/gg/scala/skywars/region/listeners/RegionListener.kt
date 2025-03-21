package gg.scala.skywars.region.listeners

import gg.scala.skywars.item.Item
import gg.scala.skywars.region.Region
import gg.scala.skywars.region.RegionHandler
import gg.scala.commons.annotations.Listeners
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.block.LeavesDecayEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerPickupItemEvent

/**
 * @author alfie
 * @project Skywars / gg.scala.game.region.listeners
 * @date 19/10/2023 / 16:00
 */
@Listeners
object RegionListener : Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val player = event.player
        if (player.hasMetadata("BuildMode")) return

        val region = RegionHandler.getRegion(player.location) ?: return

        if (!region.enabled || !region.breakBlocks) return

        event.isCancelled = true
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        val player = event.player
        if (player.hasMetadata("BuildMode")) return

        val region = RegionHandler.getRegion(player.location) ?: return

        if (!region.enabled || !region.placeBlocks) return

        event.isCancelled = true
    }

    @EventHandler
    fun onLeavesDecay(event: LeavesDecayEvent) {
        val region = RegionHandler.getRegion(event.block.location) ?: return

        if (!region.enabled || !region.leavesDecay) return

        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerDamage(event: EntityDamageEvent) {
        if (event.entity !is Player) return

        val region: Region = RegionHandler.getRegion(event.entity.location) ?: return

        if (!region.enabled || !region.damage) return

        event.isCancelled = true
    }

    @EventHandler
    fun onHungerLoss(event: FoodLevelChangeEvent) {
        if (event.entity !is Player) return

        val region: Region = RegionHandler.getRegion(event.entity.location) ?: return

        if (!region.enabled || !region.hunger) return

        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerDropItem(event: PlayerDropItemEvent) {
        val player = event.player
        val region = RegionHandler.getRegion(player.location)
        if (player.hasMetadata("BuildMode")) return

        if (region == null) return

        if (!region.enabled || !region.dropItems) return

        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerPickupItem(event: PlayerPickupItemEvent) {
        val player = event.player
        val region = RegionHandler.getRegion(player.location)
        if (player.hasMetadata("BuildMode")) return

        if (region == null) return

        if (!region.enabled || !region.pickupItems) return

        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        if (player.hasMetadata("BuildMode")) return

        val region = RegionHandler.getRegion(player.location) ?: return

        if (!region.enabled || !region.interact) return

        // allows hotbar items to be used
        for (item in Item.HOTBAR_ITEMS[player.uniqueId]!!.values) {
            if (item == null) continue

            if (!player.itemInHand.isSimilar(item.getItem(player))) continue

            item.click(event.action)
        }

        event.isCancelled = true
    }
}
