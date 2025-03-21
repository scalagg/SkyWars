package gg.scala.skywars.chests

import gg.scala.skywars.SkyWarsGame
import gg.scala.skywars.chests.type.RefillType
import gg.scala.skywars.game.Game
import gg.scala.skywars.game.state.GameType
import net.evilblock.cubed.util.bukkit.Tasks.async
import org.bukkit.Material
import org.bukkit.block.Chest
import org.bukkit.inventory.Inventory
import java.util.concurrent.ThreadLocalRandom

/**
 * @author alfie
 * @project Skywars / gg.scala.game.chests
 * @date 27/09/2023 / 20:18
 */

abstract class LootableChest {
    abstract val gameType: GameType
    abstract val stages: List<ChestStage>

    fun fillChests(game: Game, refillType: RefillType) {
        if (!game.active) return

        val map = SkyWarsGame.gameInstance.gameHandler.map
        async {
            for ((key, value) in map.chests) {
                val block = map
                    .bounds.world.getBlockAt(key)

                if (refillType == RefillType.SECOND && value.equals("MIDDLE", ignoreCase = true)
                    && (!block.hasMetadata("placedByGame"))
                ) continue

                if (refillType == RefillType.THIRD && value.equals("MIDDLE", ignoreCase = true)
                    && block.type == Material.AIR
                ) block.type = Material.CHEST

                if (block.state !is Chest) continue

                val chest = block.state as Chest

                val inventory: Inventory = chest.blockInventory

                for (stage in this.stages) {
                    if (stage.refillType.isHigherThan(refillType)) continue

                    val items = if (value.equals("SPAWN", ignoreCase = true))
                        stage.getSpawnLoot()
                    else
                        stage.getMiddleLoot()

                    items.forEach { item ->
                        val slot = ThreadLocalRandom.current().nextInt(27)
                        inventory.setItem(slot, item)
                    }

                }
            }
        }
    }

}
