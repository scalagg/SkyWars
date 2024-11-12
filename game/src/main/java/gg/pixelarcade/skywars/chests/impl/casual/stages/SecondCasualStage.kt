package gg.pixelarcade.skywars.chests.impl.casual.stages

import gg.pixelarcade.skywars.chests.ChestItem
import gg.pixelarcade.skywars.chests.ChestStage
import gg.pixelarcade.skywars.chests.lootable.LootTableDataSync
import gg.pixelarcade.skywars.chests.type.RefillType
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
