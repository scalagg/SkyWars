package gg.pixelarcade.skywars.region.commands;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.region.Region;
import gg.pixelarcade.skywars.region.RegionHandler;
import gg.pixelarcade.skywars.region.selection.RegionSelection;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.CommandHelp;
import xyz.refinedev.commands.annotation.*;
import xyz.refinedev.phoenix.packet.api.PacketHandler;
import xyz.refinedev.phoenix.utils.Color;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.region.commands
 * @date 19/10/2023 / 16:12
 */

@CommandAlias("region")
@CommandPermission("skywars.region")
public class RegionCommands extends BaseCommand {

    private final RegionHandler handler = SkyWarsPlugin.getInstance().getRegionHandler();

    @HelpCommand
    @Syntax("[page]")
    public void doHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("wand")
    @CommandPermission("skywars.region.wand")
    public void wand(Player player) {
        if (player.getInventory().contains(RegionSelection.SELECTION_WAND))
            return;

        player.getInventory().addItem(RegionSelection.SELECTION_WAND);
        player.sendMessage(Color.translate("&aYou have been given a region selection wand."));
    }

    @Subcommand("create")
    @Description("Create a region")
    @CommandPermission("skywars.region.create")
    public void create(Player player, @Name("name") String name) {
        RegionSelection selection = handler.getSelection(player);
        if (!selection.isValidSelection()) {
            player.sendMessage(Color.translate("&cYou must select a region first."));
            return;
        }

        Region region = new Region(selection.getRegion().getBounds(), name);
        handler.addRegion(region);
        handler.saveRegions();

        player.sendMessage(Color.translate("&eYou have created a region with the name &6" + name + "&e."));
    }

}
