package gg.pixelarcade.skywars.chests.impl.insane

import gg.pixelarcade.skywars.chests.ChestStage
import gg.pixelarcade.skywars.chests.LootableChest
import gg.pixelarcade.skywars.chests.impl.insane.stages.FirstInsaneStage
import gg.pixelarcade.skywars.chests.impl.insane.stages.SecondInsaneStage
import gg.pixelarcade.skywars.chests.impl.insane.stages.ThirdInsaneStage
import gg.pixelarcade.skywars.game.state.GameType
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
