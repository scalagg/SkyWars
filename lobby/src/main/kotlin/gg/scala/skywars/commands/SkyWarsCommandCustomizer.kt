package gg.scala.skywars.commands

import gg.scala.skywars.cages.command.SchemaCageCommandCustomizers
import gg.scala.commons.annotations.commands.customizer.CommandManagerCustomizer
import gg.scala.commons.command.ScalaCommandManager

object SkyWarsCommandCustomizer
{
    @CommandManagerCustomizer
    fun customize(manager: ScalaCommandManager) = SchemaCageCommandCustomizers.customize(manager)
}
