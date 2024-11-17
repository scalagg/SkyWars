package gg.pixelarcade.skywars.arena.container

import gg.pixelarcade.skywars.arena.model.SkyWarsArena

/**
 * @date 11/11/2024
 * @author Moose1301
 */
data class ArenaContainer(
    val arenas: MutableMap<String, SkyWarsArena> = mutableMapOf()
)
