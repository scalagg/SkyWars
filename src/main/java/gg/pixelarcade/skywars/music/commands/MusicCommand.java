package gg.pixelarcade.skywars.music.commands;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.music.MusicHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.CommandHelp;
import xyz.refinedev.commands.annotation.*;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.profile.Profile;

import java.awt.*;
import java.util.Map;
import java.util.UUID;

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

    @Subcommand("stopall")
    @Description("Stop all music for all players")
    @CommandPermission("skywars.music.stopall")
    public void stopAll(CommandSender sender) {
        for (UUID uuid : handler.getRadioSongPlayer().getPlayerUUIDs())
            handler.getRadioSongPlayer().removePlayer(uuid);

        sender.sendMessage("§aYou have stopped all music for all players.");
    }

    @Subcommand("playall")
    @Description("Play all music for all players")
    @CommandPermission("skywars.music.playall")
    public void playAll(CommandSender sender) {
        for (UUID uuid : handler.getRadioSongPlayer().getPlayerUUIDs()) {
            if (SharedAPI.getInstance().getProfileHandler().getProfile(uuid).isSetting("music"))
                continue;

            handler.getRadioSongPlayer().addPlayer(uuid);
        }

        sender.sendMessage("§aYou have played all music for all players.");
    }

    @Subcommand("toggle")
    @Description("Toggle hearing music")
    public void toggle(Player player) {
        if (player.hasMetadata("music")) {
            player.removeMetadata("music", SkyWarsPlugin.getInstance());
            player.sendMessage("§aYou have toggled music off.");
            return;
        } else {
            player.setMetadata("music", new FixedMetadataValue(SkyWarsPlugin.getInstance(), true));
            player.sendMessage("§aYou have toggled music on.");
            return;
        }
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
