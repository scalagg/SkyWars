package gg.scala.skywars

import gg.scala.skywars.SkyWarsShared
import gg.scala.commons.ExtendedScalaPlugin

import net.evilblock.cubed.scoreboard.ScoreboardHandler
import gg.scala.skywars.config.LimboConfig
import gg.scala.skywars.handlers.LobbyHandler
import gg.scala.skywars.listeners.LobbyListener
import gg.scala.skywars.listeners.QueueListener
import gg.scala.skywars.scoreboard.ScoreboardAdapter
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import java.util.function.Consumer


class Limbo(plugin: ExtendedScalaPlugin) : SkyWarsShared(plugin) {
    lateinit var limboConfig: gg.scala.skywars.config.LimboConfig
    lateinit var lobbyHandler: LobbyHandler

    override fun init() {
        gg.scala.skywars.Limbo.Companion.lobbyInstance = this

        limboConfig = gg.scala.skywars.config.LimboConfig
        lobbyHandler = LobbyHandler()

        listOf(
            LobbyListener(this),
            gg.scala.skywars.listeners.QueueListener()
        ).forEach(Consumer { listener: Listener? ->
            Bukkit.getPluginManager().registerEvents(listener, gg.scala.skywars.LimboPlugin.Companion.instance)
        })

        ScoreboardHandler.configure(ScoreboardAdapter(this))

        gg.scala.skywars.LimboPlugin.Companion.instance.server.messenger.registerOutgoingPluginChannel(
            gg.scala.skywars.LimboPlugin.Companion.instance,
            "BungeeCord"
        )
    }

    override fun stop() {
        //stop method
    } // give items

    companion object {
        lateinit var lobbyInstance: gg.scala.skywars.Limbo
    }
}
