package gg.scala.skywars.chests.impl.casual.stages

import gg.scala.skywars.chests.ChestItem
import gg.scala.skywars.chests.ChestStage
import gg.scala.skywars.chests.lootable.LootTableDataSync
import gg.scala.skywars.chests.type.RefillType
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.concurrent.ThreadLocalRandom

class SecondCasualStage : ChestStage() {
    override val refillType: RefillType
        get() = RefillType.SECOND
    override val tablePrefix: String
        get() = "${refillType.name}-casual"
}
