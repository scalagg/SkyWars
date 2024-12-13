package gg.pixelarcade.skywars.battlepass.command

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.battlepass.BattlePass
import gg.pixelarcade.skywars.battlepass.menu.BattlePass2Menu
import gg.pixelarcade.skywars.battlepass.menu.editor.BattlePassEditorMenu
import gg.scala.commons.acf.annotation.CommandAlias
import gg.scala.commons.acf.annotation.CommandPermission
import gg.scala.commons.acf.annotation.Default
import gg.scala.commons.acf.annotation.Name
import gg.scala.commons.acf.annotation.Subcommand
import gg.scala.commons.annotations.commands.AutoRegister
import gg.scala.commons.issuer.ScalaPlayer
import net.evilblock.cubed.util.bukkit.Tasks
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.*

/**
 * @date 12/10/2024
 * @author Moose1301
 */
@AutoRegister
@CommandAlias("battlepass|bp|bpass")
object BattlePassCommand {
    @Default
    fun execute(player: Player) {
        BattlePass2Menu(SkyWarsShared.instance.getProfile(player).battlePass).openMenu(player)
    }

}