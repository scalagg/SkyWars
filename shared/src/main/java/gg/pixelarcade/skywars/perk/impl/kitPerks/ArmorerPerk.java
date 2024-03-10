package gg.pixelarcade.skywars.perk.impl.kitPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArmorerPerk extends Perk {


    public ArmorerPerk(){
        super("Armorer Perk", Material.IRON_CHESTPLATE, 0, PerkRarity.COMMON
        , "Armorer's armor gains 1 level of Protection after each kill.");
    }


    @Override
    public void apply(Player player) {

        SkyWarsProfile profile = new SkyWarsProfile(player.getUniqueId());

        //-- Make sure that only people with the Armorer kit selected can get this perk
        if (profile.getSelectedKit().equalsIgnoreCase("Armorer")) {

            //-- Variables
            final ItemStack[] armor = player.getInventory().getArmorContents();
            final Enchantment enchantment = Enchantment.PROTECTION_ENVIRONMENTAL;
            int protectionLevel = 1;

            //-- Iterate through each armor piece
            for (ItemStack armorPiece : armor) {

                //TODO: Make it so that only the armor you receive from the Armorer kit gets the upgrade
                if (armorPiece.containsEnchantment(enchantment)) {
                    protectionLevel = armorPiece.getEnchantmentLevel(enchantment);
                    armorPiece.addEnchantment(enchantment, protectionLevel++);
                } else {
                    armorPiece.addEnchantment(enchantment, protectionLevel);
                }
            }

            //-- Set the player's new armor
            player.getInventory().setArmorContents(armor);
            player.sendMessage(ChatColor.GOLD + "[Armorer Perk]" + ChatColor.DARK_PURPLE + " +1 Prot Level!");
        }
    }

    @Override
    public boolean isKitPerk(){
        return true;
    }
}
