package gg.pixelarcade.skywars.chests.impl.ranked

import gg.pixelarcade.skywars.chests.ChestStage
import gg.pixelarcade.skywars.chests.LootableChest
import gg.pixelarcade.skywars.chests.impl.ranked.stages.FirstRankedStage
import gg.pixelarcade.skywars.chests.impl.ranked.stages.SecondRankedStage
import gg.pixelarcade.skywars.chests.impl.ranked.stages.ThirdRankedStage
import gg.pixelarcade.skywars.game.state.GameType
import java.util.*

class RankedChest : LootableChest() {
    override val gameType: GameType
        get() = GameType.RANKED

    override val stages: List<ChestStage>
        get() = listOf(
            FirstRankedStage(),
            SecondRankedStage(),
            ThirdRankedStage()
        )
}
