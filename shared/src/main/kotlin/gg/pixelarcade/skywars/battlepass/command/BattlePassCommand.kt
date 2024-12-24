package gg.pixelarcade.skywars.battlepass.command

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.battlepass.menu.BattlePass2Menu
import gg.scala.commons.acf.annotation.CommandAlias
import gg.scala.commons.acf.annotation.Default
import gg.scala.commons.annotations.commands.AutoRegister
import gg.scala.commons.command.ScalaCommand
import org.bukkit.entity.Player

/**
 * @date 12/10/2024
 * @author Moose1301
 */
@AutoRegister
@CommandAlias("battlepass|bp|bpass")
object BattlePassCommand : ScalaCommand() {
    @Default
    fun execute(player: Player) {
        BattlePass2Menu(SkyWarsShared.instance.getProfile(player).battlePass).openMenu(player)
    }

}