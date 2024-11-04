package gg.pixelarcade.skywars.chests

import gg.pixelarcade.skywars.SkyWarsGame
import gg.pixelarcade.skywars.chests.type.RefillType
import gg.pixelarcade.skywars.game.Game
import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.util.ChanceUtil.percent

import net.evilblock.cubed.util.bukkit.Tasks.async
import org.bukkit.Material
import org.bukkit.block.Chest
import org.bukkit.inventory.Inventory
import java.util.concurrent.ThreadLocalRandom

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.chests
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

                val itemsToAdd = ThreadLocalRandom.current().nextInt(MINIMUM_ITEMS, MAXIMUM_ITEMS)
                val inventory: Inventory = chest.blockInventory

                for (stage in this.stages) {
                    if (stage.refillType.isHigherThan(refillType)) continue

                    val items = if (value.equals("SPAWN", ignoreCase = true))
                        stage.spawnLoot
                    else
                        stage.middleLoot

                    for (i in 0 until itemsToAdd) {
                        val slot = ThreadLocalRandom.current().nextInt(27)

                        val randomIndex = ThreadLocalRandom.current().nextInt(items!!.size)
                        val chestItem = items[randomIndex]

                        if (percent(chestItem!!.chance)) inventory.setItem(slot, chestItem.item)
                    }
                }
            }
        }
    }

    companion object {
        const val MINIMUM_ITEMS: Int = 3
        const val MAXIMUM_ITEMS: Int = 6
    }
}
