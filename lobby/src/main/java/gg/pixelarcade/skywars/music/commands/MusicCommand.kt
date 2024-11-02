package gg.pixelarcade.skywars.music.commands

import gg.pixelarcade.skywars.SkyWarsLobby
import gg.pixelarcade.skywars.profile.hasMusicEnabled
import gg.scala.commons.acf.BaseCommand
import gg.scala.commons.acf.CommandHelp
import gg.scala.commons.acf.annotation.*
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.music.commands
 * @date 18/10/2023 / 19:05
 */
@CommandAlias("music")
class MusicCommand : BaseCommand() {
    private val handler = SkyWarsLobby.lobbyInstance.musicHandler

    @HelpCommand
    @Syntax("[page]")
    fun doHelp(sender: CommandSender?, help: CommandHelp) {
        help.showHelp()
    }

    @Subcommand("toggle")
    @Description("Toggles the music for the player")
    fun toggle(sender: Player) {
        if (handler.radioSongPlayer!!.playerUUIDs.contains(sender.uniqueId)) {
            handler.radioSongPlayer!!.removePlayer(sender.uniqueId)
        } else {
            handler.radioSongPlayer!!.addPlayer(sender.uniqueId)
        }
    }

    @Subcommand("skip")
    @Description("Skip the current song")
    @CommandPermission("skywars.music.skip")
    fun skip(sender: CommandSender) {
        handler.radioSongPlayer!!.playNextSong()
        sender.sendMessage("§aYou have skipped the current song.")
    }

    @Subcommand("stopall")
    @Description("Stop all music for all players")
    @CommandPermission("skywars.music.stopall")
    fun stopAll(sender: CommandSender) {
        for (uuid in handler.radioSongPlayer!!.playerUUIDs) handler.radioSongPlayer!!.removePlayer(uuid)

        sender.sendMessage("§aYou have stopped all music for all players.")
    }

    @Subcommand("playall")
    @Description("Play all music for all players")
    @CommandPermission("skywars.music.playall")
    fun playAll(sender: CommandSender) {
        for (uuid in handler.radioSongPlayer!!.playerUUIDs) {
            val player = Bukkit.getPlayer(uuid)
            if (player == null || !player.hasMusicEnabled()) continue

            handler.radioSongPlayer!!.addPlayer(uuid)
        }

        sender.sendMessage("§aYou have played all music for all players.")
    }

    @Subcommand("information")
    @Description("View information about the current song")
    fun information(sender: CommandSender) {
        sender.sendMessage(" ")
        sender.sendMessage("§6■ §fCurrent Song: §e" + handler.radioSongPlayer!!.song.title)
        sender.sendMessage("§6■ §fCurrent Author: §e" + handler.radioSongPlayer!!.song.author)
        sender.sendMessage("§6■ §fCurrent Length: §e" + handler.radioSongPlayer!!.song.length)
        sender.sendMessage("§6■ §fCurrent Tempo: §e" + handler.radioSongPlayer!!.song.speed)
        sender.sendMessage(" ")
    }
}
