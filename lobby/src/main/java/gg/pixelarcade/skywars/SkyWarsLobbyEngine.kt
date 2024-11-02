package gg.pixelarcade.skywars

import gg.pixelarcade.skywars.profile.SkyWarsProfile
import gg.scala.cgs.common.information.CgsGameGeneralInfo
import gg.scala.cgs.common.instance.CgsServerInstance
import gg.scala.cgs.lobby.gamemode.CgsGameLobby
import gg.scala.cgs.lobby.leaderboard.CgsLobbyRankingEntry
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.scoreboard.ScoreboardAdapter
import org.bukkit.entity.Player

/**
 * @date 11/1/2024
 * @author Moose1301
 */
object SkyWarsLobbyEngine : CgsGameLobby<SkyWarsProfile>(
    SkyWarsProfile::class
)
{
    override fun getFormattedButton(info: CgsServerInstance, player: Player): Button {
        TODO("Not yet implemented")
    }

    override fun getGameInfo(): CgsGameGeneralInfo {
        return SkyWarsGameInfo()
    }

    override fun getGameModeButtons(): Map<Int, Button> {
        TODO("Not yet implemented")
    }

    override fun getRankingEntries(): Collection<CgsLobbyRankingEntry> {
        TODO("Not yet implemented")
    }

    override fun getScoreboardAdapter(): ScoreboardAdapter {
        TODO("Not yet implemented")
    }


}
