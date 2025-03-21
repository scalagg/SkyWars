package gg.scala.skywars.handlers

import gg.scala.skywars.game.state.GameTeamType
import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.handlers.task.CountdownTask
import gg.scala.skywars.util.LimboState
import gg.scala.skywars.util.StartingState
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