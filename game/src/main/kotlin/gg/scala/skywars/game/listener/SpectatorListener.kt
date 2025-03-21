package gg.scala.skywars.game.listener

import gg.scala.skywars.SkyWarsGame

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerPickupItemEvent

/**
 * @author alfie
 * @project Skywars / gg.scala.game.game.listener
 * @date 05/10/2023 / 03:30
 */
class SpectatorListener(private val plugin: SkyWarsGame) : Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val player = event.player

        event.isCancelled = plugin.gameHandler.isSpectating(player.uniqueId)
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        val player = event.player

        event.isCancelled = plugin.gameHandler.isSpectating(player.uniqueId)
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player

        event.isCancelled = plugin.gameHandler.isSpectating(player.uniqueId)
    }

    @EventHandler
    fun onDamage(event: EntityDamageEvent) {
        if (event.entity !is Player) return

        event.isCancelled = plugin.gameHandler.isSpectating(event.entity.uniqueId)
    }

    @EventHandler
    fun onFoodLevelChange(event: FoodLevelChangeEvent) {
        if (event.entity !is Player) return

        event.isCancelled = plugin.gameHandler.isSpectating(event.entity.uniqueId)
    }

    @EventHandler
    fun onPlayerDrop(event: PlayerDropItemEvent) {
        val player = event.player

        event.isCancelled = plugin.gameHandler.isSpectating(player.uniqueId)
    }

    @EventHandler
    fun onPlayerPickup(event: PlayerPickupItemEvent) {
        val player = event.player

        event.isCancelled = plugin.gameHandler.isSpectating(player.uniqueId)
    }
}
