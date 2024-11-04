package gg.pixelarcade.skywars.leaderboard.ranking

import gg.scala.cgs.lobby.leaderboard.CgsLobbyRankingEntry

object TopWinsRankingEntry : CgsLobbyRankingEntry
{
    override fun getDisplay() = "Wins"
    override fun getId() = "wins"
    override fun getStatLabel() = "wins"

}
