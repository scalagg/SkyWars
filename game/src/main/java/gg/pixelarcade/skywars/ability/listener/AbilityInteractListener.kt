package gg.pixelarcade.skywars.ability.listener

import gg.pixelarcade.skywars.SkyWarsGame
import gg.pixelarcade.skywars.ability.type.Interact

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import java.util.*

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.ability.listener
 * @date 03/10/2023 / 06:08
 */

class AbilityInteractListener(private val plugin: SkyWarsGame) : Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val item = event.item ?: return

        if (event.action != Action.RIGHT_CLICK_AIR && event.action != Action.RIGHT_CLICK_BLOCK) return

        if (item.itemMeta.displayName == null) return

        val displayName =
            ChatColor.stripColor(item.itemMeta.displayName.replace(" ".toRegex(), "").lowercase(Locale.getDefault()))
        val ability = plugin.abilityHandler.getAbilityById(displayName)
        if (ability !is Interact) return

        if (!ability.isSimilar(item)) return

        if (!ability.onPlayerInteract(event)) return

        ability.consume(player)
        event.isCancelled = true
    }
}
