package gg.scala.skywars.handlers

import gg.scala.commons.agnostic.sync.ServerSync

import net.evilblock.cubed.util.bungee.BungeeUtil
import gg.scala.skywars.Limbo
import gg.scala.skywars.LimboPlugin
import gg.scala.skywars.config.LimboConfig
import gg.scala.skywars.handlers.task.CountdownTask
import gg.scala.skywars.util.LimboState
import gg.scala.skywars.util.StartingState
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import studio.pinkcloud.deploy.spigot.api.Deployer
import java.util.concurrent.ThreadLocalRandom
import java.util.function.Consumer


class LobbyHandler {
    val lobby = Lobby(gg.scala.skywars.config.LimboConfig.SETTINGS.gameType, gg.scala.skywars.config.LimboConfig.SETTINGS.teamType)

    fun addPlayer(player: Player) {
        if (lobby.players.size >= lobby.gameTeamType.maxPlayers) {
            player.sendMessage("You aren't supposed to be here!")
        }

        // Add the player to the player list
        lobby.players.add(player.uniqueId)

        val playerCount = lobby.players.size

        // Player count checks
        if (playerCount == gg.scala.skywars.config.LimboConfig.SETTINGS.serverStartPlayers) {
            if (!lobby.serverDeployed) {
                lobby.limboState = LimboState.DEPLOYING
                gg.scala.skywars.Limbo.lobbyInstance.lobbyHandler.deployGame(false)
            }
        }

        if (playerCount == gg.scala.skywars.config.LimboConfig.SETTINGS.minPlayers) {
            lobby.startingState = StartingState.COUNTING
            if (lobby.countdown == null) {
                lobby.countdown = CountdownTask(60)
                lobby.countdown!!.runTaskTimer(LimboPlugin.instance, 20, 20)
            } else {
                lobby.countdown!!.countdown = 60
            }
        }

        if (playerCount == gg.scala.skywars.config.LimboConfig.SETTINGS.maxPlayers) {
            if (lobby.countdown!!.countdown > 30) {
                lobby.countdown!!.countdown = 30
            }
        }
    }

    fun removePlayer(player: Player) {
        // Remove the player from the player list
        lobby.players.remove(player.uniqueId)

        val playerCount = lobby.players.size
        if (playerCount < gg.scala.skywars.config.LimboConfig.SETTINGS.minPlayers && lobby.countdown != null) {
            lobby.countdown!!.stop = true
            lobby.limboState = LimboState.DEPLOYED
        }

        Bukkit.broadcastMessage("Countdown stopped (Unfulfilled requirements)")
    }

    fun deployGame(sendOnDeploy: Boolean) {
        //-- Instance Variables
        val templates: List<String> = Deployer.getTemplates()
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
                gg.scala.skywars.Limbo.lobbyInstance.lobbyHandler.lobby.limboState = LimboState.DEPLOYED
                gg.scala.skywars.Limbo.lobbyInstance.lobbyHandler.lobby.serverName = serverName
                return@createServer
            }
            for (player in Bukkit.getOnlinePlayers()) {
                player.sendMessage("Sending you to the game!")
                BungeeUtil.sendToServer(player, serverName)
            }
        }
    }

    fun createServer(templateName: String?, consumer: Consumer<String>?) {
        Deployer.deploy(
            templateName,
            ServerSync.local.region.name,
            consumer
        )
    }
}

