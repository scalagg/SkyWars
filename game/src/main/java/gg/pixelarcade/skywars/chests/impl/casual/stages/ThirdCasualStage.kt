package gg.pixelarcade.skywars.chests.impl.casual.stages

import gg.pixelarcade.skywars.chests.ChestItem
import gg.pixelarcade.skywars.chests.ChestStage
import gg.pixelarcade.skywars.chests.lootable.LootTableDataSync
import gg.pixelarcade.skywars.chests.type.RefillType
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
