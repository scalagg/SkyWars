package gg.pixelarcade.skywars.perk.impl.kitPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PyromancerPerk extends Perk {

    public PyromancerPerk() {
        super("Pyromancer Perk", Material.LAVA_BUCKET, 0, PerkRarity.COMMON);
    }

    @Override
    public void apply(Player player) {

        SkyWarsProfile profile = new SkyWarsProfile(player.getUniqueId());

        //-- Make sure that only people with the pyromancer kit selected can get this perk
        if (profile.getSelectedKit().equalsIgnoreCase("Pyromancer")) {

            //-- Give player potion effects
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 24, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 2));

            player.sendMessage(ChatColor.GOLD + "[Pyromancer Perk]" + ChatColor.DARK_PURPLE + " You Received Fire Resistance and Speed II!");
        }
    }
}
