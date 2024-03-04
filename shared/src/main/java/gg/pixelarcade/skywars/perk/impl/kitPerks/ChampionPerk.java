package gg.pixelarcade.skywars.perk.impl.kitPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChampionPerk extends Perk {

    public ChampionPerk(){
        super("Champion Perk", Material.DIAMOND_SWORD, 0, PerkRarity.COMMON);
    }

    @Override
    public void apply(Player player) {

        SkyWarsProfile profile = new SkyWarsProfile(player.getUniqueId());

        //-- Make sure that only people with the champion kit selected can get this perk
        if(profile.getSelectedKit().equalsIgnoreCase("Champion")){

            //-- Variables
            final ItemStack[] inventory = player.getInventory().getContents();
            final Enchantment enchantment = Enchantment.DAMAGE_ALL;
            int sharpnessLevel;

            //-- Iterate through the player's inventory
            for (ItemStack inventorySlot : inventory){

                //-- Make sure we only grab the champion sword
                if(inventorySlot.getType() == Material.DIAMOND_SWORD && inventorySlot.getEnchantmentLevel(enchantment) >= 2){
                    sharpnessLevel = inventorySlot.getEnchantmentLevel(enchantment) + 1;
                    inventorySlot.addEnchantment(enchantment, sharpnessLevel);
                }
            }

            //-- Give the player back their items
            player.getInventory().setContents(inventory);
            player.sendMessage(ChatColor.GOLD + "[Champion Perk]" + ChatColor.DARK_PURPLE + " +1 Sharpness Level!");
        }
    }
}
