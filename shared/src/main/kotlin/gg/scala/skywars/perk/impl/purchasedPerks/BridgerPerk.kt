package gg.scala.skywars.perk.impl.purchasedPerks

import de.slikey.effectlib.util.RandomUtils
import gg.scala.skywars.perk.Perk
import gg.scala.skywars.perk.PerkRarity
import io.netty.util.internal.ThreadLocalRandom
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

class BridgerPerk : Perk(
    "Bridger Perk", Material.WOOD, 1000, PerkRarity.RARE,
    "Grants 25% chance to not consume placeable blocks."
) {
    override fun apply(player: Player) {
    }

    fun apply(player: Player, block: Block) {
        val chance = ThreadLocalRandom.current().nextInt(1, 4)

        if(chance != 4)
            return

        player.inventory.addItem(ItemStack(block.type, 1, block.data.toShort()))
    }
}
