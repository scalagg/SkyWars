package gg.pixelarcade.skywars.perk.impl.kitPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ScoutPerk extends Perk {

    public ScoutPerk() {
        super("Scout Perk", Material.FEATHER, 0, PerkRarity.COMMON);
    }

    @Override
    public void apply(Player player) {

        SkyWarsProfile profile = new SkyWarsProfile(player.getUniqueId());

        //-- Make sure that only people with the scout kit selected can get this perk
        if (profile.getSelectedKit().equalsIgnoreCase("Scout")) {

            player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
            player.sendMessage(ChatColor.GOLD + "[Scout Perk]" + ChatColor.DARK_PURPLE + " +1 Ender Pearl!");
        }
    }
}