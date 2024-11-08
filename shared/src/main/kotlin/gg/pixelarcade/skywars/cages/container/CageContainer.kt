package gg.pixelarcade.skywars.cages.container

import gg.pixelarcade.skywars.cages.Cage

/**
 * @author GrowlyX
 * @since 8/24/2024
 */
data class CageContainer(
    val cages: MutableMap<String, Cage> = mutableMapOf()
)
