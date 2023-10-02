package xyz.refinedev.skywars.kit.enums;

import lombok.Getter;
import org.bukkit.ChatColor;

@Getter
public enum Rarity {

    COMMON("Common", ChatColor.DARK_GREEN),
    RARE("Rare", ChatColor.BLUE),
    LEGENDARY("Legendary", ChatColor.GOLD),
    MYTHICAL("Mythical", ChatColor.DARK_PURPLE);

    private final String name;
    private final ChatColor color;

    Rarity(String name, ChatColor color) {
        this.name = name;
        this.color = color;
    }
}