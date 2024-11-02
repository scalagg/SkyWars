package gg.pixelarcade.skywars.perk.impl.purchasedPerks

import gg.pixelarcade.skywars.perk.Perk
import gg.pixelarcade.skywars.perk.PerkRarity
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class LastStandPerk : Perk(
    "Last Stand Perk", Material.APPLE, 1000, PerkRarity.RARE,
    "Gain 10 seconds of Resistance when below 5 hearts."
) {
    override fun apply(player: Player) {
        player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10, 1))
    }
}
