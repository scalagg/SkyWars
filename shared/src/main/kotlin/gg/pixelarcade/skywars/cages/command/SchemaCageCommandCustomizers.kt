package gg.pixelarcade.skywars.cages.command

import gg.pixelarcade.skywars.cages.SchemaCage
import gg.pixelarcade.skywars.cages.sync.SchemaCageDataSync
import gg.pixelarcade.skywars.chests.lootable.LootTableDataSync
import gg.pixelarcade.skywars.chests.lootable.command.LootTableCommand
import gg.pixelarcade.skywars.chests.lootable.model.LootTable
import gg.scala.commons.acf.ConditionFailedException
import gg.scala.commons.command.ScalaCommandManager

/**
 * @author GrowlyX
 * @since 8/25/2024
 */
object SchemaCageCommandCustomizers
{
    fun customize(manager: ScalaCommandManager)
    {
        manager.commandCompletions.registerCompletion("schemacages") {
            SchemaCageDataSync.cached().schemas.keys
        }

        manager.commandContexts.registerContext(SchemaCage::class.java) {
            val arg = it.popFirstArg()

            SchemaCageDataSync.cached().schemas[arg.lowercase()]
                ?: throw ConditionFailedException("No schema cage by that name was found.")
        }

        manager.commandContexts.registerContext(LootTable::class.java) {
            val arg = it.popFirstArg()

            LootTableDataSync.cached().tables[arg.lowercase()]
                ?: throw ConditionFailedException("No Loot Table by that name was found.")
        }
    }
}
