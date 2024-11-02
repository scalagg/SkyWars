package gg.pixelarcade.skywars.perk.impl.kitPerks

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.perk.Perk
import gg.pixelarcade.skywars.perk.PerkRarity
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class PaladinPerk : Perk(
    "Paladin Perk", Material.DIAMOND_SWORD, 0, PerkRarity.COMMON,
    "Gain Resistance II for 7 seconds on kill."
) {
    override fun apply(player: Player) {
        val profile = SkyWarsShared.instance.getProfile(player)

        //-- Make sure that only people with the Paladin kit can get this perk
        if (profile.selectedKit.equals("Paladin", ignoreCase = true)) {
            //-- Give the player resistance

            player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 7, 2))
            player.sendMessage(ChatColor.GOLD.toString() + "[Paladin Perk]" + ChatColor.DARK_PURPLE + " You Received Resistence II for 7 Seconds!")
        }
    }

    override val isKitPerk: Boolean
        get() = true
}
