package gg.pixelarcade.skywars.perk

import lombok.Getter
import org.bukkit.ChatColor

@Getter
enum class PerkRarity(val displayName: String, val color: ChatColor) {
    COMMON("Common", ChatColor.DARK_GREEN),
    RARE("Rare", ChatColor.BLUE),
    LEGENDARY("Legendary", ChatColor.GOLD),
    MYTHICAL("Mythical", ChatColor.DARK_PURPLE)
}
