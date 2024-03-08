package gg.pixelarcade.skywars.perk;

import lombok.Data;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Data
public abstract class Perk {

    private final String name;
    private final Material displayMaterial;
    private final int cost;
    private final PerkRarity rarity;
    private String description;

    public boolean isKitPerk() {
        return false;
    }
    public boolean isGlobalPerk() {
        return false;
    }

    public abstract void apply(Player player);

}