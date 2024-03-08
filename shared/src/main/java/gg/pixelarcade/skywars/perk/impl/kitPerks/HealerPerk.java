package gg.pixelarcade.skywars.perk.impl.kitPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HealerPerk extends Perk {

    public HealerPerk() {
        super("Healer Perk", Material.GOLDEN_APPLE, 0, PerkRarity.COMMON);
    }

    @Override
    public void apply(Player player) {

        SkyWarsProfile profile = new SkyWarsProfile(player.getUniqueId());

        //-- Make sure that only people with the healer kit selected can get this perk
        if (profile.getSelectedKit().equalsIgnoreCase("Healer")) {

            //-- Set player's max health +4HP
            double maxHealth = player.getMaxHealth() + 4;
            player.setMaxHealth(maxHealth);

            //-- Heal player for 8HP
            double newHealth = player.getHealth() + 8;
            player.setHealth(newHealth);

            //-- Give player golden apple
            player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
            player.sendMessage(ChatColor.GOLD + "[Healer Perk]" + ChatColor.DARK_PURPLE + " You Have Been Healed! +1 Golden Apple!");
        }
    }

    @Override
    public boolean isKitPerk(){
        return true;
    }
}
