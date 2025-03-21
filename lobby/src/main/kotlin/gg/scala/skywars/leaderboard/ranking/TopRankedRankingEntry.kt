package gg.scala.skywars.leaderboard.ranking

import gg.scala.cgs.lobby.leaderboard.CgsLobbyRankingEntry

/**
 * @author Topu
 * @date 3/22/2025
 */
object TopRankedRankingEntry : CgsLobbyRankingEntry
{
    override fun getDisplay() = "Ranked"
    override fun getId() = "ranked"
    override fun getStatLabel() = "ranked"

}
