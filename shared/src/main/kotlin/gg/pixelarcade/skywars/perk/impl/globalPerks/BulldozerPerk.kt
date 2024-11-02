package gg.pixelarcade.skywars.perk.impl.globalPerks

import gg.pixelarcade.skywars.perk.Perk
import gg.pixelarcade.skywars.perk.PerkRarity
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class BulldozerPerk : Perk(
    "Bulldozer Perk", Material.ANVIL, 0, PerkRarity.LEGENDARY,
    "Gain 5 seconds of Strength I after a kill."
) {
    override fun apply(player: Player) {
        player.addPotionEffect(PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5, 1))
    }

    override val isGlobalPerk: Boolean
        get() = true
}
