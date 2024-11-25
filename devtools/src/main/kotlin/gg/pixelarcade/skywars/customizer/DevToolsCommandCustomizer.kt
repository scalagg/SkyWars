package gg.pixelarcade.skywars.customizer

import gg.pixelarcade.skywars.cages.command.SchemaCageCommandCustomizers
import gg.scala.commons.annotations.commands.customizer.CommandManagerCustomizer
import gg.scala.commons.command.ScalaCommandManager

/**
 * @author GrowlyX
 * @since 8/25/2024
 */
object DevToolsCommandCustomizer
{
    @CommandManagerCustomizer
    fun customize(manager: ScalaCommandManager) = SchemaCageCommandCustomizers.customize(manager)
}
