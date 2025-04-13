package gg.scala.skywars.perk.impl.kitPerks

import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.perk.Perk
import gg.scala.skywars.perk.PerkRarity
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player

class SniperPerk
    : Perk(
    "Sniper Perk", Material.BOW, 0, PerkRarity.COMMON,
    "Archery Bow gains 1 level of Power after each kill."
) {
    override fun apply(player: Player) {
        val profile = SkyWarsShared.instance.getProfile(player)

        //-- Make sure that only people with the Sniper kit can get this perk
        if (profile.selectedKit.equals("Sniper", ignoreCase = true)) {
            //-- Variables

            val inventory = player.inventory.contents
            val enchantment = Enchantment.ARROW_DAMAGE
            var powerLevel = 1

            //-- Iterate through the player's inventory
            for (inventorySlot in inventory) {
                //-- Make sure we only grab a bow item

                if (inventorySlot.type == Material.BOW) {
                    if (inventorySlot.containsEnchantment(enchantment)) {
                        powerLevel = inventorySlot.getEnchantmentLevel(enchantment)
                        inventorySlot.addEnchantment(enchantment, powerLevel++)
                    } else {
                        inventorySlot.addEnchantment(enchantment, powerLevel)
                    }
                }
            }

            //-- Give the player back their items
            player.inventory.contents = inventory
            player.sendMessage(ChatColor.GOLD.toString() + "[Sniper Perk]" + ChatColor.DARK_PURPLE + " +1 Power Level!")
        }
    }

    override val isKitPerk: Boolean
        get() = true
}