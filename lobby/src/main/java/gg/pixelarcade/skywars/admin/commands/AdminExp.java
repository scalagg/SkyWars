package gg.pixelarcade.skywars.admin.commands;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.annotation.CommandAlias;
import xyz.refinedev.commands.annotation.Description;
import xyz.refinedev.commands.annotation.Name;
import xyz.refinedev.commands.annotation.Subcommand;

@CommandAlias("skywarsexp")
public class AdminExp extends BaseCommand {

    @Subcommand("giveexp")
    @Description("Give [player] an ammount of exp")
    public void giveExp(CommandSender sender, @Name("player name") String name, @Name("exp amount") int amount){
        SkyWarsShared.getInstance().getProfileHandler().getProfile(Bukkit.getPlayer(name).getUniqueId()).incrementXP(amount);

        sender.sendMessage(ChatColor.GOLD + "Done!");
    }

    @Subcommand("setexp")
    @Description("Set [player]'s exp")
    public void setExp(CommandSender sender, @Name("player name") String name, @Name("exp amount") int amount){
        SkyWarsShared.getInstance().getProfileHandler().getProfile(Bukkit.getPlayer(name).getUniqueId()).setXp(amount);

        sender.sendMessage(ChatColor.GOLD + "Done!");
    }

    @Subcommand("incrementLevel")
    @Description("Increment a [player]'s level ")
    public void giveLevel(CommandSender sender, @Name("Player name") String name, @Name("level amount") int amount){

        SkyWarsShared.getInstance().getProfileHandler().getProfile(Bukkit.getPlayer(name).getUniqueId()).incrementLevel();

        sender.sendMessage(ChatColor.GOLD + "Done!");
    }
}