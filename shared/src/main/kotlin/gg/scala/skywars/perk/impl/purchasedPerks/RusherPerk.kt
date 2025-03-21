package gg.scala.skywars.perk.impl.purchasedPerks

import gg.scala.skywars.perk.Perk
import gg.scala.skywars.perk.PerkRarity
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class RusherPerk : Perk(
    "Rusher Perk", Material.BREWING_STAND, 1000, PerkRarity.RARE,
    "Gain 15s of Speed I when the game starts."
) {
    override fun apply(player: Player) {
        player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 15, 1))
    }
}
