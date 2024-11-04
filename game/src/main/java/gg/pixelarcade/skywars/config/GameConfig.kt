package gg.pixelarcade.skywars.config

import gg.pixelarcade.skywars.game.state.GameType
import org.bukkit.Bukkit
import org.bukkit.Location

/**
 * This config file needs to be adjusted for each map
 * @Param GAMETYPE should be set via the deployer
 */
object GameConfig {
    var SETTINGS: Settings = Settings()


    class Settings {
        var name: String = "world"
        var type: GameType = GameType.NORMAL

        var firstCorner: Location = Location(Bukkit.getWorld(name), 62094.0, 20.0, 113.0)
        var secondCorner: Location = Location(Bukkit.getWorld(name), 61916.0, 74.0, -64.0)
    }
}
