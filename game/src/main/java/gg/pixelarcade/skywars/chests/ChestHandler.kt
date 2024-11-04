package gg.pixelarcade.skywars.chests

import gg.pixelarcade.skywars.chests.impl.casual.CasualChest
import gg.pixelarcade.skywars.chests.impl.insane.InsaneChest
import gg.pixelarcade.skywars.chests.impl.ranked.RankedChest
import gg.pixelarcade.skywars.game.state.GameType


/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.chests
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
