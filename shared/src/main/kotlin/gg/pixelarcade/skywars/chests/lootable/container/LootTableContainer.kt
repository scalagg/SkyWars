package gg.pixelarcade.skywars.chests.lootable.container

import gg.pixelarcade.skywars.cages.Cage
import gg.pixelarcade.skywars.chests.lootable.model.LootTable

/**
 * @date 11/11/2024
 * @author Moose1301
 */
data class LootTableContainer(
    val tables: MutableMap<String, LootTable> = mutableMapOf()
)
