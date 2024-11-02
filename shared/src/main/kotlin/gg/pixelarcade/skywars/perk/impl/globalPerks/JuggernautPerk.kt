package gg.pixelarcade.skywars.perk.impl.globalPerks

import gg.pixelarcade.skywars.perk.Perk
import gg.pixelarcade.skywars.perk.PerkRarity
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class JuggernautPerk : Perk(
    "Juggernaut Perk", Material.DIAMOND_HELMET, 0, PerkRarity.LEGENDARY,
    "Gain 10 seconds of Regeneration I after a kill."
) {
    override fun apply(player: Player) {
        player.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 10, 1))
    }

    override val isGlobalPerk: Boolean
        get() = true
}
