package gg.pixelarcade.skywars.battlepass.challenge.listener


import gg.pixelarcade.skywars.battlepass.BattlePassHandler
import gg.pixelarcade.skywars.battlepass.challenge.ChallengeHandler
import gg.pixelarcade.skywars.battlepass.challenge.impl.ExecuteCommandChallenge
import gg.pixelarcade.skywars.battlepass.daily.DailyChallengeHandler
import gg.pixelarcade.skywars.battlepass.weekly.WeeklyChallengeHandler
import gg.pixelarcade.skywars.game.event.GameEndEvent
import gg.pixelarcade.skywars.game.event.GameStartEvent
import gg.pixelarcade.skywars.game.event.PlayerKillEvent
import gg.pixelarcade.skywars.game.state.GameTeamType
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerCommandPreprocessEvent

object ChallengeListener : Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private fun onPlayerPreProcessCommand(event: PlayerCommandPreprocessEvent) {

        for (challenge in ChallengeHandler.getAllChallenges()) {
            if (challenge is ExecuteCommandChallenge) {
                if (event.message.startsWith("/${challenge.command}", ignoreCase = true)) {
                    DailyChallengeHandler.getSession().getProgress(event.player.uniqueId).executedCommand(challenge.command)
                    WeeklyChallengeHandler.getSession().getProgress(event.player.uniqueId).executedCommand(challenge.command)
                    BattlePassHandler.getBattlePassByPlayer(event.player).executedCommand(challenge.command)
                    break
                }
            }
        }

    }

    @EventHandler
    private fun onPlayerKill(event: PlayerKillEvent) {
        DailyChallengeHandler.getSession().getProgress(event.player.uniqueId).addKills(1)
        WeeklyChallengeHandler.getSession().getProgress(event.player.uniqueId).addKills(1)
    }

    @EventHandler
    fun onGameWin(event: GameEndEvent) {
        val game = event.game
        if (game.winner == null) return

        val player = Bukkit.getPlayer(game.winner) ?: return


        DailyChallengeHandler.getSession().getProgress(player.uniqueId).addWins(1)
        WeeklyChallengeHandler.getSession().getProgress(player.uniqueId).addWins(1)
    }

    @EventHandler
    fun onGameStart(event: GameStartEvent) {
        val game = event.game;

        game.players.forEach {
            DailyChallengeHandler.getSession().getProgress(it).addPlays(1)
            WeeklyChallengeHandler.getSession().getProgress(it).addPlays(1)
        }
    }

}