package gg.scala.skywars.perk.impl.kitPerks

import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.perk.Perk
import gg.scala.skywars.perk.PerkRarity
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.EnchantmentStorageMeta
import java.util.concurrent.ThreadLocalRandom

class BlacksmithPerk : Perk(
    "Blacksmith Perk", Material.ANVIL, 0, PerkRarity.COMMON,
    "Every kill gains 3 levels and drops a random enchanted book"
) {
    override fun apply(player: Player) {
        val profile = SkyWarsShared.instance.getProfile(player)

        //-- Make sure that only people with the armorer kit selected can get this perk
        if (profile.selectedKit.equals("Blacksmith", ignoreCase = true)) {
            //-- Recycled from BlacksmithKit

            val enchantmentLevel = ThreadLocalRandom.current().nextInt(3) + 1
            val enchantment = Enchantment.values()[ThreadLocalRandom.current()
                .nextInt(Enchantment.values().size)]
            val enchantedBook = ItemStack(Material.ENCHANTED_BOOK)
            val meta = enchantedBook.itemMeta as EnchantmentStorageMeta
            meta.addStoredEnchant(enchantment, enchantmentLevel, true)
            enchantedBook.setItemMeta(meta)
            player.inventory.addItem(enchantedBook)

            //-- Give the player 3 Exp levels
            val xp = player.level + 3
            player.level = xp
            player.sendMessage(ChatColor.GOLD.toString() + "[Blacksmith Perk]" + ChatColor.DARK_PURPLE + " You Received 3 Experience Levels and +1 Enchantment Book!")
        }
    }

    override val isKitPerk: Boolean
        get() = true
}
