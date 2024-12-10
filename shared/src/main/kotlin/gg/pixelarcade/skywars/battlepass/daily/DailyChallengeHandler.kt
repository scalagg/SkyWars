package gg.pixelarcade.skywars.battlepass.daily


import gg.pixelarcade.skywars.battlepass.BattlePassHandler
import gg.pixelarcade.skywars.battlepass.daily.sync.DailyChallengeDataSync
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
        return this.session
    }

}