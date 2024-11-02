package gg.pixelarcade.skywars.game

import gg.pixelarcade.skywars.chests.type.RefillType
import gg.pixelarcade.skywars.game.state.GameState
import gg.pixelarcade.skywars.game.state.GameTeamType
import gg.pixelarcade.skywars.game.state.GameType
import lombok.Data
import java.util.*

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.game
 * @date 26/09/2023 / 20:43
 */
@Data
class Game(val gameType: GameType) {
    val id: UUID = UUID.randomUUID()

    var winner: UUID? = null
    var gameTeamType: GameTeamType? = null

    val spectators: Set<UUID> = HashSet()
    val players: Set<UUID> = HashSet()
    val alivePlayers: Set<UUID> = HashSet()

    val kills: Map<UUID, Int> = HashMap()

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
