package gg.pixelarcade.skywars.chests

import gg.pixelarcade.skywars.chests.type.RefillType

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.chests
 * @date 17/10/2023 / 15:50
 */
abstract class ChestStage {
    abstract val refillType: RefillType
    abstract val spawnLoot: List<ChestItem?>?
    abstract val middleLoot: List<ChestItem?>?
}
