package gg.scala.skywars.config

import gg.scala.skywars.game.state.GameTeamType
import gg.scala.skywars.game.state.GameType

object LimboConfig {
    var SETTINGS: gg.scala.skywars.config.LimboConfig.Settings = gg.scala.skywars.config.LimboConfig.Settings()

    class Settings {
        var minPlayers: Int = 2
        var maxPlayers: Int = 12
        var serverStartPlayers: Int = 2
        var gameType: GameType = GameType.NORMAL
        var teamType: GameTeamType = GameTeamType.SOLO
    }
}
