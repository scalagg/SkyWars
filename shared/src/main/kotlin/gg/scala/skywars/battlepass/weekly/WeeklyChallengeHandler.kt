package gg.scala.skywars.battlepass.weekly


import gg.scala.skywars.battlepass.BattlePassHandler
import gg.scala.skywars.battlepass.weekly.sync.WeeklyChallengeDataSync
import org.bukkit.Bukkit

object WeeklyChallengeHandler {

    private var session: WeeklyChallengeSession = WeeklyChallengeDataSync.cached();

    fun refresh() {
        if (Bukkit.getPluginManager().isPluginEnabled("ScGameLobby") && WeeklyChallengeDataSync.cached().hasExpired()) {
            WeeklyChallengeDataSync.sync(WeeklyChallengeSession())
        }

        session = WeeklyChallengeDataSync.cached();


        Bukkit.broadcastMessage("${BattlePassHandler.PREFIX}New weekly challenges have been generated!")
    }

    fun getSession(): WeeklyChallengeSession {
        return session
    }

}