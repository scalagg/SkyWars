package gg.pixelarcade.skywars.admin.commands

import gg.pixelarcade.skywars.cages.command.SchemaCageCommandCustomizers
import gg.scala.commons.annotations.commands.customizer.CommandManagerCustomizer
import gg.scala.commons.command.ScalaCommandManager

object SkyWarsCommandCustomizer
{
    @CommandManagerCustomizer
    fun customize(manager: ScalaCommandManager) = SchemaCageCommandCustomizers.customize(manager)
}
