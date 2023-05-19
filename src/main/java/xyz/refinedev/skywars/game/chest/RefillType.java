package xyz.refinedev.skywars.game.chest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * @author Creaxx
 * Created At: 5/13/2023
 */

@Getter
@RequiredArgsConstructor
public enum RefillType {
    FIRST(
            // Spawn
            Arrays.asList( new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 2), false).toItemStack(),
                    new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 8, 1), true).toItemStack(),
                    new ItemBuilder(Material.GOLDEN_APPLE, 2).toItemStack(), //TODO: make the amount randomized 1-2
                    new ItemBuilder(Material.DIAMOND_AXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 2).toItemStack(),
                    new ItemBuilder(Material.DIAMOND_PICKAXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 2).toItemStack(),
                    new ItemBuilder(Material.DIAMOND_SWORD).addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2).toItemStack(), //TODO: make only two of these items spawn in the entire map to keep game balanced
                    new ItemBuilder(Material.EGG, 8).toItemStack(), //TODO: make the amount randomized  8-16
                    new ItemBuilder(Material.SNOW_BALL, 8).toItemStack(), //TODO: make the amount randomized  8-16
                    new ItemBuilder(Material.FISHING_ROD).toItemStack(),
                    new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 2), true).toItemStack(),
                    new ItemBuilder(Material.LAVA_BUCKET).toItemStack(),
                    new ItemBuilder(Material.WATER_BUCKET).toItemStack(),
                    new ItemBuilder(Material.WOOD, 32).toItemStack(), //TODO: make the amount randomized from 32-128 planks of wood found in the map
                    // ---------------------
                    // TODO: Make the diamond armor randomized from 1-2 pieces of armor
                    new ItemBuilder(Material.DIAMOND_LEGGINGS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(),
                    new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(),
                    new ItemBuilder(Material.DIAMOND_HELMET).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(),
                    new ItemBuilder(Material.DIAMOND_BOOTS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(),
                    // ---------------------
                    new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 15, 1), false).toItemStack(),
                    new ItemBuilder(Material.BOW).addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1).toItemStack(), //TODO: 3 of these spawn in the entire map
                    new ItemBuilder(Material.ARROW, 16).toItemStack(), //TODO: make the amount randomized 16-32
                    new ItemBuilder(Material.EXP_BOTTLE, 32).toItemStack()
                    ),


            // Middle
            Arrays.asList(
                    new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 2), false).toItemStack(),
                    new ItemBuilder(Material.GOLDEN_APPLE, 2).toItemStack(), //TODO: make the amount randomized 1-2
                    new ItemBuilder(Material.DIAMOND_SWORD).addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1).toItemStack(), //TODO: make only two of these items spawn in the entire map to keep game balanced
                    new ItemBuilder(Material.LAVA_BUCKET).toItemStack(),
                    new ItemBuilder(Material.IRON_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack(),
                    new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack()

            )),
    SECOND(
            //Spawn
            Arrays.asList(
                    new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 2), false).toItemStack(),
                    new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 8, 1), true).toItemStack(),
                    new ItemBuilder(Material.GOLDEN_APPLE, 2).toItemStack(), //TODO: make the amount randomized 1-2
                    new ItemBuilder(Material.DIAMOND_AXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 2).toItemStack(),
                    new ItemBuilder(Material.DIAMOND_PICKAXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 2).toItemStack(),
                    new ItemBuilder(Material.EGG, 8).toItemStack(), //TODO: make the amount randomized  8-16
                    new ItemBuilder(Material.SNOW_BALL, 8).toItemStack(), //TODO: make the amount randomized  8-16
                    new ItemBuilder(Material.FISHING_ROD).toItemStack(),
                    new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 2), true).toItemStack(),
                    new ItemBuilder(Material.LAVA_BUCKET).toItemStack(),
                    new ItemBuilder(Material.WATER_BUCKET).toItemStack(),
                    new ItemBuilder(Material.WOOD, 32).toItemStack(), //TODO: make the amount randomized from 32-128 planks of wood found in the map
                    new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 15, 1), false).toItemStack(),
                    new ItemBuilder(Material.BOW).addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1).toItemStack(), //TODO: 3 of these spawn in the entire map
                    new ItemBuilder(Material.ARROW, 16).toItemStack(), //TODO: make the amount randomized 16-32
                    new ItemBuilder(Material.EXP_BOTTLE, 32).toItemStack(),
                    // ---------------------
                    // TODO: Make the diamond armor randomized from 1-2 pieces of armor
                    new ItemBuilder(Material.DIAMOND_LEGGINGS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack(),
                    new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack(),
                    new ItemBuilder(Material.DIAMOND_HELMET).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack(),
                    new ItemBuilder(Material.DIAMOND_BOOTS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack(),
                    // ---------------------
                    new ItemBuilder(Material.ENDER_PEARL, 5).toItemStack(), //TODO: make the amount randomized 1-12
                    new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 1), false).toItemStack() //TODO: Make this do 2 hearts

                    ),
            //Middle
            Arrays.asList(
                    new ItemBuilder(Material.DIAMOND_SWORD).addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3).toItemStack(), //TODO: make only one of these spawn to keep balance
                    // ---------------------
                    // TODO: Make the diamond armor randomized from 1-2 pieces of armor
                    new ItemBuilder(Material.DIAMOND_LEGGINGS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack(),
                    new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack(),
                    new ItemBuilder(Material.DIAMOND_HELMET).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack(),
                    new ItemBuilder(Material.DIAMOND_BOOTS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack()
                    // ---------------------
            )),
    THIRD(
            //Spawn
            Arrays.asList(
            new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 2), false).toItemStack(),
            new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 8, 1), true).toItemStack(),
            new ItemBuilder(Material.GOLDEN_APPLE, 2).toItemStack(), //TODO: make the amount randomized 1-2
            new ItemBuilder(Material.DIAMOND_AXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 2).toItemStack(),
            new ItemBuilder(Material.DIAMOND_PICKAXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 2).toItemStack(),
            new ItemBuilder(Material.DIAMOND_SWORD).addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1).toItemStack(), //TODO: make only two of these items spawn in the entire map to keep game balanced
            new ItemBuilder(Material.EGG, 8).toItemStack(), //TODO: make the amount randomized  8-16
            new ItemBuilder(Material.SNOW_BALL, 8).toItemStack(), //TODO: make the amount randomized  8-16
            new ItemBuilder(Material.FISHING_ROD).toItemStack(),
            new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 2), true).toItemStack(),
            new ItemBuilder(Material.LAVA_BUCKET).toItemStack(),
            new ItemBuilder(Material.WATER_BUCKET).toItemStack(),
            new ItemBuilder(Material.WOOD, 32).toItemStack(), //TODO: make the amount randomized from 32-128 planks of wood found in the map
            // ---------------------
            // TODO: Make the diamond armor randomized from 1-2 pieces of armor
            new ItemBuilder(Material.DIAMOND_LEGGINGS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack(),
            new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack(),
            new ItemBuilder(Material.DIAMOND_HELMET).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack(),
            new ItemBuilder(Material.DIAMOND_BOOTS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack(),
            // ---------------------
            new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 15, 1), false).toItemStack(),
            new ItemBuilder(Material.BOW).addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1).toItemStack(), //TODO: 3 of these spawn in the entire map
            new ItemBuilder(Material.ARROW, 16).toItemStack(), //TODO: make the amount randomized 16-32
            new ItemBuilder(Material.EXP_BOTTLE, 32).toItemStack(),
            new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 2), false).toItemStack(),
            new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 8, 1), true).toItemStack(),
            new ItemBuilder(Material.GOLDEN_APPLE, 2).toItemStack(), //TODO: make the amount randomized 1-2
            new ItemBuilder(Material.DIAMOND_AXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 2).toItemStack(),
            new ItemBuilder(Material.DIAMOND_PICKAXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 2).toItemStack(),
            new ItemBuilder(Material.EGG, 8).toItemStack(), //TODO: make the amount randomized  8-16
            new ItemBuilder(Material.SNOW_BALL, 8).toItemStack(), //TODO: make the amount randomized  8-16
            new ItemBuilder(Material.FISHING_ROD).toItemStack(),
            new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 2), true).toItemStack(),
            new ItemBuilder(Material.LAVA_BUCKET).toItemStack(),
            new ItemBuilder(Material.WATER_BUCKET).toItemStack(),
            new ItemBuilder(Material.WOOD, 32).toItemStack(), //TODO: make the amount randomized from 32-128 planks of wood found in the map
            new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 15, 1), false).toItemStack(),
            new ItemBuilder(Material.BOW).addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1).toItemStack(), //TODO: 3 of these spawn in the entire map
            new ItemBuilder(Material.ARROW, 16).toItemStack(), //TODO: make the amount randomized 16-32
            new ItemBuilder(Material.EXP_BOTTLE, 32).toItemStack(),

            new ItemBuilder(Material.ENDER_PEARL, 5).toItemStack(), //TODO: make the amount randomized 1-12
            new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 1), false).toItemStack() //TODO: Make this do 2 hearts
            ),
            //Middle
            Arrays.asList(
                    new ItemBuilder(Material.DIAMOND_SWORD).addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3).toItemStack() //TODO: make only one of these spawn to keep balance
            ));

    private final List<ItemStack> spawnItems;
    private final List<ItemStack> middleItems;
}
