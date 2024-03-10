package gg.pixelarcade.skywars.perk.impl.purchasedPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

//TODO: Not sure how to implement this yet

public class EnvironmentalExpertPerk extends Perk {

    public EnvironmentalExpertPerk() {
        super("Environmental Expert Perk", Material.LEATHER_CHESTPLATE, 1000, PerkRarity.RARE,
                "Reduces environmental damage by 20%");
    }

    /*

    @Override
    public void apply(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10, 1));
    }
     */

    @Override
    public void apply(Player player) {

    }
}
