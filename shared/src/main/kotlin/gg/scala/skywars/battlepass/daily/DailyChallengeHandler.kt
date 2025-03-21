package gg.scala.skywars.battlepass.daily


import gg.scala.skywars.battlepass.BattlePassHandler
import gg.scala.skywars.battlepass.daily.sync.DailyChallengeDataSync
import org.bukkit.Bukkit

object DailyChallengeHandler {

    private var session: DailyChallengeSession = DailyChallengeDataSync.cached();

    fun refresh() {
        if (Bukkit.getPluginManager().isPluginEnabled("ScGameLobby") && DailyChallengeDataSync.cached().hasExpired()) {
            DailyChallengeDataSync.sync(DailyChallengeSession())
        }

        session = DailyChallengeDataSync.cached();


        Bukkit.broadcastMessage("${BattlePassHandler.PREFIX}New daily challenges have been generated!")
    }

    fun getSession(): DailyChallengeSession {
        return session
    }

}