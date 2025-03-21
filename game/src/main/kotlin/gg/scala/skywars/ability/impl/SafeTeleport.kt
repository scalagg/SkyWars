package gg.scala.skywars.ability.impl

import gg.scala.skywars.SkyWarsGame
import gg.scala.skywars.ability.Ability
import gg.scala.skywars.ability.type.Interact
import gg.scala.skywars.game.event.GameEndEvent
import gg.scala.skywars.util.Tuple
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.bukkit.Tasks.async
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.ItemStack
import java.util.*
import java.util.function.Consumer

/**
 * @author alfie
 * @project Skywars / gg.scala.game.ability.impl
 * @date 17/10/2023 / 17:06
 */
class SafeTeleport : Ability(), Interact {
    private val lastLocation: MutableMap<UUID, _root_ide_package_.gg.scala.skywars.util.Tuple<Location, Long>> = HashMap()

    override val name: String
        get() = Color.translate("&d&lSafe Teleport")

    override fun getItem(): ItemStack {
        return ItemBuilder(Material.NETHER_STAR)
            .name(name)
            .addToLore(Color.translate("&7Click to teleport where you were last touching the ground."))
            .addToLore(" ")
            .addToLore(Color.translate("&e&lNOTE: &fWe store your location every 15 seconds."))
            .build()
    }

    override fun onPlayerInteract(event: PlayerInteractEvent): Boolean {
        val player = event.player
        if (!lastLocation.containsKey(player.uniqueId)
            || lastLocation[player.uniqueId]!!.value < System.currentTimeMillis()
        ) {
            player.sendMessage(Color.translate("&c&lERROR: &fCould not find a safe location to teleport to."))
            return false
        }

        player.sendMessage(Color.translate("&a&lSUCCESS: &fTeleported to your last safe location."))
        player.teleport(lastLocation[player.uniqueId]!!.key)
        player.fallDistance = 0f
        return true
    }

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player
        val from = event.from
        val to = event.to

        if (from.blockX == to.blockX && from.blockY == to.blockY && from.blockZ == to.blockZ) return

        if (SkyWarsGame.gameInstance.gameHandler.isSpectating(player.uniqueId)
            || !SkyWarsGame.gameInstance.gameHandler.hasStarted()
        ) return

        if (!player.isOnGround) return

        if (lastLocation.containsKey(player.uniqueId)
            && lastLocation[player.uniqueId]!!.value > System.currentTimeMillis()
        ) return

        lastLocation[player.uniqueId] = _root_ide_package_.gg.scala.skywars.util.Tuple(
            player.location,
            System.currentTimeMillis() + 1000L * SAFE_TELEPORT_DURATION
        )
    }

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        lastLocation.remove(event.entity.uniqueId)
    }

    @EventHandler
    fun onGameEnd(event: GameEndEvent) {
        async { event.game.players.forEach(Consumer { key: UUID -> lastLocation.remove(key) }) }
    }

    companion object {
        private const val SAFE_TELEPORT_DURATION = 15
    }
}
