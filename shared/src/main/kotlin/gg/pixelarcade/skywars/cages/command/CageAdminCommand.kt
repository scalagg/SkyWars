package gg.pixelarcade.skywars.cages.command

import gg.pixelarcade.skywars.cages.menu.CageViewAllMenu
import gg.scala.commons.acf.annotation.CommandAlias
import gg.scala.commons.acf.annotation.CommandPermission
import gg.scala.commons.acf.annotation.Default
import gg.scala.commons.command.ScalaCommand
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 8/25/2024
 */

@CommandAlias("cageadmin")
@CommandPermission("practice.command.cageadmin")
object CageAdminCommand : ScalaCommand()
{
    @Default
    fun help(player: Player) = CageViewAllMenu().openMenu(player)
}
