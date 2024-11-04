package net.mccade.skywars.config

import gg.pixelarcade.skywars.game.state.GameTeamType
import gg.pixelarcade.skywars.game.state.GameType

object LimboConfig {
    var SETTINGS: Settings = Settings()

    class Settings {
        var minPlayers: Int = 2
        var maxPlayers: Int = 12
        var serverStartPlayers: Int = 2
        var gameType: GameType = GameType.NORMAL
        var teamType: GameTeamType = GameTeamType.SOLO
    }
}
