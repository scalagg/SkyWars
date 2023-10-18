package gg.pixelarcade.skywars.music.commands;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.music.MusicHandler;
import org.bukkit.command.CommandSender;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.CommandHelp;
import xyz.refinedev.commands.annotation.*;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.music.commands
 * @date 18/10/2023 / 19:05
 */

@CommandAlias("music")
public class MusicCommand extends BaseCommand {

    private final MusicHandler handler = SkyWarsPlugin.getInstance().getMusicHandler();

    @HelpCommand
    @Syntax("[page]")
    public void doHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("skip")
    @Description("Skip the current song")
    @CommandPermission("skywars.music.skip")
    public void skip(CommandSender sender) {
        handler.getRadioSongPlayer().playNextSong();
        sender.sendMessage("§aYou have skipped the current song.");
    }

    @Subcommand("information")
    @Description("View information about the current song")
    public void information(CommandSender sender) {
        sender.sendMessage(" ");
        sender.sendMessage("§6■ §fCurrent Song: §e" + handler.getRadioSongPlayer().getSong().getTitle());
        sender.sendMessage("§6■ §fCurrent Author: §e" + handler.getRadioSongPlayer().getSong().getAuthor());
        sender.sendMessage("§6■ §fCurrent Length: §e" + handler.getRadioSongPlayer().getSong().getLength());
        sender.sendMessage("§6■ §fCurrent Tempo: §e" + handler.getRadioSongPlayer().getSong().getSpeed());
        sender.sendMessage(" ");
    }

}
