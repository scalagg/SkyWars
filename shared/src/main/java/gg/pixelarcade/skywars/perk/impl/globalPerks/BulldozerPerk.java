package gg.pixelarcade.skywars.perk.impl.globalPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BulldozerPerk extends Perk {

    public BulldozerPerk() {
        super("Bulldozer Perk", Material.ANVIL, 0, PerkRarity.LEGENDARY,
                "Gain 5 seconds of Strength I after a kill.");
    }

    @Override
    public void apply(Player player){

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5, 1));

    }

    @Override
    public boolean isGlobalPerk() {
        return true;
    }
}
