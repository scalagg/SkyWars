package gg.scala.skywars.perk.impl.kitPerks

import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.perk.Perk
import gg.scala.skywars.perk.PerkRarity
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class HoundPerk : Perk(
    "Hound Perk", Material.EGG, 0, PerkRarity.COMMON,
    "Spawns a wolf and get 16 steak on kill."
) {
    override fun apply(player: Player) {
        val profile = SkyWarsShared.instance.getProfile(player)

        //-- Make sure that only people with the Magician kit can get this perk
        if (profile.selectedKit.equals("Hound", ignoreCase = true)) {
            //-- Spawn a wolf at the player's location

            val world = Bukkit.getWorld(player.uniqueId)
            world.spawnEntity(player.location, EntityType.WOLF)

            //-- Give the player Steak
            player.inventory.addItem(ItemStack(Material.COOKED_BEEF, 16))

            player.sendMessage(ChatColor.GOLD.toString() + "[Hound Perk]" + ChatColor.DARK_PURPLE + " You Received a New Best Friend!")
        }
    }

    override val isKitPerk: Boolean
        get() = true
}