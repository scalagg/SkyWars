package gg.pixelarcade.skywars.game.listener

import gg.pixelarcade.skywars.SkyWarsGame
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerPickupItemEvent

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.game.listener
 * @date 08/10/2023 / 06:15
 */

class PreventionListener(private val plugin: SkyWarsGame) : Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        if (plugin.gameHandler.hasStarted()) return

        event.isCancelled = true
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        if (plugin.gameHandler.hasStarted()) return

        event.isCancelled = true
    }

    @EventHandler
    fun onFoodLevelChange(event: FoodLevelChangeEvent) {
        if (plugin.gameHandler.hasStarted()) return

        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerDropItem(event: PlayerDropItemEvent) {
        if (plugin.gameHandler.hasStarted()) return

        event.isCancelled = true
    }

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) {
        if (plugin.gameHandler.hasStarted()) return

        event.isCancelled = true
    }

    @EventHandler
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {
        if (plugin.gameHandler.hasStarted()) return

        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerPickupItem(event: PlayerPickupItemEvent) {
        if (plugin.gameHandler.hasStarted()) return

        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (plugin.gameHandler.hasStarted()) return

        if (event.action != Action.RIGHT_CLICK_AIR || event.action != Action.RIGHT_CLICK_BLOCK) return

        if (ALLOWED_INTERACTION.contains(event.material)) return

        event.isCancelled = true
    }

    companion object {
        private val ALLOWED_INTERACTION: List<Material> = listOf(
            Material.PAPER,
            Material.EMERALD,
            Material.BED
        )
    }
}
