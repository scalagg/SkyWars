package gg.pixelarcade.skywars.battlepass.service


import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.battlepass.challenge.ChallengeHandler
import gg.pixelarcade.skywars.battlepass.daily.DailyChallengeHandler
import gg.pixelarcade.skywars.battlepass.weekly.WeeklyChallengeHandler
import org.bukkit.Bukkit
import org.bukkit.entity.Player

object BattlePassThread : Thread() {

    override fun run() {

        while (true) {

            Bukkit.getServer().onlinePlayers.forEach{
                this.check(it)
            }

            try {
                sleep(1000L)
            } catch (ex: InterruptedException) {
                ex.printStackTrace()
            }
        }

    }

    private fun check(player: Player) {

        val battlePass = SkyWarsShared.instance.getProfile(player.uniqueId).battlePass;

        if (!battlePass.requiresUpdate) {
            return
        }

        battlePass.requiresUpdate = false

        if (battlePass.isPremium()) {

            for (challenge in ChallengeHandler.getChallenges()) {

                if (challenge.daily) {
                    continue
                }

                if (battlePass.hasCompletedChallenge(player,challenge)) {
                    continue
                }

                if (challenge.meetsCompletionRequirements(player,battlePass)) {
                    challenge.onComplete(player,battlePass)
                }

            }
        }

        for (challenge in DailyChallengeHandler.getSession().getChallenges()) {

            if (!challenge.daily) {
                continue
            }

            if (battlePass.hasCompletedChallenge(player,challenge)) {
                continue
            }

            if (challenge.meetsCompletionRequirements(player,battlePass)) {
                challenge.onComplete(player,battlePass)
            }

        }
        for (challenge in WeeklyChallengeHandler.getSession().getChallenges()) {

            if (!challenge.weekly) {
                continue
            }

            if (battlePass.hasCompletedChallenge(player,challenge)) {
                continue
            }

            if (challenge.meetsCompletionRequirements(player,battlePass)) {
                challenge.onComplete(player,battlePass)
            }

        }
    }

}
