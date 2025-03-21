package gg.scala.skywars.perk.impl.kitPerks

import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.perk.Perk
import gg.scala.skywars.perk.PerkRarity
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ScoutPerk : Perk(
    "Scout Perk", Material.FEATHER, 0, PerkRarity.COMMON,
    "Obtain 1 Ender Pearl after each kill"
) {
    override fun apply(player: Player) {
        val profile = SkyWarsShared.instance.getProfile(player)

        //-- Make sure that only people with the scout kit selected can get this perk
        if (profile.selectedKit.equals("Scout", ignoreCase = true)) {
            player.inventory.addItem(ItemStack(Material.ENDER_PEARL))
            player.sendMessage(ChatColor.GOLD.toString() + "[Scout Perk]" + ChatColor.DARK_PURPLE + " +1 Ender Pearl!")
        }
    }

    override val isKitPerk: Boolean
        get() = true
}