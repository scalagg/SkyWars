package gg.pixelarcade.skywars.perk.impl.purchasedPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RusherPerk extends Perk {

    public RusherPerk(){
        super("Rusher Perk", Material.BREWING_STAND, 1000, PerkRarity.RARE,
                "Gain 15s of Speed I when the game starts.");
    }

    @Override
    public void apply(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 1));
    }
}
