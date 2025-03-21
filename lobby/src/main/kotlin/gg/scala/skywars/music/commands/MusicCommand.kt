package gg.scala.skywars.music.commands

import gg.scala.skywars.SkyWarsLobby
import gg.scala.skywars.profile.hasMusicEnabled
import gg.scala.commons.acf.CommandHelp
import gg.scala.commons.acf.annotation.*
import gg.scala.commons.annotations.commands.AutoRegister
import gg.scala.commons.command.ScalaCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author alfie
 * @project Skywars / gg.scala.game.music.commands
 * @date 18/10/2023 / 19:05
 */
@AutoRegister
@CommandAlias("music")
class MusicCommand : ScalaCommand() {

    @Default
    @HelpCommand
    fun onHelp(help: CommandHelp) {
        help.showHelp()
    }

    @Subcommand("toggle")
    @Description("Toggles the music for the player")
    fun toggle(sender: Player) {
        if (SkyWarsLobby.lobbyInstance.musicHandler.radioSongPlayer!!.playerUUIDs.contains(sender.uniqueId)) {
            SkyWarsLobby.lobbyInstance.musicHandler.radioSongPlayer!!.removePlayer(sender.uniqueId)
        } else {
            SkyWarsLobby.lobbyInstance.musicHandler.radioSongPlayer!!.addPlayer(sender.uniqueId)
        }
    }

    @Subcommand("skip")
    @Description("Skip the current song")
    @CommandPermission("skywars.music.skip")
    fun skip(sender: CommandSender) {
        SkyWarsLobby.lobbyInstance.musicHandler.radioSongPlayer!!.playNextSong()
        sender.sendMessage("§aYou have skipped the current song.")
    }

    @Subcommand("stopall")
    @Description("Stop all music for all players")
    @CommandPermission("skywars.music.stopall")
    fun stopAll(sender: CommandSender) {
        for (uuid in SkyWarsLobby.lobbyInstance.musicHandler.radioSongPlayer!!.playerUUIDs) SkyWarsLobby.lobbyInstance.musicHandler.radioSongPlayer!!.removePlayer(uuid)

        sender.sendMessage("§aYou have stopped all music for all players.")
    }

    @Subcommand("playall")
    @Description("Play all music for all players")
    @CommandPermission("skywars.music.playall")
    fun playAll(sender: CommandSender) {
        for (uuid in SkyWarsLobby.lobbyInstance.musicHandler.radioSongPlayer!!.playerUUIDs) {
            val player = Bukkit.getPlayer(uuid)
            if (player == null || !player.hasMusicEnabled()) continue

            SkyWarsLobby.lobbyInstance.musicHandler.radioSongPlayer!!.addPlayer(uuid)
        }

        sender.sendMessage("§aYou have played all music for all players.")
    }

    @Subcommand("information")
    @Description("View information about the current song")
    fun information(sender: CommandSender) {
        sender.sendMessage(" ")
        sender.sendMessage("§6■ §fCurrent Song: §e" + SkyWarsLobby.lobbyInstance.musicHandler.radioSongPlayer!!.song.title)
        sender.sendMessage("§6■ §fCurrent Author: §e" + SkyWarsLobby.lobbyInstance.musicHandler.radioSongPlayer!!.song.author)
        sender.sendMessage("§6■ §fCurrent Length: §e" + SkyWarsLobby.lobbyInstance.musicHandler.radioSongPlayer!!.song.length)
        sender.sendMessage("§6■ §fCurrent Tempo: §e" + SkyWarsLobby.lobbyInstance.musicHandler.radioSongPlayer!!.song.speed)
        sender.sendMessage(" ")
    }
}
