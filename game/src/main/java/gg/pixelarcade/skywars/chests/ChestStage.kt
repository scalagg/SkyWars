package gg.pixelarcade.skywars.chests

import gg.pixelarcade.skywars.chests.lootable.LootTableDataSync
import gg.pixelarcade.skywars.chests.lootable.model.LootTable
import gg.pixelarcade.skywars.chests.type.RefillType
import org.bukkit.inventory.ItemStack

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.chests
 * @date 17/10/2023 / 15:50
 */
abstract class ChestStage {
    abstract val refillType: RefillType
    abstract val tablePrefix: String

    fun getSpawnLoot(): List<ItemStack> {
        return getLootFromTable(LootTableDataSync.cached().tables["$tablePrefix-spawn"])

    }
    fun getMiddleLoot(): List<ItemStack> {
        return getLootFromTable(LootTableDataSync.cached().tables["$tablePrefix-middle"])
    }


    protected fun getLootFromTable(lootTable: LootTable?): List<ItemStack> {
        if(lootTable == null)
            return ArrayList()

        return lootTable.generateRewards().map { it.stack }
    }
}
