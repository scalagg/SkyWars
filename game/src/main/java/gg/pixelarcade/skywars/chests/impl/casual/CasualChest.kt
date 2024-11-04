package gg.pixelarcade.skywars.chests.impl.casual

import gg.pixelarcade.skywars.chests.ChestStage
import gg.pixelarcade.skywars.chests.LootableChest
import gg.pixelarcade.skywars.chests.impl.casual.stages.FirstCasualStage
import gg.pixelarcade.skywars.chests.impl.casual.stages.SecondCasualStage
import gg.pixelarcade.skywars.chests.impl.casual.stages.ThirdCasualStage
import gg.pixelarcade.skywars.game.state.GameType
import java.util.*

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.chests.impl.casual
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
