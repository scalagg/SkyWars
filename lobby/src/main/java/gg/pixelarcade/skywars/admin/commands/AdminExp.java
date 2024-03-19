package gg.pixelarcade.skywars.admin.commands;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.leaderboard.impl.LevelLeaderboard;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import gg.pixelarcade.skywars.util.GsonProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.annotation.*;

@CommandPermission("skywars.admin")
@CommandAlias("skywarsexp")
public class AdminExp extends BaseCommand {

    @Subcommand("givecoins")
    @Description("Give [player] an amount of coins")
    public void giveCoins(CommandSender sender, @Name("player") Player player, @Name("amount") int amount) {
        SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).incrementCoins(amount);

        sender.sendMessage(ChatColor.GOLD + "Done! ");
    }
    @Subcommand("giveexp")
    @Description("Give [player] an ammount of exp")
    public void giveExp(CommandSender sender, @Name("player") Player player, @Name("amount") int amount){
        SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).incrementXP(amount);
        SkyWarsShared.getInstance().getLeaderboardHandler().getLeaderboard(LevelLeaderboard.class).update(
                SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId())
        );

        sender.sendMessage(ChatColor.GOLD + "Done!");
    }

    @Subcommand("setexp")
    @Description("Set [player]'s exp")
    public void setExp(CommandSender sender, @Name("player") Player player, @Name("amount") int amount){
        SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).setXp(amount);
        SkyWarsShared.getInstance().getLeaderboardHandler().getLeaderboard(LevelLeaderboard.class).update(
                SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId())
        );
        sender.sendMessage(ChatColor.GOLD + "Done!");
        SkyWarsShared.getInstance().getProfileHandler().getProfile(Bukkit.getPlayer(name).getUniqueId()).save();
    }

    @Subcommand("setLevel")
    public void setLevel(CommandSender sender, @Name("player name") String name, @Name("level") int amount) {
        SkyWarsShared.getInstance().getProfileHandler().getProfile(Bukkit.getPlayer(name).getUniqueId()).setLevel(amount);
        SkyWarsShared.getInstance().getLeaderboardHandler().getLeaderboard(LevelLeaderboard.class).update(
                SkyWarsShared.getInstance().getProfileHandler().getProfile(Bukkit.getPlayer(name).getUniqueId())
        );
        sender.sendMessage(ChatColor.GOLD + "Done!");
        SkyWarsShared.getInstance().getProfileHandler().getProfile(Bukkit.getPlayer(name).getUniqueId()).save();
    }

    @Subcommand("incrementLevel")
    @Description("Increment a [player]'s level ")
    public void giveLevel(CommandSender sender, @Name("Player name") String name){

        SkyWarsShared.getInstance().getProfileHandler().getProfile(Bukkit.getPlayer(name).getUniqueId()).incrementLevel();
        SkyWarsShared.getInstance().getLeaderboardHandler().getLeaderboard(LevelLeaderboard.class).update(
                SkyWarsShared.getInstance().getProfileHandler().getProfile(Bukkit.getPlayer(name).getUniqueId())
        );
        sender.sendMessage(ChatColor.GOLD + "Done!");
    }

    @Async
    @Subcommand("lb")
    public void lb(Player sender){
        SkyWarsShared.getInstance().getLeaderboardHandler().getLeaderboard(LevelLeaderboard.class).top10().join().forEach(uuid -> {
            SkyWarsProfile profile = SkyWarsShared.getInstance().getProfileHandler().getProfile(uuid);
            sender.sendMessage(ChatColor.GOLD + profile.getName() + " - " + profile.getLevel());
        });
    }

    @Private
    @CommandPermission("skywars.admin")
    @CommandAlias("skaprofile")
    public void profile(CommandSender player, @Name("player") Player name){
        SkyWarsProfile profile = SkyWarsShared.getInstance().getProfileHandler().getProfile(name.getUniqueId());
        player.sendMessage(GsonProvider.GSON.toJson(profile));
    }
}