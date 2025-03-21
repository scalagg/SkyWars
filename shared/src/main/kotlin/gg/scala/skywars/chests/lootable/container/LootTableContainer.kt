package gg.scala.skywars.chests.lootable.container

import gg.scala.skywars.cages.Cage
import gg.scala.skywars.chests.lootable.model.LootTable

/**
 * @date 11/11/2024
 * @author Moose1301
 */
data class LootTableContainer(
    val tables: MutableMap<String, LootTable> = mutableMapOf()
)
