package gg.pixelarcade.skywars

import gg.pixelarcade.skywars.admin.commands.AdminExp
import gg.pixelarcade.skywars.items.*
import gg.pixelarcade.skywars.leaderboard.LeaderboardHologramHandler
import gg.pixelarcade.skywars.leaderboard.LeaderboardNPCsHandler
import gg.pixelarcade.skywars.listeners.LobbyListener
import gg.pixelarcade.skywars.listeners.QuestListener
import gg.pixelarcade.skywars.music.MusicHandler
import gg.pixelarcade.skywars.music.commands.MusicCommand
import gg.pixelarcade.skywars.profile.PersonalStatsNPCHandler
import gg.pixelarcade.skywars.queue.QueueNPCsHandler
import gg.pixelarcade.skywars.region.RegionHandler
import gg.pixelarcade.skywars.region.commands.RegionCommands
import gg.pixelarcade.skywars.region.listeners.RegionListener
import gg.pixelarcade.skywars.region.selection.listeners.RegionSelectionListener
import gg.pixelarcade.skywars.util.PlayerUtil
import gg.scala.commons.ExtendedScalaPlugin
import net.evilblock.cubed.util.bukkit.Tasks.delayed
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.function.Consumer

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 04:31
 */

class SkyWarsLobby(plugin: ExtendedScalaPlugin) : SkyWarsShared(plugin) {
    lateinit var musicHandler: MusicHandler
    lateinit var regionHandler: RegionHandler
    lateinit var leaderboardNPCsHandler: LeaderboardNPCsHandler

    lateinit var queueNPCsHandler: QueueNPCsHandler

    lateinit var leaderboardHologramHandler: LeaderboardHologramHandler
    lateinit var personalStatsNPCHandler: PersonalStatsNPCHandler

    override fun init() {
        lobbyInstance = this

        regionHandler = RegionHandler()
        musicHandler = MusicHandler(File(SkyWarsLobbyPlugin.instance.dataFolder, "music"))
        leaderboardNPCsHandler = LeaderboardNPCsHandler()

        queueNPCsHandler = QueueNPCsHandler()

        leaderboardHologramHandler = LeaderboardHologramHandler()
        personalStatsNPCHandler = PersonalStatsNPCHandler()


        listOf(
            RegionListener(regionHandler),
            RegionSelectionListener(regionHandler),
            LobbyListener(this),
            QuestListener()
        ).forEach(Consumer { listener: Listener? ->
            Bukkit.getPluginManager().registerEvents(listener, SkyWarsLobbyPlugin.instance)
        })

        commandHandler.registerCommand(MusicCommand())
        commandHandler.registerCommand(RegionCommands())
        commandHandler.registerCommand(AdminExp())

        plugin.server.messenger.registerOutgoingPluginChannel(plugin, "BungeeCord")

        Bukkit.getWorlds()[0].entities
            .forEach(Consumer { it: Entity ->
                if (it is Player) return@Consumer
                it.remove()
            })
    }

    override fun stop() {
        leaderboardNPCsHandler.destroy()

        queueNPCsHandler.destroy()

        leaderboardHologramHandler.destroy()
        personalStatsNPCHandler.destroy()
    }

    fun giveItems(player: Player) {
        PlayerUtil.resetPlayer(player)

        // this is so weird but otherwise last player to die doesn't get items for some reason :/
        delayed(2L, Runnable {
            player.inventory.setItem(0, ProfileItem(player).getItem(player))
            player.inventory.setItem(8, KitEditorItem(player).getItem(player))
        })
    }

    companion object {
        lateinit var lobbyInstance: SkyWarsLobby
    }
}