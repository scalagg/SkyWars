package gg.scala.skywars.chests.impl.casual.stages

import gg.scala.skywars.chests.ChestItem
import gg.scala.skywars.chests.ChestStage
import gg.scala.skywars.chests.lootable.LootTableDataSync
import gg.scala.skywars.chests.type.RefillType
import io.netty.util.internal.ThreadLocalRandom
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ThirdCasualStage : ChestStage() {
    override val refillType: RefillType
        get() = RefillType.THIRD

    override val tablePrefix: String
        get() = "${refillType.name}-casual"
}
