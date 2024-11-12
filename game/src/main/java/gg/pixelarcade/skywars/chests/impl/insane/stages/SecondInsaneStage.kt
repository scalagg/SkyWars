package gg.pixelarcade.skywars.chests.impl.insane.stages

import gg.pixelarcade.skywars.chests.ChestItem
import gg.pixelarcade.skywars.chests.ChestStage
import gg.pixelarcade.skywars.chests.type.RefillType
import io.netty.util.internal.ThreadLocalRandom
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import java.util.*

class SecondInsaneStage : ChestStage() {
    override val refillType: RefillType
        get() = RefillType.SECOND

    override val tablePrefix: String
        get() = "${refillType.name}-insane"
}
