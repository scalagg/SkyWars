package gg.pixelarcade.skywars.leaderboard.ranking

import gg.scala.cgs.lobby.leaderboard.CgsLobbyRankingEntry

object TopKillsRankingEntry : CgsLobbyRankingEntry
{
    override fun getDisplay() = "Level"
    override fun getId() = "levels"
    override fun getStatLabel() = "levels"

}
