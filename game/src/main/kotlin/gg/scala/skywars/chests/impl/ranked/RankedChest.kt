package gg.scala.skywars.chests.impl.ranked

import gg.scala.skywars.chests.ChestStage
import gg.scala.skywars.chests.LootableChest
import gg.scala.skywars.chests.impl.ranked.stages.FirstRankedStage
import gg.scala.skywars.chests.impl.ranked.stages.SecondRankedStage
import gg.scala.skywars.chests.impl.ranked.stages.ThirdRankedStage
import gg.scala.skywars.game.state.GameType
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
