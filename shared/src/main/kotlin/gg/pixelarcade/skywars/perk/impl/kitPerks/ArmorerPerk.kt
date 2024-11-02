package gg.pixelarcade.skywars.perk.impl.kitPerks

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.perk.Perk
import gg.pixelarcade.skywars.perk.PerkRarity
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player

class ArmorerPerk : Perk(
    "Armorer Perk", Material.IRON_CHESTPLATE, 0, PerkRarity.COMMON,
    "Armorer's armor gains 1 level of Protection after each kill."
) {
    override fun apply(player: Player) {
        val profile = SkyWarsShared.instance.getProfile(player)

        //-- Make sure that only people with the Armorer kit selected can get this perk
        if (profile.selectedKit.equals("Armorer", ignoreCase = true)) {
            //-- Variables

            val armor = player.inventory.armorContents
            val enchantment = Enchantment.PROTECTION_ENVIRONMENTAL
            var protectionLevel = 1

            //-- Iterate through each armor piece
            for (armorPiece in armor) {
                //TODO: Make it so that only the armor you receive from the Armorer kit gets the upgrade

                if (armorPiece.containsEnchantment(enchantment)) {
                    protectionLevel = armorPiece.getEnchantmentLevel(enchantment)
                    armorPiece.addEnchantment(enchantment, protectionLevel++)
                } else {
                    armorPiece.addEnchantment(enchantment, protectionLevel)
                }
            }

            //-- Set the player's new armor
            player.inventory.armorContents = armor
            player.sendMessage(ChatColor.GOLD.toString() + "[Armorer Perk]" + ChatColor.DARK_PURPLE + " +1 Prot Level!")
        }
    }

    override val isKitPerk: Boolean
        get() = true
}
