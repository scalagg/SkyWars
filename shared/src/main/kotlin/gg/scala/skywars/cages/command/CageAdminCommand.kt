package gg.scala.skywars.cages.command

import gg.scala.skywars.cages.menu.CageViewAllMenu
import gg.scala.commons.acf.annotation.CommandAlias
import gg.scala.commons.acf.annotation.CommandPermission
import gg.scala.commons.acf.annotation.Default
import gg.scala.commons.annotations.commands.AutoRegister
import gg.scala.commons.command.ScalaCommand
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 8/25/2024
 */

@AutoRegister
@CommandAlias("cageadmin")
@CommandPermission("practice.command.cageadmin")
object CageAdminCommand : ScalaCommand()
{
    @Default
    fun help(player: Player) = CageViewAllMenu().openMenu(player)
}
