package gg.scala.skywars.battlepass.daily

import org.bukkit.Bukkit


object DailyChallengeService : Runnable {
    var checkNext: Boolean = false;
    var lobby = Bukkit.getPluginManager().getPlugin("ScGameLobby") != null;

    override fun run() {

        if (DailyChallengeHandler.getSession().hasExpired()) {
            if(!lobby && !checkNext) {
                checkNext = true;
                return;
            }
            checkNext = false;
            DailyChallengeHandler.refresh()
        }

    }

}