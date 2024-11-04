package gg.pixelarcade.skywars.ability.type

import org.bukkit.event.player.PlayerInteractEvent

/**
 * @author alfie
 * @project Skywars
 * @date 03/10/2023 / 06:02
 */
interface Interact {
    fun onPlayerInteract(event: PlayerInteractEvent): Boolean
}
