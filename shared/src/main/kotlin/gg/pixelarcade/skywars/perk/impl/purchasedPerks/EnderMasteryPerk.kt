package gg.pixelarcade.skywars.perk.impl.purchasedPerks

import gg.pixelarcade.skywars.perk.Perk
import gg.pixelarcade.skywars.perk.PerkRarity
import org.bukkit.Material
import org.bukkit.entity.Player

//TODO: Not sure how to implement this yet
class EnderMasteryPerk : Perk(
    "Ender Mastery Perk", Material.ENDER_PEARL, 1000, PerkRarity.RARE,
    "Reduces damage taken with enderpearls by 100%"
) {
    override fun apply(player: Player) {
    }
}
