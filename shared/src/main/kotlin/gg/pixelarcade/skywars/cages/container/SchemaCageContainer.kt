package gg.pixelarcade.skywars.cages.container

import gg.pixelarcade.skywars.cages.SchemaCage

/**
 * @author GrowlyX
 * @since 8/24/2024
 */
data class SchemaCageContainer(
    val schemas: MutableMap<String, SchemaCage> = mutableMapOf()
)
