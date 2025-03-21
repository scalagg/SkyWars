package gg.scala.skywars.perk.impl.kitPerks

import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.perk.Perk
import gg.scala.skywars.perk.PerkRarity
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class PyromancerPerk : Perk(
    "Pyromancer Perk", Material.LAVA_BUCKET, 0, PerkRarity.COMMON,
    "Upon kill obtain 24 seconds of Fire Resistance and 10 seconds of Speed II."
) {
    override fun apply(player: Player) {
        val profile = SkyWarsShared.instance.getProfile(player)

        //-- Make sure that only people with the pyromancer kit selected can get this perk
        if (profile.selectedKit.equals("Pyromancer", ignoreCase = true)) {
            //-- Give player potion effects

            player.addPotionEffect(PotionEffect(PotionEffectType.FIRE_RESISTANCE, 24, 1))
            player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 10, 2))

            player.sendMessage(ChatColor.GOLD.toString() + "[Pyromancer Perk]" + ChatColor.DARK_PURPLE + " You Received Fire Resistance and Speed II!")
        }
    }

    override val isKitPerk: Boolean
        get() = true
}
