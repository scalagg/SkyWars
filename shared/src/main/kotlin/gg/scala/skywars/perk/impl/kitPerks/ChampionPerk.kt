package gg.scala.skywars.perk.impl.kitPerks

import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.perk.Perk
import gg.scala.skywars.perk.PerkRarity
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player

class ChampionPerk : Perk(
    "Champion Perk", Material.DIAMOND_SWORD, 0, PerkRarity.COMMON,
    "Champion Sword gains 1 level of Sharpness after each kill."
) {
    override fun apply(player: Player) {
        val profile = SkyWarsShared.instance.getProfile(player)

        //-- Make sure that only people with the champion kit selected can get this perk
        if (profile.selectedKit.equals("Champion", ignoreCase = true)) {
            //-- Variables

            val inventory = player.inventory.contents
            val enchantment = Enchantment.DAMAGE_ALL
            var sharpnessLevel: Int

            //-- Iterate through the player's inventory
            for (inventorySlot in inventory) {
                //-- Make sure we only grab the champion sword

                if (inventorySlot.type == Material.DIAMOND_SWORD && inventorySlot.getEnchantmentLevel(enchantment) >= 2) {
                    sharpnessLevel = inventorySlot.getEnchantmentLevel(enchantment) + 1
                    inventorySlot.addEnchantment(enchantment, sharpnessLevel)
                }
            }

            //-- Give the player back their items
            player.inventory.contents = inventory
            player.sendMessage(ChatColor.GOLD.toString() + "[Champion Perk]" + ChatColor.DARK_PURPLE + " +1 Sharpness Level!")
        }
    }

    override val isKitPerk: Boolean
        get() = true
}
