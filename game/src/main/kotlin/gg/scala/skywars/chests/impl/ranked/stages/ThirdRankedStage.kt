package gg.scala.skywars.chests.impl.ranked.stages

import gg.scala.skywars.chests.ChestItem
import gg.scala.skywars.chests.ChestStage
import gg.scala.skywars.chests.type.RefillType
import io.netty.util.internal.ThreadLocalRandom
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

class ThirdRankedStage : ChestStage() {
    override val refillType: RefillType
        get() = RefillType.THIRD

    override val tablePrefix: String
        get() = "${refillType.name}-ranked"
}
