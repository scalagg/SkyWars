package gg.scala.skywars.battlepass.weekly

import org.bukkit.Bukkit


object WeeklyChallengeService : Runnable {
    var checkNext: Boolean = false;
    var lobby = Bukkit.getPluginManager().getPlugin("ScGameLobby") != null;

    override fun run() {

        if (WeeklyChallengeHandler.getSession().hasExpired()) {
            if(!lobby && !checkNext) {
                checkNext = true;
                return;
            }
            checkNext = false;
            WeeklyChallengeHandler.refresh()
        }

    }

}