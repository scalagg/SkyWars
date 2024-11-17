package gg.pixelarcade.skywars

import gg.pixelarcade.skywars.arena.ArenaManager
import gg.pixelarcade.skywars.item.listeners.ItemListener
import gg.pixelarcade.skywars.kit.KitHandler
import gg.pixelarcade.skywars.leaderboard.LeaderboardHandler
import gg.pixelarcade.skywars.perk.PerkHandler
import gg.pixelarcade.skywars.profile.SkyWarsProfile
import gg.pixelarcade.skywars.quests.QuestHandler
import gg.scala.cgs.common.player.handler.CgsPlayerHandler
import gg.scala.cgs.common.player.handler.CgsPlayerHandler.find
import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.commons.acf.PaperCommandManager
import gg.scala.store.storage.type.DataStoreStorageType

import net.evilblock.cubed.ScalaCommonsSpigot
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import java.util.function.Consumer

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 02:34
 */

abstract class SkyWarsShared(val plugin: ExtendedScalaPlugin) {
    val kitHandler: KitHandler
    val perkHandler: PerkHandler
    val questHandler: QuestHandler
    val leaderboardHandler: LeaderboardHandler
    val arenaManager: ArenaManager

    val commandHandler: PaperCommandManager

    init {
        instance = this

        commandHandler = ScalaCommonsSpigot.instance.commandManager

        kitHandler = KitHandler()
        perkHandler = PerkHandler()
        questHandler = QuestHandler()
        leaderboardHandler = LeaderboardHandler()
        arenaManager = ArenaManager()

        listOf(ItemListener()).forEach(Consumer { listener: ItemListener? ->
            Bukkit.getPluginManager().registerEvents(
                listener,
                plugin
            )
        })

        Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord")

        init()
    }

    fun getProfile(player: Player): SkyWarsProfile {
        return getProfile(player.uniqueId)
    }

    fun getProfile(playerId: UUID): SkyWarsProfile {
        val stats = CgsPlayerHandler.statistics[playerId];
        if(stats != null || Bukkit.isPrimaryThread())
            return stats as SkyWarsProfile

        val gameSpecificStatistics = CgsPlayerHandler.statsLayer
            .load(playerId, DataStoreStorageType.MONGO)
            .join()

        return gameSpecificStatistics as SkyWarsProfile
    }

    abstract fun init()
    abstract fun stop()


    companion object {
        lateinit var instance: SkyWarsShared
    }
}
