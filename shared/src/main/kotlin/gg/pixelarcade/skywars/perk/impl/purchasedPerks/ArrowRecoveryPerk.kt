package gg.pixelarcade.skywars.perk.impl.purchasedPerks

import gg.pixelarcade.skywars.perk.Perk
import gg.pixelarcade.skywars.perk.PerkRarity
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

class ArrowRecoveryPerk : Perk(
    "Arrow Recovery Perk", Material.ARROW, 1000, PerkRarity.RARE,
    "25% chance of getting your arrow back on a bow hit."
) {
    override fun apply(player: Player) {
        val random = Random()
        val chance = random.nextInt(1, 4)

        if (chance == 4) {
            player.inventory.addItem(
                ItemStack(Material.ARROW)
            )
        }
    }
}
