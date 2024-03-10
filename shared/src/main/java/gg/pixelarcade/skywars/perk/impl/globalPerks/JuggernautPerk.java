package gg.pixelarcade.skywars.perk.impl.globalPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JuggernautPerk extends Perk {
    public JuggernautPerk() {
        super("Juggernaut Perk", Material.DIAMOND_HELMET, 0, PerkRarity.LEGENDARY,
                "Gain 10 seconds of Regeneration I after a kill.");
    }

    @Override
    public void apply(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10, 1));
    }

    @Override
    public boolean isGlobalPerk() {
        return true;
    }
}
