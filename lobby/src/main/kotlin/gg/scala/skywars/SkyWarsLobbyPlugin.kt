package gg.scala.skywars

import gg.scala.cgs.lobby.gamemode.CgsGameLobby
import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.commons.core.plugin.*
import java.util.*

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 02:28
 */
@Plugin(
    name = "SkyWars-Lobby",
    version = "%remote%/%branch%/%id%"
)
@PluginAuthor("Scala")
@PluginWebsite("https://scala.gg")
@PluginDependencyComposite(
    PluginDependency("scala-commons"),
    PluginDependency("Lemon"),
    PluginDependency("ScGameLobby"),
    PluginDependency("Apollo-Bukkit"),
    PluginDependency("cloudsync", soft = true),
)
class SkyWarsLobbyPlugin : ExtendedScalaPlugin() {
    override fun enable() {
        super.enable()
        instance = this

        SkyWarsLobby(this)

        val delay = 10000L
        val timer = Timer()
        timer.scheduleAtFixedRate(TimerTask() {

        }, delay, 5000L)
        timer.scheduleAtFixedRate(GenerateTeamGameServerTask(), delay, 5000L)

        val lobby = SkyWarsLobbyEngine
        lobby.resourcePlugin = this
        CgsGameLobby.INSTANCE = lobby

    }

    override fun disable() {
        SkyWarsShared.instance.shutdown()
        super.disable()
    }


    companion object {

        lateinit var instance: SkyWarsLobbyPlugin
    }
}
