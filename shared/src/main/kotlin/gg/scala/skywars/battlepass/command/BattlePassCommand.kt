package gg.scala.skywars.battlepass.command

import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.battlepass.menu.BattlePass2Menu
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
object BattlePassCommand : ScalaCommand() {

    @CommandAlias("battlepass|bp|bpass")
    fun onBattlePass(player: Player) {
        BattlePass2Menu(SkyWarsShared.instance.getProfile(player).battlePass).openMenu(player)
    }

}