package gg.scala.skywars

import gg.scala.skywars.items.*
import gg.scala.skywars.leaderboard.LeaderboardHologramHandler
import gg.scala.skywars.leaderboard.LeaderboardNPCsHandler
import gg.scala.skywars.music.MusicHandler
import gg.scala.skywars.profile.PersonalStatsNPCHandler
import gg.scala.skywars.queue.QueueNPCsHandler
import gg.scala.skywars.region.RegionHandler
import gg.scala.commons.ExtendedScalaPlugin
import net.evilblock.cubed.util.bukkit.Tasks.delayed
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
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

        regionHandler = RegionHandler
        musicHandler = MusicHandler(File(SkyWarsLobbyPlugin.instance.dataFolder, "music"))
//        leaderboardNPCsHandler = LeaderboardNPCsHandler()

        queueNPCsHandler = QueueNPCsHandler()

        leaderboardHologramHandler = LeaderboardHologramHandler()
        personalStatsNPCHandler = PersonalStatsNPCHandler()

        plugin.server.messenger.registerOutgoingPluginChannel(plugin, "BungeeCord")

        Bukkit.getWorlds()[0].entities
            .forEach(Consumer { it: Entity ->
                if (it is Player) return@Consumer
                it.remove()
            })
    }

    override fun stop() {
//        leaderboardNPCsHandler.destroy()

        queueNPCsHandler.destroy()

        leaderboardHologramHandler.destroy()
        personalStatsNPCHandler.destroy()
    }

    fun giveItems(player: Player) {
        _root_ide_package_.gg.scala.skywars.util.PlayerUtil.resetPlayer(player)

        // this is so weird but otherwise last player to die doesn't get items for some reason :/
        delayed(2L, Runnable {
            player.inventory.setItem(1, ProfileItem(player).getItem(player))
            player.inventory.setItem(2, KitEditorItem(player).getItem(player))
        })
    }

    companion object {
        lateinit var lobbyInstance: SkyWarsLobby
    }
}