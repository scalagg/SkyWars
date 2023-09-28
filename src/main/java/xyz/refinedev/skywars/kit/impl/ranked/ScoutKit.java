package xyz.refinedev.skywars.kit.impl.ranked;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.kit.Kit;

import java.util.Arrays;
import java.util.List;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.kit.impl
 * @date 26/09/2023 / 18:32
 */

public class ScoutKit extends Kit {

    @Override
    public String getName() {
        return "Scout";
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.RANKED_SOLO);
    }
    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.POTION)
                .setName("&aScout")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fSpeed 2 for 20 seconds",
                        "&7■ &fIron Armor",
                        "&7■ &fDiamond Axe",
                        "&7■ &fDiamond Pickaxe",
                        "&6&m---------------------"
                        ))
                .toItemStack();
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
