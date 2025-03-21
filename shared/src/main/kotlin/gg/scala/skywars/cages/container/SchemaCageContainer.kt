package gg.scala.skywars.cages.container

import gg.scala.skywars.cages.SchemaCage

/**
 * @author GrowlyX
 * @since 8/24/2024
 */
data class SchemaCageContainer(
    val schemas: MutableMap<String, SchemaCage> = mutableMapOf()
)
