package gg.scala.skywars.chests.impl.insane

import gg.scala.skywars.chests.ChestStage
import gg.scala.skywars.chests.LootableChest
import gg.scala.skywars.chests.impl.insane.stages.FirstInsaneStage
import gg.scala.skywars.chests.impl.insane.stages.SecondInsaneStage
import gg.scala.skywars.chests.impl.insane.stages.ThirdInsaneStage
import gg.scala.skywars.game.state.GameType
import java.util.*

class InsaneChest : LootableChest() {
    override val gameType: GameType
        get() = GameType.INSANE

    override val stages: List<ChestStage>
        get() = listOf(
            FirstInsaneStage(),
            SecondInsaneStage(),
            ThirdInsaneStage()
        )
}
