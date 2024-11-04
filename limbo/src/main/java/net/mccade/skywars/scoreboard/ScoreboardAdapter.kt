package net.mccade.skywars.scoreboard

import gg.pixelarcade.skywars.scoreboard.api.animation.impl.TitleAnimation

import net.evilblock.cubed.scoreboard.ScoreboardAdapter
import net.mccade.skywars.Limbo
import net.mccade.skywars.util.StartingState
import org.bukkit.entity.Player
import java.text.SimpleDateFormat
import java.util.*

class ScoreboardAdapter(private val plugin: Limbo) :  ScoreboardAdapter() {

    private val animation = TitleAnimation(
        "SKYWARS",
        "&6&l",
        "&e&l",
        "&f&l"
    )

    override fun getTitle(player: Player): String {
        return animation.currentFrame
    }

    override fun getLines(lines: LinkedList<String>, player: Player) {
        //-- Instance Variables

        //-- Date
        val dateFormat = SimpleDateFormat("MM/dd/yy")
        lines.add("&7" + dateFormat.format(System.currentTimeMillis()))
        lines.add("")

        //-- Player Count
        val currentPlayers = plugin.lobbyHandler.lobby.players.size
        val maxPlayers = plugin.lobbyHandler.lobby.gameTeamType.maxPlayers
        lines.add("&fPlayers: &a$currentPlayers/$maxPlayers")
        lines.add("")

        //-- Waiting
        if (plugin.lobbyHandler.lobby.startingState != StartingState.COUNTING) {
            lines.add("&fWaiting...")
            lines.add("")
        } else {
            lines.add("&aStarting in: " + plugin.lobbyHandler.lobby.countdown?.countdown + "s")
            lines.add("")
        }

        val badType = plugin.lobbyHandler.lobby.gameTeamType.name.lowercase(Locale.getDefault())
        val goodType = badType.substring(0, 1).uppercase(Locale.getDefault()) + badType.substring(1)

        //-- Map, Mode, Bracket if there is one
        lines.add("&fType: &b&l$goodType")
        lines.add("&fMode: " + "&b&l" + plugin.lobbyHandler.lobby.gameType.displayName)
        val gameType = plugin.lobbyHandler.lobby.gameType
        //        if (gameType == GameType.RANKED) {
//            //lines.add("&fBracket: " + playerBracket"); TODO: Show the player's bracket
//        }
        lines.add("")

        //-- Footer
        lines.add("&eplay.mccade.net")
    }
}
