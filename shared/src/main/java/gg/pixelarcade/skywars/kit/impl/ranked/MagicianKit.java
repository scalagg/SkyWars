package gg.pixelarcade.skywars.kit.impl.ranked;

import gg.pixelarcade.skywars.kit.Kit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.KitRarity;

public class MagicianKit extends Kit {

    public MagicianKit() {
        super("Magician", Material.STICK, 0, GameType.RANKED, KitRarity.COMMON);
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemStack(Material.IRON_HELMET),
                new ItemBuilder(Material.DIAMOND_CHESTPLATE)
                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                        .toItemStack(),
                new ItemStack(Material.IRON_LEGGINGS),
                new ItemStack(Material.IRON_BOOTS)
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.MILK_BUCKET, 2)
                .toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 3)
                .addCustomPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 25 * 10, 1), true)
                .toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 3)
                .addCustomPotionEffect(new PotionEffect(PotionEffectType.HARM, 25 * 10, 1), true)
                .toItemStack());
    }
}
