package net.mccade.skywars

import gg.pixelarcade.skywars.SkyWarsShared
import gg.scala.commons.ExtendedScalaPlugin

import net.evilblock.cubed.scoreboard.ScoreboardHandler
import net.mccade.skywars.config.LimboConfig
import net.mccade.skywars.handlers.LobbyHandler
import net.mccade.skywars.listeners.LobbyListener
import net.mccade.skywars.listeners.QueueListener
import net.mccade.skywars.scoreboard.ScoreboardAdapter
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import java.util.function.Consumer


class Limbo(plugin: ExtendedScalaPlugin) : SkyWarsShared(plugin) {
    lateinit var limboConfig: LimboConfig
    lateinit var lobbyHandler: LobbyHandler

    override fun init() {
        lobbyInstance = this

        limboConfig = LimboConfig
        lobbyHandler = LobbyHandler()

        listOf(
            LobbyListener(this),
            QueueListener()
        ).forEach(Consumer { listener: Listener? ->
            Bukkit.getPluginManager().registerEvents(listener, LimboPlugin.instance)
        })

        ScoreboardHandler.configure(ScoreboardAdapter(this))

        LimboPlugin.instance.server.messenger.registerOutgoingPluginChannel(
            LimboPlugin.instance,
            "BungeeCord"
        )
    }

    override fun stop() {
        //stop method
    } // give items

    companion object {
        lateinit var lobbyInstance: Limbo
    }
}
