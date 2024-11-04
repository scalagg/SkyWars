package net.mccade.skywars.handlers

import gg.pixelarcade.skywars.game.state.GameTeamType
import gg.pixelarcade.skywars.game.state.GameType
import net.mccade.skywars.handlers.task.CountdownTask
import net.mccade.skywars.util.LimboState
import net.mccade.skywars.util.StartingState
import java.util.*


class Lobby(val gameType: GameType, val gameTeamType: GameTeamType) {
    val id: UUID = UUID.randomUUID()

    var limboState = LimboState.STALE
    var startingState = StartingState.WAITING
    var serverName = ""
    var serverDeployed = false
    var countdown: CountdownTask? = null

    val players: MutableSet<UUID> = HashSet()

    val isRanked: Boolean
        get() = this.gameType == GameType.RANKED
}