package gg.scala.skywars.perk


import org.bukkit.ChatColor


enum class PerkRarity(val displayName: String, val color: ChatColor) {
    COMMON("Common", ChatColor.DARK_GREEN),
    RARE("Rare", ChatColor.BLUE),
    LEGENDARY("Legendary", ChatColor.GOLD),
    MYTHICAL("Mythical", ChatColor.DARK_PURPLE)
}
