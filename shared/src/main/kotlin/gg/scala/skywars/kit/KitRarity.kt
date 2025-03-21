package gg.scala.skywars.kit



import org.bukkit.ChatColor

enum class KitRarity(val displayName: String, val color: ChatColor) {
    COMMON("Common", ChatColor.DARK_GREEN),
    RARE("Rare", ChatColor.BLUE),
    LEGENDARY("Legendary", ChatColor.GOLD),
    MYTHICAL("Mythical", ChatColor.DARK_PURPLE);

}