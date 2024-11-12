package gg.pixelarcade.skywars.chests.lootable.model

import gg.pixelarcade.skywars.cages.Cage
import gg.pixelarcade.skywars.cages.sync.CageDataSync
import gg.pixelarcade.skywars.chests.lootable.LootTableDataSync
import net.evilblock.cubed.util.math.Chance.percent


/**
 * @date 11/11/2024
 * @author Moose1301
 */
data class LootTable(
    val id: String, var displayName: String,
    val items: MutableList<LootTableItem> = mutableListOf(),
    var minItems: Int = 1,
    var maxItems: Int = 1,
) {


    fun editAndSave(block: LootTable.() -> Unit)
    {
        block(this)

        val cached = LootTableDataSync.cached()
        cached.tables[id] = this
        LootTableDataSync.sync(cached)
    }
    fun generateRewards(): List<LootTableItem> {
        if (this.items.isEmpty()) return ArrayList()

        var amountOfRewards: Int = minItems
        val rewardsRange: Int = minItems - maxItems
        var extraRewardChance = 10.0

        for (i in 0 until rewardsRange) {
            if (percent(extraRewardChance)) {
                amountOfRewards++
                extraRewardChance /= 10.0
            } else {
                break
            }
        }
        val winnings: MutableList<LootTableItem> = ArrayList()
        amountOfRewards = amountOfRewards.coerceAtMost(maxItems)

        winnings@
        while (winnings.size < amountOfRewards) {
            for (reward in items) {
                if (percent(reward.chance)) {
                    winnings.add(reward)

                    if (winnings.size >= amountOfRewards) {
                        break@winnings
                    }
                }
            }
        }

        return winnings
    }
}
