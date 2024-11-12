package gg.pixelarcade.skywars.chests.impl.casual.stages

import gg.pixelarcade.skywars.SkyWarsGame
import gg.pixelarcade.skywars.chests.ChestItem
import gg.pixelarcade.skywars.chests.ChestStage
import gg.pixelarcade.skywars.chests.lootable.LootTableDataSync
import gg.pixelarcade.skywars.chests.type.RefillType
import io.netty.util.internal.ThreadLocalRandom
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.chests.stage.test
 * @date 17/10/2023 / 16:00
 */
class FirstCasualStage : ChestStage() {
    override val refillType: RefillType
        get() = RefillType.FIRST


    override val tablePrefix: String
        get() = "${refillType.name}-casual"


}