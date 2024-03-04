package gg.pixelarcade.skywars.perk.impl.kitPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.Random;

public class MagicianPerk extends Perk {

    public MagicianPerk() {
        super("Magician Perk", Material.POTION, 0, PerkRarity.COMMON);
    }

    @Override
    public void apply(Player player) {

        SkyWarsProfile profile = new SkyWarsProfile(player.getUniqueId());

        //-- Make sure that only people with the Magician kit can get this perk
        if (profile.getSelectedKit().equalsIgnoreCase("Magician")){

            // 1/10 chance the player receives XP
            Random rand1 = new Random();
            int xpChance = rand1.nextInt(10);

            if (xpChance != 1) {

                // Though it's a bit redundant we have to make an entire array of potion id's that are exclusively positive
                int[] posPotionEffects = {1, 3, 5, 6, 8, 10, 11, 12, 14, 21, 22};

                //-- Create a random positive potion effect
                Random rand2 = new Random();
                int potionID = posPotionEffects[rand2.nextInt(posPotionEffects.length)];
                PotionEffectType effect = PotionEffectType.getById(potionID);

                //-- Give the player a level 1 potion effect for 15 seconds
                player.addPotionEffect(new PotionEffect(effect, 15, 1));
                player.sendMessage(ChatColor.GOLD + "[Magician Perk]" + ChatColor.DARK_PURPLE + " You Received " + effect.getName() + " For 15 Seconds!");

            } else {

                //-- Give the player their Exp levels
                int xp = player.getLevel() + 10;
                player.setLevel(xp);
                player.sendMessage(ChatColor.GOLD + "[Magician Perk]" + ChatColor.DARK_PURPLE + " You Received 10 Experience Levels!");
            }

        }

    }
}
