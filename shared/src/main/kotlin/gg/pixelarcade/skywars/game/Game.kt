package gg.pixelarcade.skywars.game

import gg.pixelarcade.skywars.chests.type.RefillType
import gg.pixelarcade.skywars.game.state.GameState
import gg.pixelarcade.skywars.game.state.GameTeamType
import gg.pixelarcade.skywars.game.state.GameType
import java.util.*

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.game
 * @date 26/09/2023 / 20:43
 */

class Game(val gameType: GameType, val gameTeamType: GameTeamType) {
    val id: UUID = UUID.randomUUID()

    var winner: UUID? = null

    val spectators: MutableSet<UUID> = HashSet()
    val players: MutableSet<UUID> = HashSet()
    val alivePlayers: MutableSet<UUID> = HashSet()

    val kills: MutableMap<UUID, Int> = HashMap()

    var refillType = RefillType.FIRST
    var state = GameState.WAITING

    var startedAt: Date? = null
    var endedAt: Date? = null

    var forceStarted = false
    var active = false

    val isRanked: Boolean
        get() = this.gameType == GameType.RANKED

    companion object {
        const val SECOND_REFILL_DELAY: Int = 20 * 60 * 3
        const val THIRD_REFILL_DELAY: Int = 20 * 60 * 5
        const val SUDDEN_DEATH_COMMENCES: Int = 20 * 60 * 10
    }
}
