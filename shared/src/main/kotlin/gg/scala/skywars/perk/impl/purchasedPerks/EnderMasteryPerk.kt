package gg.scala.skywars.perk.impl.purchasedPerks

import gg.scala.skywars.perk.Perk
import gg.scala.skywars.perk.PerkRarity
import org.bukkit.Material
import org.bukkit.entity.Player

class EnderMasteryPerk : Perk(
    "Ender Mastery Perk", Material.ENDER_PEARL, 1000, PerkRarity.RARE,
    "Reduces damage taken with enderpearls by 100%"
) {
    override fun apply(player: Player) {
    }
}
