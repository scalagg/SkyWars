package gg.pixelarcade.skywars.admin.commands

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.leaderboard.impl.LevelLeaderboard
import gg.pixelarcade.skywars.util.GsonProvider
import gg.scala.commons.acf.annotation.*
import gg.scala.commons.annotations.commands.AutoRegister
import gg.scala.commons.command.ScalaCommand
import net.evilblock.cubed.util.bukkit.Tasks
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*
import java.util.function.Consumer

@AutoRegister
@CommandPermission("skywars.admin")
@CommandAlias("skywarsadmin|swadmin|skywars-admin|sw-admin")
class AdminExp : ScalaCommand() {

    @Subcommand("givecoins")
    @Description("Give [player] an amount of coins")
    fun giveCoins(sender: CommandSender, @Name("player") name: String?, @Name("amount") amount: Int) {
        SkyWarsShared.instance.getProfile(Bukkit.getPlayer(name).uniqueId).incrementCoins(amount)

        sender.sendMessage(ChatColor.GOLD.toString() + "Done! ")
    }

    @Subcommand("giveexp")
    @Description("Give [player] an ammount of exp")
    fun giveExp(sender: CommandSender, @Name("player") name: String?, @Name("amount") amount: Int) {
        SkyWarsShared.instance.getProfile(Bukkit.getPlayer(name).uniqueId).incrementXP(amount)
        SkyWarsShared.instance.leaderboardHandler.getLeaderboard(LevelLeaderboard::class.java)!!.update(
            SkyWarsShared.instance.getProfile(Bukkit.getPlayer(name).uniqueId)
        )

        sender.sendMessage(ChatColor.GOLD.toString() + "Done!")
    }

    @Subcommand("setexp")
    @Description("Set [player]'s exp")
    fun setExp(sender: CommandSender, @Name("player") name: String?, @Name("amount") amount: Int) {
        SkyWarsShared.instance.getProfile(Bukkit.getPlayer(name).uniqueId).xp = amount
        SkyWarsShared.instance.leaderboardHandler.getLeaderboard(LevelLeaderboard::class.java)!!.update(
            SkyWarsShared.instance.getProfile(Bukkit.getPlayer(name).uniqueId)
        )
        sender.sendMessage(ChatColor.GOLD.toString() + "Done!")
        SkyWarsShared.instance.getProfile(Bukkit.getPlayer(name).uniqueId).save()
    }

    @Subcommand("setLevel")
    fun setLevel(sender: CommandSender, @Name("player name") name: String?, @Name("level") amount: Int) {
        SkyWarsShared.instance.getProfile(Bukkit.getPlayer(name).uniqueId).level = amount
        SkyWarsShared.instance.leaderboardHandler.getLeaderboard(LevelLeaderboard::class.java)!!.update(
            SkyWarsShared.instance.getProfile(Bukkit.getPlayer(name).uniqueId)
        )
        sender.sendMessage(ChatColor.GOLD.toString() + "Done!")
        SkyWarsShared.instance.getProfile(Bukkit.getPlayer(name).uniqueId).save()
    }

    @Subcommand("incrementLevel")
    @Description("Increment a [player]'s level ")
    fun giveLevel(sender: CommandSender, @Name("Player name") name: String?) {
        SkyWarsShared.instance.getProfile(Bukkit.getPlayer(name).uniqueId).incrementLevel()
        SkyWarsShared.instance.leaderboardHandler.getLeaderboard(LevelLeaderboard::class.java)!!.update(
            SkyWarsShared.instance.getProfile(Bukkit.getPlayer(name).uniqueId)
        )
        sender.sendMessage(ChatColor.GOLD.toString() + "Done!")
    }


    @Subcommand("lb")
    fun lb(sender: Player) {
        Tasks.async {
            SkyWarsShared.instance.leaderboardHandler.getLeaderboard(LevelLeaderboard::class.java)!!.top10().join().forEach(
                Consumer { uuid: UUID? ->
                    val profile = SkyWarsShared.instance.getProfile(uuid!!)
                    sender.sendMessage(ChatColor.GOLD.toString() + profile.name + " - " + profile.level)
                })
        }

    }

    @Private
    @CommandPermission("skywars.admin")
    @CommandAlias("skaprofile")
    fun profile(player: CommandSender, @Name("player") name: Player) {
        val profile = SkyWarsShared.instance.getProfile(name.uniqueId)
        player.sendMessage(GsonProvider.GSON.toJson(profile))
    }
}