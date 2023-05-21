package xyz.refinedev.skywars.commands;

import com.google.common.base.Strings;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.CommandHelp;
import xyz.refinedev.commands.ConditionFailedException;
import xyz.refinedev.commands.annotation.*;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.skywars.handler.ArenaHandler;
import xyz.refinedev.skywars.menu.arena.ArenasMenu;
import xyz.refinedev.skywars.pxSkyWars;

import java.io.File;

@CommandAlias("arena")
@CommandPermission("practice.command.arena")
public class ArenaCommand extends BaseCommand {
    final ArenaHandler arenaHandler = pxSkyWars.getInstance().getArenaHandler();

    @HelpCommand
    @Syntax("[page]")
    public void doHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("manage")
    @Description("Open the management menu")
    @CommandPermission("practice.command.arena.manage")
    public void manage(Player player) {
        Phoenix.getInstance().getMenuHandler().openMenu(new ArenasMenu(), player);
    }

    @Subcommand("ratings")
    @CommandCompletion("@arenas")
    @Description("View the ratings of an arena")
    @CommandPermission("practice.command.arena.ratings")
    public void ratings(CommandSender sender, @Name("schematic") ArenaSchematic schematic) {
        for (String s : MessageConfig.IMP.ARENA.RATING_LINES) {
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
        ArenaHandler arenaHandler = Bolt.getInstance().getArenaHandler();

        if (arenaHandler.getSchematic(schematicName) != null) {
            throw new ConditionFailedException(Color.translate(MessageConfig.IMP.ARENA.ALREADY_EXISTS
                    .replace("<arena>", schematicName)));
        }

        ArenaSchematic schematic = new ArenaSchematic(schematicName);
        File schemFile = schematic.getSchematicFile();

        if (!schemFile.exists()) {
            throw new ConditionFailedException(Color.translate(MessageConfig.IMP.ARENA.FILE_NOT_FOUND
                    .replace("<arena>", schematicName)
                    .replace("<path>", schemFile.getPath())));
        }

        arenaHandler.registerSchematic(schematic);

        try {
            schematic.pasteModelArena();
            arenaHandler.saveSchematics();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        sender.sendMessage(Color.translate(MessageConfig.IMP.ARENA.SCHEMATIC_CREATE
                .replace("<arena>", schematicName)));
    }

    @Subcommand("listarenas")
    @CommandCompletion("@arenas")
    @Description("List all arenas")
    @CommandPermission("practice.command.arena.listarenas")
    public void arenaListArenas(Player sender, @Name("schematic") ArenaSchematic schematic) {
        for (String s : Color.translate(MessageConfig.IMP.ARENA.LIST_ARENAS)) {

            if (s.contains("<arenas>")) {
                for (Arena arena : arenaHandler.getArenas(schematic)) {
                    String locationStr = locToStr(arena.getSpectatorSpawn());
                    String occupiedStr = arena.isInUse() ? Color.translate(MessageConfig.IMP.ARENA.ARENA_STATUS_IN_USE) : Color.translate(MessageConfig.IMP.ARENA.ARENA_STATUS_OPEN);

                    sender.sendMessage(Color.translate(MessageConfig.IMP.ARENA.ARENA_COPY_INFORMATION
                            .replace("<copy>", String.valueOf(arena.getCopy()))
                            .replace("<location>", locationStr)
                            .replace("<status>", occupiedStr)));
                }
                continue;
            }

            sender.sendMessage(Color.translate(s.replace("<arena>", schematic.getName())));
        }
    }

    @Subcommand("repaste")
    @CommandCompletion("@arenas")
    @Description("Repaste a schematic's arenas")
    @CommandPermission("practice.command.arena.repaste")
    public void arenaRepasteSchematic(Player sender, @Name("schematic") ArenaSchematic schematic) {
        int currentCopies = arenaHandler.countArenas(schematic);

        if (currentCopies == 0) {
            throw new ConditionFailedException(Color.translate(MessageConfig.IMP.ARENA.NO_ARENA_COPIES_AVAILABLE
                    .replace("<arena>", schematic.getName())));
        }

        ArenaGrid arenaGrid = arenaHandler.getGrid();

        sender.sendMessage(Color.translate(MessageConfig.IMP.ARENA.REPASTING_OR_SCALING_ARENAS_STARTING));

        arenaGrid.scaleCopies(schematic, 0, () -> {
            sender.sendMessage(Color.translate(MessageConfig.IMP.ARENA.CREATING_NEW_COPIES));

            arenaGrid.scaleCopies(schematic, currentCopies, () -> sender.sendMessage(Color.translate(MessageConfig.IMP.ARENA.SCHEMATICS_REPASTED
                    .replace("<copies>", String.valueOf(currentCopies))
                    .replace("<schematic>", schematic.getName()))));
        });
    }

    @Private
    @Subcommand("scale")
    @CommandCompletion("@arenas")
    @Description("Scale schematics to a specific size")
    @CommandPermission("practice.command.arena.scale")
    public void arenaScale(Player sender, @Name("schematic") ArenaSchematic schematic, @Name("count") int count) {
        sender.sendMessage(Color.translate(MessageConfig.IMP.ARENA.REPASTING_OR_SCALING_ARENAS_STARTING));

        arenaHandler.getGrid().scaleCopies(schematic, count, () -> sender.sendMessage(Color.translate(MessageConfig.IMP.ARENA.ARENA_SCALE
                .replace("<arena>", schematic.getName())
                .replace("<count>", String.valueOf(count)))));
    }

    @Private
    @Subcommand("scaleall")
    @Description("Rescale all schematics and their arenas")
    @CommandPermission("practice.command.arena.scaleall")
    public void arenaRescaleAll(Player sender, @Name("count") int count) {
        ArenaHandler arenaHandler = Bolt.getInstance().getArenaHandler();
        sender.sendMessage(Color.translate(MessageConfig.IMP.ARENA.REPASTING_OR_SCALING_ARENAS_STARTING));
        arenaHandler.getGrid().scaleall(count, () -> sender.sendMessage(Color.translate(MessageConfig.IMP.ARENA.SCALING_ALL_ARENAS.replace("<count>", String.valueOf(count)))));
    }

    @Subcommand("icon")
    @CommandCompletion("@arenas")
    @Description("Set the icon for an arena")
    @CommandPermission("practice.command.arena.icon")
    public void icon(Player sender, @Name("schematic") ArenaSchematic schematic) {
        if (sender.getItemInHand() == null) {
            throw new ConditionFailedException(Color.translate(MessageConfig.IMP.ARENA.HOLD_AN_ITEM));
        }

        schematic.setIcon(sender.getItemInHand().getData());
        Bolt.getInstance().getArenaHandler().saveSchematics();
        sender.sendMessage(Color.translate(MessageConfig.IMP.ARENA.ARENA_ICON_CHANGED
                .replace("<arena>", schematic.getName())));
    }

    @Subcommand("displayname")
    @CommandCompletion("@arenas")
    @Description("Set the displayname for an arena")
    @CommandPermission("practice.command.arena.displayname")
    public void icon(Player sender, @Name("schematic") ArenaSchematic schematic, @Name("displayname") String displayname) {
        schematic.setDisplayName(displayname);
        pxSkyWars.getInstance().getArenaHandler().saveSchematics();
        sender.sendMessage(Color.translate(MessageConfig.IMP.ARENA.ARENA_ICON_CHANGED
                .replace("<arena>", schematic.getName())));
    }


    @Subcommand("list")
    @Description("List all schematics")
    @CommandPermission("practice.command.arena.list")
    public void arenaListSchems(Player sender) {
        ArenaHandler arenaHandler = Bolt.getInstance().getArenaHandler();
        for (String s : Color.translate(MessageConfig.IMP.ARENA.LIST_SCHEMATICS)){
            if (s.contains("<list>")) {
                arenaHandler.getSchematics().forEach(schematic -> {
                    int size = arenaHandler.getArenas(schematic).size();
                    sender.sendMessage(Color.translate(MessageConfig.IMP.ARENA.LIST_OF_SCHEMATICS
                            .replace("<arena>", schematic.getName())
                            .replace("<copies>", String.valueOf(size))));
                });
            } else {
                sender.sendMessage(Color.translate(s.replace("<total>", String.valueOf(arenaHandler.getSchematics().size()))));
            }
        }
    }

    public static String locToStr(Location loc) {
        return "(" + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + ")";
    }


    public String getProgressBar(int current, int max, int totalBars) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        return Strings.repeat("" + ChatColor.GREEN + '|', progressBars) + Strings.repeat("" + ChatColor.RED + '|', totalBars - progressBars);
    }
}
