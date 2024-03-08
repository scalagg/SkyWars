package gg.pixelarcade.skywars.perk.impl.kitPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HoundPerk extends Perk {

    public HoundPerk() {
        super("Hound Perk", Material.EGG, 0, PerkRarity.COMMON);
    }

    @Override
    public void apply(Player player) {

        SkyWarsProfile profile = new SkyWarsProfile(player.getUniqueId());

        //-- Make sure that only people with the Magician kit can get this perk
        if (profile.getSelectedKit().equalsIgnoreCase("Hound")) {

            //-- Spawn a wolf at the player's location
            World world = Bukkit.getWorld(player.getUniqueId());
            world.spawnEntity(player.getLocation(), EntityType.WOLF);

            //-- Give the player Steak
            player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));

            player.sendMessage(ChatColor.GOLD + "[Hound Perk]" + ChatColor.DARK_PURPLE + " You Received a New Best Friend!");
        }
    }

    @Override
    public boolean isKitPerk(){
        return true;
    }
}