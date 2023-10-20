package gg.pixelarcade.skywars.hologram.commands;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.hologram.TickableHologram;
import gg.pixelarcade.skywars.hologram.handler.HologramHandler;
import gg.pixelarcade.skywars.hologram.meta.HologramData;
import gg.pixelarcade.skywars.hologram.meta.HologramType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.CommandHelp;
import xyz.refinedev.commands.annotation.*;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.chat.Clickable;


import java.util.UUID;

/**
 * This Project is property of Refine Development © 2021 - 2023
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 8/15/2023
 * Project: Bolt
 */

@SuppressWarnings("unused")
@CommandAlias("hologram|holograms|holo|holos")
@CommandPermission("practice.manager")
public class HologramCommands extends BaseCommand {

    @HelpCommand
    @Syntax("[page]")
    public void doHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

//    @Subcommand("list")
//    @Description("List all holograms")
//    @CommandPermission("practice.manager")
//    public void list(CommandSender sender) {
//        HologramHandler hologramHandler = SkyWarsPlugin.getInstance().getHologramHandler();
//
//        sender.sendMessage(Color.CHAT_BAR);
//        sender.sendMessage(Color.translate("&c&lHolo List"));
//        sender.sendMessage("");
//
//        if (hologramHandler.getHolograms().isEmpty()) {
//            sender.sendMessage("");
//            sender.sendMessage(Color.translate("&7&oThere are no holograms created."));
//            sender.sendMessage("");
//            sender.sendMessage(Color.CHAT_BAR);
//            return;
//        }
//
//        for ( TickableHologram hologram : hologramHandler.getHolograms() ) {
//            HologramData data = hologram.getData();
//            String type;
////            switch (hologram.getType()) {
////                case GLOBAL: {
////                    type = " &8[&7Global&8]";
////                    break;
////                }
////                case KIT: {
////                    KitHologram kitHologram = (KitHologram) hologram;
////                    type = " &8[&7Kit&8] &7(" + kitHologram.getKit().getDisplayName() + ")";
////                    break;
////                }
////                default: {
////                    type = " &8[&7Switch&8]";
////                    break;
////                }
////            }
//
////            String text = Color.translate(" &7* &c" + data.getName() + type);
//
//            // Cool click thingy lel
//            if (sender instanceof Player) {
//                Player player = (Player) sender;
//                Clickable clickable = new Clickable(text, "&7Click to teleport to this hologram", "/boltholo teleport " + data.getName());
//                clickable.sendToPlayer(player);
//            } else {
//                sender.sendMessage(text);
//            }
//        }
//        sender.sendMessage(Color.CHAT_BAR);
//    }

//    @Subcommand("create")
//    @Description("Create a hologram")
//    @CommandPermission("practice.manager")
//    public void create(CommandSender sender, Player player,String name, HologramType type, LeaderboardType leaderboardType, @OptArg Kit kitType) {
//        TickableHologram skywarsHologram;
//
////        switch (type) {
////            case GLOBAL: {
////                skywarsHologram = new GlobalHologram(leaderboardType);
////                break;
////            }
////            case SWITCH: {
////                skywarsHologram = new SwitchHologram(leaderboardType);
////                break;
////            }
////            case KIT: {
////                if (kitType == null) {
////                    sender.sendMessage(Color.translate("&cPlease provide a valid kit!"));
////                    return;
////                }
////                skywarsHologram = new KitHologram(kitType, leaderboardType);
////                break;
////            }
////            default: {
////                return;
////            }
////        }
//
//        HologramData data = new HologramData(UUID.randomUUID());
//        data.setLocation(player.getLocation());
//        data.setName(name);
//
//        skywarsHologram.setData(data);
//        skywarsHologram.tick();
//
//        HologramHandler hologramHandler = SkyWarsPlugin.getInstance().getHologramHandler();
//        hologramHandler.getHolograms().add(skywarsHologram);
//
//        sender.sendMessage(Color.translate("&8[&c&lBolt&8] &fSuccessfully created a &chologram&7."));
//    }

    @Subcommand("delete")
    @Description("Delete a hologram")
    @CommandPermission("practice.manager")
    public void delete(CommandSender sender, TickableHologram hologram) {
        HologramHandler hologramHandler = SkyWarsPlugin.getInstance().getHologramHandler();
        hologramHandler.delete(hologram);
        sender.sendMessage(Color.translate("&8[&c&lBolt&8] &fSuccessfully deleted the specified &chologram&7."));
    }

    @Subcommand("teleport")
    @Description("Teleport to a hologram")
    @CommandPermission("practice.manager")
    public void teleport(Player player, TickableHologram hologram) {
        player.teleport(hologram.getData().getLocation());
        player.sendMessage(Color.translate("&8[&c&lBolt&8] &fSuccessfully teleported to the specified &chologram&7."));
    }

    @Subcommand("move")
    @Description("Move a hologram to your location")
    @CommandPermission("practice.manager")
    public void move(Player player, TickableHologram hologram) {
        HologramData data = hologram.getData();
        data.setLocation(player.getLocation());
        data.getHologram().setPosition(player.getLocation());

        player.sendMessage(Color.translate("&8[&c&lBolt&8] &fSuccessfully moved the specified &chologram&7."));
    }

}
