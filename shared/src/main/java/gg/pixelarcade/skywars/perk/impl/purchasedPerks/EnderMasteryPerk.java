package gg.pixelarcade.skywars.perk.impl.purchasedPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;

//TODO: Not sure how to implement this yet
public class EnderMasteryPerk extends Perk {

    public EnderMasteryPerk(){
        super("Ender Mastery Perk", Material.ENDER_PEARL, 1000, PerkRarity.RARE,
                "Reduces damage taken with enderpearls by 100%");
    }

    @Override
    public void apply(Player player) {

    }
}
