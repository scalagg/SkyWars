package gg.pixelarcade.skywars.perk.impl.kitPerks;

import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkRarity;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.concurrent.ThreadLocalRandom;

public class BlacksmithPerk extends Perk {

    public BlacksmithPerk() {
        super("Blacksmith Perk", Material.ANVIL, 0, PerkRarity.COMMON);
    }

    @Override
    public void apply(Player player) {

        SkyWarsProfile profile = new SkyWarsProfile(player.getUniqueId());

        //-- Make sure that only people with the armorer kit selected can get this perk
        if (profile.getSelectedKit().equalsIgnoreCase("Blacksmith")) {

            //-- Recycled from BlacksmithKit
            int enchantmentLevel = ThreadLocalRandom.current().nextInt(3) + 1;
            Enchantment enchantment = Enchantment.values()[ThreadLocalRandom.current().nextInt(Enchantment.values().length)];
            ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK);
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) enchantedBook.getItemMeta();
            meta.addStoredEnchant(enchantment, enchantmentLevel, true);
            enchantedBook.setItemMeta(meta);
            player.getInventory().addItem(enchantedBook);

            //-- Give the player 3 Exp levels
            int xp = player.getLevel() + 3;
            player.setLevel(xp);
            player.sendMessage(ChatColor.GOLD + "[Blacksmith Perk]" + ChatColor.DARK_PURPLE + " You Received 3 Experience Levels and +1 Enchantment Book!");
        }
    }
}
