package gg.pixelarcade.skywars.perk.impl.kitPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SniperPerk extends Perk {

    //TODO: Doubles the effect of the Blazing Arrow and ArrowRecovery Perks

    public SniperPerk(){
        super("Sniper Perk", Material.BOW, 0, PerkRarity.COMMON);
    }

    @Override
    public void apply(Player player) {

        SkyWarsProfile profile = new SkyWarsProfile(player.getUniqueId());

        //-- Make sure that only people with the Sniper kit can get this perk
        if(profile.getSelectedKit().equalsIgnoreCase("Sniper")){

            //-- Variables
            final ItemStack[] inventory = player.getInventory().getContents();
            final Enchantment enchantment = Enchantment.ARROW_DAMAGE;
            int powerLevel = 1;

            //-- Iterate through the player's inventory
            for (ItemStack inventorySlot : inventory){

                //-- Make sure we only grab a bow item
                if(inventorySlot.getType() == Material.BOW){

                    //TODO: Make it so that only the bow you receive from the Sniper kit gets the upgrade
                    if (inventorySlot.containsEnchantment(enchantment)){
                        powerLevel = inventorySlot.getEnchantmentLevel(enchantment);
                        inventorySlot.addEnchantment(enchantment, powerLevel++);
                    } else {
                        inventorySlot.addEnchantment(enchantment, powerLevel);
                    }

                }
            }

            //-- Give the player back their items
            player.getInventory().setContents(inventory);
            player.sendMessage(ChatColor.GOLD + "[Sniper Perk]" + ChatColor.DARK_PURPLE + " +1 Power Level!");
        }
    }

    @Override
    public boolean isKitPerk(){
        return true;
    }
}