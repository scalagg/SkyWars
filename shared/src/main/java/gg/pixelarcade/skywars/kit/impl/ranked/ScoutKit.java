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

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.kit.impl
 * @date 26/09/2023 / 18:32
 */

public class ScoutKit extends Kit {

    public ScoutKit() {
        super("Scout", Material.POTION, 0, GameType.RANKED, KitRarity.COMMON);
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
        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_AXE)
                .addUnsafeEnchantment(Enchantment.DIG_SPEED, 1)
                .addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1)
                .toItemStack());

        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_PICKAXE)
                .addUnsafeEnchantment(Enchantment.DIG_SPEED, 1)
                .toItemStack());

        player.getInventory().addItem(new ItemBuilder(Material.POTION, 3)
                .addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 25 * 20, 1), true)
                .toItemStack());
    }
}
