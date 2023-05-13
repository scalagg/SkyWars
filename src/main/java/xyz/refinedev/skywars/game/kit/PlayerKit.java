package xyz.refinedev.skywars.game.kit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * @author Creaxx
 * Created At: 5/13/2023
 */

@Getter
@RequiredArgsConstructor
public enum PlayerKit {

    SCOUT("Scout", Arrays.asList(
            new ItemBuilder(Material.DIAMOND_AXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 1).addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1).toItemStack(),
            new ItemBuilder(Material.DIAMOND_PICKAXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 1).toItemStack(),
            new ItemBuilder(Material.POTION, 3).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 25 * 20, 1), true).toItemStack()
    ), Arrays.asList(
            new ItemStack(Material.IRON_HELMET),
            new ItemStack(Material.IRON_CHESTPLATE),
            new ItemStack(Material.IRON_LEGGINGS),
            new ItemStack(Material.IRON_BOOTS)
    )),

    MAGICIAN("Magician", Arrays.asList(
            new ItemStack(Material.DIAMOND_SWORD),
            new ItemStack(Material.IRON_PICKAXE),
            new ItemStack(Material.IRON_AXE),
            new ItemStack(Material.MILK_BUCKET, 2),
            new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 0), true).toItemStack(),
            new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 0), true).toItemStack()
    ), Arrays.asList(
            new ItemStack(Material.IRON_HELMET),
            new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1).toItemStack(),
            new ItemStack(Material.IRON_LEGGINGS),
            new ItemStack(Material.IRON_BOOTS)
    ));

    private final String displayName;
    private final List<ItemStack> items;
    private final List<ItemStack> armor;

}
