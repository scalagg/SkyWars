package gg.pixelarcade.skywars.commands;

import com.google.common.base.Strings;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.arena.ArenaSchematic;
import gg.pixelarcade.skywars.utils.SpigotConstants;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.CommandHelp;
import xyz.refinedev.commands.ConditionFailedException;
import xyz.refinedev.commands.annotation.*;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.utils.Color;
import gg.pixelarcade.skywars.arena.menu.manage.ManageMenu;
import gg.pixelarcade.skywars.arena.ArenaHandler;

import java.io.File;
import java.util.Arrays;

@CommandAlias("arena")
@CommandPermission("practice.command.arena")
public class ArenaCommand extends BaseCommand {

    private final ArenaHandler handler = SkyWarsPlugin.getInstance().getArenaHandler();

    @HelpCommand
    @Syntax("[page]")
    public void doHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("manage")
    @Description("Open the management menu")
    @CommandPermission("practice.command.arena.manage")
    public void manage(Player player) {
        Phoenix.getInstance().getMenuHandler().openMenu(new ManageMenu(), player);
    }

    @Subcommand("ratings")
    @CommandCompletion("@arenas")
    @Description("View the ratings of an arena")
    @CommandPermission("practice.command.arena.ratings")
    public void ratings(CommandSender sender, @Name("schematic") ArenaSchematic schematic) {
        for (String s : Arrays.asList(
                "&7&m------------------------------",
                "&b&l<arena> Ratings",
                "&7&m------------------------------",
                " &e● &fPositive: &a<positive>",
                " &e● &fNegative: &c<negative>",
                " &e● &fRating Bar: <ratingBar>",
                "&7&m------------------------------"
        )) {
            sender.sendMessage(Color.translate(s
                    .replace("<arena>", schematic.getName())
                    .replace("<positive>", String.valueOf(schematic.getPositiveRatings()))
                    .replace("<negative>", String.valueOf(schematic.getNegativeRatings()))
                    .replace("<ratingBar>", getProgressBar(schematic.getPositiveRatings(), schematic.getNegativeRatings() + schematic.getPositiveRatings(), 20))));
        }
    }

    @Subcommand("create")
    @CommandPermission("practice.command.arena.create")
    @Description("Create and load a schematic from world edit as an arena")
    public void arenaCreateSchematic(Player sender, @Name("schematic") String schematicName) {
        if (handler.getSchematic(schematicName) != null) {
            sender.sendMessage(ChatColor.RED + "Schematic " + schematicName + " already exists");
            return;
        }

        ArenaSchematic schematic = new ArenaSchematic(schematicName);
        File schemFile = schematic.getSchematicFile();

        if (!schemFile.exists()) {
            sender.sendMessage(ChatColor.RED + "No file for " + schematicName + " found. (" + schemFile.getPath() + ")");
            return;
        }

        handler.registerSchematic(schematic);

        try {
            handler.saveSchematics();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        sender.sendMessage(Color.translate(SpigotConstants.GAME + " &aSuccessfully created the schematic &b<arena>&a."
                .replace("<arena>", schematicName)));
    }

    @Subcommand("icon")
    @CommandCompletion("@arenas")
    @Description("Set the icon for an arena")
    @CommandPermission("practice.command.arena.icon")
    public void icon(Player sender, @Name("schematic") ArenaSchematic schematic) {
        if (sender.getItemInHand() == null) {
            throw new ConditionFailedException(Color.translate(SpigotConstants.GAME + "&cYou must be holding an item."));
        }

        schematic.setIcon(sender.getItemInHand().getData());
        handler.saveSchematics();
        sender.sendMessage(Color.translate(SpigotConstants.GAME + "&cSet the icon for &b<arena>&c."
                .replace("<arena>", schematic.getName())));
    }

    @Subcommand("displayname")
    @CommandCompletion("@arenas")
    @Description("Set the displayname for an arena")
    @CommandPermission("practice.command.arena.displayname")
    public void icon(Player sender, @Name("schematic") ArenaSchematic schematic, @Name("displayname") String displayname) {
        schematic.setDisplayName(displayname);
        handler.saveSchematics();
        sender.sendMessage(Color.translate(SpigotConstants.GAME + "&cSet the displayname for &b<arena>&c."
                .replace("<arena>", schematic.getName())));
    }

    @Subcommand("list")
    @Description("List all schematics")
    @CommandPermission("practice.command.arena.list")
    public void arenaListSchems(Player sender) {
        for (String s : Color.translate(Arrays.asList(
                "&7&m------------------------------",
                "&3&lSchematics &7(Total: <total>)",
                "&7&m------------------------------",
                " <list>",
                "&7&m------------------------------"
        ))) {
            if (s.contains("<list>")) {
                handler.getSchematics().forEach(schematic -> {
                    sender.sendMessage(Color.translate("&e● &b<arena>"
                            .replace("<arena>", schematic.getName())));
                });
            } else {
                sender.sendMessage(Color.translate(s.replace("<total>", String.valueOf(handler.getSchematics().size()))));
            }
        }
    }

    public String getProgressBar(int current, int max, int totalBars) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        return Strings.repeat("" + ChatColor.GREEN + '|', progressBars) + Strings.repeat("" + ChatColor.RED + '|', totalBars - progressBars);
    }
}
