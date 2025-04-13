package gg.scala.skywars.handlers

import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.game.GameType

import net.evilblock.cubed.util.bungee.BungeeUtil
import gg.scala.skywars.Limbo
import gg.scala.skywars.LimboPlugin
import gg.scala.skywars.config.LimboConfig
import gg.scala.skywars.handlers.task.CountdownTask
import gg.scala.skywars.util.LimboState
import gg.scala.skywars.util.StartingState
import net.evilblock.cubed.util.CC
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import java.util.function.Consumer
import kotlin.collections.ArrayList


class LobbyHandler {
    val lobby = Lobby(LimboConfig.SETTINGS.gameType, LimboConfig.SETTINGS.teamType)

    fun addPlayer(player: Player) {
        if (lobby.players.size >= lobby.gameTeamType.maxPlayers) {
            player.sendMessage("You aren't supposed to be here!")
        }

        // Add the player to the player list
        lobby.players.add(player.uniqueId)

        val playerCount = lobby.players.size

        // Player count checks
        if (playerCount == LimboConfig.SETTINGS.serverStartPlayers) {
            if (!lobby.serverDeployed) {
                lobby.limboState = LimboState.DEPLOYING
                Limbo.lobbyInstance.lobbyHandler.deployGame(false)
            }
        }

        if (playerCount == LimboConfig.SETTINGS.minPlayers) {
            lobby.startingState = StartingState.COUNTING
            if (lobby.countdown == null) {
                lobby.countdown = CountdownTask(60)
                lobby.countdown!!.runTaskTimer(LimboPlugin.instance, 20, 20)
            } else {
                lobby.countdown!!.countdown = 60
            }
        }

        if (playerCount == LimboConfig.SETTINGS.maxPlayers) {
            if (lobby.countdown!!.countdown > 30) {
                lobby.countdown!!.countdown = 30
            }
        }
    }

    fun removePlayer(player: Player) {
        // Remove the player from the player list
        lobby.players.remove(player.uniqueId)

        val playerCount = lobby.players.size
        if (playerCount < LimboConfig.SETTINGS.minPlayers && lobby.countdown != null) {
            lobby.countdown!!.stop = true
            lobby.limboState = LimboState.DEPLOYED
        }

        Bukkit.broadcastMessage("${CC.GREEN}Countdown ${CC.RED}stopped ${CC.GRAY}(Unfulfilled requirements)")
    }

    fun deployGame(sendOnDeploy: Boolean) {
        //-- Instance Variables

        val templates: List<String> = CgsGameEngine.getTemplates()
        val skywarsTemplates: MutableList<String> = ArrayList()

        if (templates.isEmpty()) {
            LimboPlugin.instance.getLogger().warning("No templates found!")
            return
        }

        for (template in templates) {
            if (template.startsWith("skywars-")) skywarsTemplates.add(template)
        }

        if (skywarsTemplates.isEmpty()) {
            LimboPlugin.instance.logger.warning("No skywars templates found!")
            return
        }

        val number = ThreadLocalRandom.current().nextInt(skywarsTemplates.size)
        val templateName = skywarsTemplates[number]

        createServer(templateName) { serverName: String ->
            // Send all the players to the server
            if (!sendOnDeploy) {
                Limbo.lobbyInstance.lobbyHandler.lobby.limboState = LimboState.DEPLOYED
                Limbo.lobbyInstance.lobbyHandler.lobby.serverName = serverName
                return@createServer
            }
            for (player in Bukkit.getOnlinePlayers()) {
                player.sendMessage("${CC.GREEN}Sending you to $serverName!")
                BungeeUtil.sendToServer(player, serverName)
            }
        }
    }

    private fun createServer(templateName: String?, consumer: Consumer<String>?) {
        val gameType = templateName?.let { GameType.getGameByDisplayName(it) }
        val serverName = templateName + "-" + getId()
        CgsGameEngine.createServer(serverName, templateName!!, gameType!!.maxTeamSize, gameType.toString(), consumer)
    }

    private fun getId(): String {
        val uuid = UUID.randomUUID()
        val uuidString = uuid.toString()
        val chars = uuidString.substring(0, 8)
        return chars
    }
}

