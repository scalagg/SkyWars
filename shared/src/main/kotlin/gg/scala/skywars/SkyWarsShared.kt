package gg.scala.skywars

import gg.scala.skywars.arena.ArenaManager
import gg.scala.skywars.battlepass.BattlePassManager
import gg.scala.skywars.kit.KitHandler
import gg.scala.skywars.leaderboard.LeaderboardHandler
import gg.scala.skywars.perk.PerkHandler
import gg.scala.skywars.profile.SkyWarsProfile
import gg.scala.skywars.quests.QuestHandler
import gg.scala.cgs.common.player.handler.CgsPlayerHandler
import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.store.storage.type.DataStoreStorageType

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

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
    val battlePassManager: BattlePassManager

    init {
        instance = this

        kitHandler = KitHandler()
        perkHandler = PerkHandler()
        questHandler = QuestHandler()
        leaderboardHandler = LeaderboardHandler()
        arenaManager = ArenaManager()
        battlePassManager = BattlePassManager()

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
    fun shutdown() {
        stop()
        battlePassManager.shutdown()
    }

    abstract fun init()

    abstract fun stop()


    companion object {
        lateinit var instance: SkyWarsShared
    }
}
