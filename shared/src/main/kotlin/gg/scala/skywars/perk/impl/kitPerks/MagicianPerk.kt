package gg.scala.skywars.perk.impl.kitPerks

import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.perk.Perk
import gg.scala.skywars.perk.PerkRarity
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

class MagicianPerk : Perk(
    "Magician Perk", Material.POTION, 0, PerkRarity.COMMON,
    "Gain a random positive potion effect on kill, or 10 levels."
) {
    override fun apply(player: Player) {
        val profile = SkyWarsShared.instance.getProfile(player)

        //-- Make sure that only people with the Magician kit can get this perk
        if (profile.selectedKit.equals("Magician", ignoreCase = true)) {
            // 1/10 chance the player receives XP

            val rand1 = Random()
            val xpChance = rand1.nextInt(10)

            if (xpChance != 1) {
                // Though it's a bit redundant we have to make an entire array of potion id's that are exclusively positive

                val posPotionEffects = intArrayOf(1, 3, 5, 6, 8, 10, 11, 12, 14, 21, 22)

                //-- Create a random positive potion effect
                val rand2 = Random()
                val potionID = posPotionEffects[rand2.nextInt(posPotionEffects.size)]
                val effect = PotionEffectType.getById(potionID)

                //-- Give the player a level 1 potion effect for 15 seconds
                player.addPotionEffect(PotionEffect(effect, 15, 1))
                player.sendMessage(ChatColor.GOLD.toString() + "[Magician Perk]" + ChatColor.DARK_PURPLE + " You Received " + effect.name + " For 15 Seconds!")
            } else {
                //-- Give the player their Exp levels

                val xp = player.level + 10
                player.level = xp
                player.sendMessage(ChatColor.GOLD.toString() + "[Magician Perk]" + ChatColor.DARK_PURPLE + " You Received 10 Experience Levels!")
            }
        }
    }

    override val isKitPerk: Boolean
        get() = true
}
