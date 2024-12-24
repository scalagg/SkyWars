package gg.pixelarcade.skywars.battlepass.command

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.battlepass.BattlePass
import gg.pixelarcade.skywars.battlepass.menu.editor.BattlePassEditorMenu
import gg.scala.commons.acf.CommandHelp
import gg.scala.commons.acf.annotation.*
import gg.scala.commons.annotations.commands.AutoRegister
import gg.scala.commons.command.ScalaCommand
import gg.scala.commons.issuer.ScalaPlayer
import net.evilblock.cubed.util.bukkit.Tasks
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import java.util.*

/**
 * @date 12/10/2024
 * @author Moose1301
 */
@AutoRegister
@CommandAlias("battlepassadmin")
@CommandPermission("skywars.battlepass.admin")
object BattlePassAdminCommand : ScalaCommand() {

    @Default
    @HelpCommand
    fun onHelp(help: CommandHelp) {
        help.showHelp()
    }

    @Subcommand("setxp")
    @CommandPermission("skywars.battlepass.admin.setxp")
    fun handleSetXp(sender: ScalaPlayer, @Name("player") player: UUID, @Name("experience") experience: Int) {
        Tasks.async {
            val profile = SkyWarsShared.instance.getProfile(player);

            profile.battlePass.setExperience(player, experience)

            sender.sendMessage("${ChatColor.GREEN}You have set ${ChatColor.WHITE}${profile.niceName}${ChatColor.GREEN}'s BattlePass XP to $experience!")
            profile.save()
        }
    }

    @Subcommand("setpremium|set-premium")
    @CommandPermission("skywars.battlepass.admin.premium")
    fun handlePremium(sender: ScalaPlayer, @Name("player") player: UUID, @Name("premium") premium: Boolean) {
        Tasks.async {
            val profile = SkyWarsShared.instance.getProfile(player);

            val battlePass = profile.battlePass

            battlePass.setPremium(player, premium)
            battlePass.requiresUpdate = true
            profile.save()

            if (premium) {
                Bukkit.getServer().getPlayer(player)?.also {
                    it.sendMessage("")
                    it.sendMessage(" ${ChatColor.GOLD}${ChatColor.BOLD}BattlePass Upgraded")
                    it.sendMessage(" ${ChatColor.GRAY}Your BattlePass is now Premium! This means you")
                    it.sendMessage(" ${ChatColor.GRAY}now have access to all of the premium challenges")
                    it.sendMessage(" ${ChatColor.GRAY}and their rewards. Use /bp to view new challenges.")
                    it.sendMessage("")
                }

            }


            if (premium) {
                sender.sendMessage("${ChatColor.GREEN}You granted ${ChatColor.WHITE}${profile.niceName} ${ChatColor.GREEN}the Premium BattlePass!")
            } else {
                sender.sendMessage("${ChatColor.GREEN}You revoked ${ChatColor.WHITE}${profile.niceName}${ChatColor.GREEN}'s Premium BattlePass!")
            }
        }
    }

    @Subcommand("reset")
    @CommandPermission("skywars.battlepass.admin.reset")
    fun handleReset(sender: ScalaPlayer, @Name("player") player: UUID) {
        Tasks.async {
            val profile = SkyWarsShared.instance.getProfile(player)

            profile.battlePass = BattlePass

            profile.save()

            sender.sendMessage("${ChatColor.GREEN}You have reset ${ChatColor.WHITE}${profile.niceName} ${ChatColor.GREEN}'s BattlePass progress.")

        }
    }

    @Subcommand("editor")
    @CommandPermission("skywars.battlepass.admin.editor")
    fun handleEditor(sender: ScalaPlayer) {
        BattlePassEditorMenu().openMenu(sender.bukkit())
    }
}