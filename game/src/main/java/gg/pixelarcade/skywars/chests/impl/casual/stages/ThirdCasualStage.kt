package gg.pixelarcade.skywars.chests.impl.casual.stages

import gg.pixelarcade.skywars.chests.ChestItem
import gg.pixelarcade.skywars.chests.ChestStage
import gg.pixelarcade.skywars.chests.type.RefillType
import io.netty.util.internal.ThreadLocalRandom
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material

class ThirdCasualStage : ChestStage() {
    override val refillType: RefillType
        get() = RefillType.THIRD

    override val spawnLoot: List<ChestItem>
        get() = listOf(
            ChestItem(
                ItemBuilder(
                    Material.ENDER_PEARL,
                    ThreadLocalRandom.current().nextInt(1, 6)
                ).build(), 100.0
            )
        )

    override val middleLoot: List<ChestItem>
        get() = listOf(
            ChestItem(
                ItemBuilder(
                    Material.ENDER_PEARL,
                    ThreadLocalRandom.current().nextInt(1, 6)
                ).build(), 100.0
            )
        )
}
