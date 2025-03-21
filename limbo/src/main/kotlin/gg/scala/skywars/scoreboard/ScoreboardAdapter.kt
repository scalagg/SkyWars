package gg.scala.skywars.scoreboard

import net.evilblock.cubed.scoreboard.ScoreboardAdapter
import gg.scala.skywars.Limbo
import gg.scala.skywars.util.StartingState
import gg.scala.skywars.utils.ScoreboardTitleService
import org.bukkit.entity.Player
import java.text.SimpleDateFormat
import java.util.*

class ScoreboardAdapter(private val plugin: Limbo) :  ScoreboardAdapter() {

    override fun getTitle(player: Player): String {
        return ScoreboardTitleService.getCurrentTitle()
    }

    override fun getLines(board: LinkedList<String>, player: Player) {
        //-- Instance Variables

        //-- Date
        val dateFormat = SimpleDateFormat("MM/dd/yy")
        board.add("&7" + dateFormat.format(System.currentTimeMillis()))
        board.add("")

        //-- Player Count
        val currentPlayers = plugin.lobbyHandler.lobby.players.size
        val maxPlayers = plugin.lobbyHandler.lobby.gameTeamType.maxPlayers
        board.add("&fPlayers: &6$currentPlayers/$maxPlayers")
        board.add("")

        //-- Waiting
        if (plugin.lobbyHandler.lobby.startingState != StartingState.COUNTING) {
            board.add("&fWaiting...")
            board.add("")
        } else {
            board.add("&aStarting in: " + plugin.lobbyHandler.lobby.countdown?.countdown + "s")
            board.add("")
        }

        val badType = plugin.lobbyHandler.lobby.gameTeamType.name.lowercase(Locale.getDefault())
        val goodType = badType.substring(0, 1).uppercase(Locale.getDefault()) + badType.substring(1)

        //-- Map, Mode, Bracket if there is one
        board.add("&fType: &6&l$goodType")
        board.add("&fMode: " + "&6&l" + plugin.lobbyHandler.lobby.gameType.displayName)
        val gameType = plugin.lobbyHandler.lobby.gameType
        //        if (gameType == GameType.RANKED) {
//            //lines.add("&fBracket: " + playerBracket"); TODO: Show the player's bracket
//        }
        board.add("")

        //-- Footer
        board.add("&7scala.gg")
    }
}
