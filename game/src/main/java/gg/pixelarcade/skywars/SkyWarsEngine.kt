package gg.pixelarcade.skywars

import gg.pixelarcade.skywars.adapter.SkyWarsNametagAdapter
import gg.pixelarcade.skywars.adapter.SkyWarsVisibilityAdapter
import gg.pixelarcade.skywars.profile.SkyWarsProfile
import gg.pixelarcade.skywars.adapter.SkywarsScoreboardAdapter
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.information.CgsGameGeneralInfo
import gg.scala.cgs.common.information.mode.CgsGameMode
import gg.scala.cgs.common.player.scoreboard.CgsGameScoreboardRenderer
import gg.scala.cgs.common.snapshot.CgsGameSnapshot
import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.lemon.util.CubedCacheUtil
import net.evilblock.cubed.util.CC
import kotlin.properties.Delegates

/**
 * @date 11/4/2024
 * @author Moose1301
 */
class SkyWarsEngine(
    plugin: ExtendedScalaPlugin,
    gameInfo: CgsGameGeneralInfo,
    gameMode: CgsGameMode
) : CgsGameEngine<SkyWarsProfile>(
    plugin, gameInfo, gameMode,
    SkyWarsProfile::class
) {
    companion object {
        @JvmStatic
        var INSTANCE by Delegates.notNull<SkyWarsEngine>()
    }

    override fun getScoreboardRenderer() = SkywarsScoreboardAdapter

    override fun getVisibilityAdapter() = SkyWarsVisibilityAdapter
    override fun getNametagAdapter() = SkyWarsNametagAdapter

    override fun getGameSnapshot(): CgsGameSnapshot {
        return object : CgsGameSnapshot {
            override fun getExtraInformation(): List<String> {
                val topKills = mutableListOf<String>()

                val sortedKills =
                    SkyWarsGame.gameInstance.gameHandler.game.kills.entries.sortedByDescending { it.value }

                for (i in 0 until 3.coerceAtMost(sortedKills.size)) {
                    val (key, value) = sortedKills[i]
                    topKills.add("  ${CC.YELLOW}#${i + 1} ${CC.GRAY}- ${CC.WHITE}${CubedCacheUtil.fetchName(key)}${CC.GRAY} - ${CC.PRI}$value")
                }

                return topKills
            }
        }
    }
}