package gg.pixelarcade.skywars.chests.impl.ranked.stages

import gg.pixelarcade.skywars.chests.ChestItem
import gg.pixelarcade.skywars.chests.ChestStage
import gg.pixelarcade.skywars.chests.type.RefillType
import io.netty.util.internal.ThreadLocalRandom
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

class SecondRankedStage : ChestStage() {
    override val refillType: RefillType
        get() = RefillType.SECOND


    override val tablePrefix: String
        get() = "${refillType.name}-ranked"
}
