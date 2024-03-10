package gg.pixelarcade.skywars.perk.impl.kitPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PaladinPerk extends Perk {

    public PaladinPerk() {
        super("Paladin Perk", Material.DIAMOND_SWORD, 0, PerkRarity.COMMON,
                "Gain Resistance II for 7 seconds on kill.");
    }

    @Override
    public void apply(Player player) {

        SkyWarsProfile profile = new SkyWarsProfile(player.getUniqueId());

        //-- Make sure that only people with the Paladin kit can get this perk
        if (profile.getSelectedKit().equalsIgnoreCase("Paladin")){

            //-- Give the player resistance
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 7, 2));
            player.sendMessage(ChatColor.GOLD + "[Paladin Perk]" + ChatColor.DARK_PURPLE + " You Received Resistence II for 7 Seconds!");
        }
    }

    @Override
    public boolean isKitPerk(){
        return true;
    }
}
