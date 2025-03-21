package gg.scala.skywars.chests.impl.casual

import gg.scala.skywars.chests.ChestStage
import gg.scala.skywars.chests.LootableChest
import gg.scala.skywars.chests.impl.casual.stages.FirstCasualStage
import gg.scala.skywars.chests.impl.casual.stages.SecondCasualStage
import gg.scala.skywars.chests.impl.casual.stages.ThirdCasualStage
import gg.scala.skywars.game.state.GameType
import java.util.*

/**
 * @author alfie
 * @project Skywars / gg.scala.game.chests.impl.casual
 * @date 17/10/2023 / 16:04
 */
class CasualChest : LootableChest() {
    override val gameType: GameType
        get() = GameType.NORMAL

    override val stages: List<ChestStage>
        get() = listOf(
            FirstCasualStage(),
            SecondCasualStage(),
            ThirdCasualStage()
        )
}
