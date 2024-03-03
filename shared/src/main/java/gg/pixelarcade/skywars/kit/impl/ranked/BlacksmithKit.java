package gg.pixelarcade.skywars.kit.impl.ranked;

import gg.pixelarcade.skywars.kit.Kit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.KitRarity;

import java.util.concurrent.ThreadLocalRandom;

public class BlacksmithKit extends Kit {

    public BlacksmithKit() {
        super("Blacksmith", Material.ANVIL, 0, GameType.RANKED, KitRarity.COMMON);
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemStack(Material.IRON_HELMET),
                new ItemStack(Material.IRON_CHESTPLATE),
                new ItemStack(Material.IRON_LEGGINGS),
                new ItemStack(Material.IRON_BOOTS)
        };
    }

    @Override
    public void apply(Player player) {
        for (int i = 0; i < 5; i++) {
            int enchantmentLevel = ThreadLocalRandom.current().nextInt(3) + 1;
            Enchantment enchantment = Enchantment.values()[ThreadLocalRandom.current().nextInt(Enchantment.values().length)];
            ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK);
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) enchantedBook.getItemMeta();
            meta.addStoredEnchant(enchantment, enchantmentLevel, true);
            enchantedBook.setItemMeta(meta);
            player.getInventory().addItem(enchantedBook);
        }
    }
}
