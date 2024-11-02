package gg.pixelarcade.skywars.perk.impl.kitPerks

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.perk.Perk
import gg.pixelarcade.skywars.perk.PerkRarity
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class HealerPerk : Perk(
    "Healer Perk", Material.GOLDEN_APPLE, 0, PerkRarity.COMMON,
    "Upon kill, max health increases by 4HP, heal for 8HP and gain a Golden Apple."
) {
    override fun apply(player: Player) {
        val profile = SkyWarsShared.instance.getProfile(player)

        //-- Make sure that only people with the healer kit selected can get this perk
        if (profile.selectedKit.equals("Healer", ignoreCase = true)) {
            //-- Set player's max health +4HP

            val maxHealth = player.maxHealth + 4
            player.maxHealth = maxHealth

            //-- Heal player for 8HP
            val newHealth = player.health + 8
            player.health = newHealth

            //-- Give player golden apple
            player.inventory.addItem(ItemStack(Material.GOLDEN_APPLE))
            player.sendMessage(ChatColor.GOLD.toString() + "[Healer Perk]" + ChatColor.DARK_PURPLE + " You Have Been Healed! +1 Golden Apple!")
        }
    }

    override val isKitPerk: Boolean
        get() = true
}
