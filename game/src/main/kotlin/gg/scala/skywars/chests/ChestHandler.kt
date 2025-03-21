package gg.scala.skywars.chests

import gg.scala.skywars.chests.impl.casual.CasualChest
import gg.scala.skywars.chests.impl.insane.InsaneChest
import gg.scala.skywars.chests.impl.ranked.RankedChest
import gg.scala.skywars.game.state.GameType


/**
 * @author alfie
 * @project Skywars / gg.scala.game.chests
 * @date 27/09/2023 / 20:36
 */

class ChestHandler {
    private val chests: MutableList<LootableChest> = ArrayList()

    init {
        register(CasualChest())
        register(RankedChest())
        register(InsaneChest())
    }

    fun getChest(gameType: GameType): LootableChest? {
        for (chest in chests) {
            if (chest.gameType == gameType) return chest
        }

        return null
    }

    fun register(chest: LootableChest) {
        chests.add(chest)
    }
}
