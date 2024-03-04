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

    public abstract void apply(Player player);

}