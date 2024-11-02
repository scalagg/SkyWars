package gg.pixelarcade.skywars

import gg.scala.cgs.lobby.gamemode.CgsGameLobby
import gg.scala.commons.ExtendedScalaPlugin
import lombok.Getter
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 02:28
 */

class SkyWarsLobbyPlugin : ExtendedScalaPlugin() {
    override fun enable() {
        instance = this

        SkyWarsLobby(this)

        val lobby = SkyWarsLobbyEngine
        lobby.resourcePlugin = this
        CgsGameLobby.INSTANCE = lobby

    }

    override fun disable() {
        SkyWarsShared.instance.stop()
    }


    companion object {

        lateinit var instance: SkyWarsLobbyPlugin
    }
}
