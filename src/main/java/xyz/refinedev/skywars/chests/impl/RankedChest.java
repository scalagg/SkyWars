package xyz.refinedev.skywars.chests.impl;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.chests.LootableChest;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.game.state.RefillType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RankedChest extends LootableChest {

    @Override
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.RANKED_SOLO, GameType.RANKED_DOUBLES);
    }

    @Override
    public List<ItemStack> getSpawnLoot(RefillType type) {
        List<ItemStack> items = new ArrayList<>();

        switch (type) {
            case FIRST: {
                items.addAll(Arrays.asList(
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 15, 4), true).toItemStack(),
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 2), true).toItemStack(),
                        new ItemStack(Material.SNOW_BALL, 16),
                        new ItemStack(Material.SNOW_BALL, 8),
                        new ItemStack(Material.EGG, 8),
                        new ItemStack(Material.EGG, 16),
                        new ItemStack(Material.FISHING_ROD),
                        new ItemStack(Material.LAVA_BUCKET),
                        new ItemStack(Material.WATER_BUCKET),
                        new ItemStack(Material.EXP_BOTTLE, 8),
                        new ItemStack(Material.EXP_BOTTLE, 16),
                        new ItemStack(Material.EXP_BOTTLE, 32)
                ));

                break;
            }

            case SECOND:
            case THIRD: {
                items.addAll(Arrays.asList(
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 15, 4), true).toItemStack(),
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 2), true).toItemStack(),
                        new ItemStack(Material.SNOW_BALL, 16),
                        new ItemStack(Material.SNOW_BALL, 8),
                        new ItemStack(Material.EGG, 8),
                        new ItemStack(Material.EGG, 16),
                        new ItemStack(Material.FISHING_ROD),
                        new ItemStack(Material.LAVA_BUCKET),
                        new ItemStack(Material.WATER_BUCKET),
                        new ItemStack(Material.EXP_BOTTLE, 8),
                        new ItemStack(Material.EXP_BOTTLE, 16),
                        new ItemStack(Material.EXP_BOTTLE, 32),
                        new ItemStack(Material.ENDER_PEARL, 3),
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1), true).toItemStack()
                ));

                break;
            }
        }

        return items;
    }

    @Override
    public List<ItemStack> getMiddleLoot(RefillType type) {
        List<ItemStack> items = new ArrayList<>();

        switch (type) {
            case FIRST: {
                items.addAll(Arrays.asList(
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 2), true).toItemStack(),
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 15, 2), true).toItemStack(),
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 2), true).toItemStack(),
                        new ItemBuilder(Material.GOLDEN_APPLE).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_AXE).addEnchantment(Enchantment.DIG_SPEED, 1).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 1).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.FIRE_ASPECT, 1).toItemStack(), // TODO: Make oinly two of these to spawn possbiel
                        new ItemStack(Material.SNOW_BALL, 16),
                        new ItemStack(Material.SNOW_BALL, 8),
                        new ItemStack(Material.EGG, 8),
                        new ItemStack(Material.EGG, 16),
                        new ItemStack(Material.LAVA_BUCKET),
                        new ItemStack(Material.WATER_BUCKET),
                        new ItemStack(Material.LOG, 32),
                        new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack(),
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 15, 1), true).toItemStack(),
                        new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1).toItemStack(),
                        new ItemStack(Material.ARROW, 16),
                        new ItemStack(Material.EXP_BOTTLE, 8),
                        new ItemStack(Material.EXP_BOTTLE, 16),
                        new ItemStack(Material.EXP_BOTTLE, 32)
                ));

                break;
            }

            case SECOND: {
                items.addAll(Arrays.asList(
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 2), true).toItemStack(),
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 15, 2), true).toItemStack(),
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 2), true).toItemStack(),
                        new ItemBuilder(Material.GOLDEN_APPLE).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_AXE).addEnchantment(Enchantment.DIG_SPEED, 1).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 1).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.FIRE_ASPECT, 1).toItemStack(), // TODO: Make oinly two of these to spawn possbiel
                        new ItemStack(Material.SNOW_BALL, 16),
                        new ItemStack(Material.SNOW_BALL, 8),
                        new ItemStack(Material.EGG, 8),
                        new ItemStack(Material.EGG, 16),
                        new ItemStack(Material.LAVA_BUCKET),
                        new ItemStack(Material.WATER_BUCKET),
                        new ItemStack(Material.LOG, 32),
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 15, 1), true).toItemStack(),
                        new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1).toItemStack(),
                        new ItemStack(Material.ARROW, 16),
                        new ItemStack(Material.EXP_BOTTLE, 8),
                        new ItemStack(Material.EXP_BOTTLE, 16),
                        new ItemStack(Material.EXP_BOTTLE, 32),
                        new ItemStack(Material.ENDER_PEARL, 3),
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1), true).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 1).addEnchantment(Enchantment.FIRE_ASPECT, 2).toItemStack()
                ));

                break;
            }

            case THIRD: {
                items.addAll(Arrays.asList(
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 2), true).toItemStack(),
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 15, 2), true).toItemStack(),
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 2), true).toItemStack(),
                        new ItemBuilder(Material.GOLDEN_APPLE).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_AXE).addEnchantment(Enchantment.DIG_SPEED, 1).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 1).toItemStack(),
                        // TODO: Make only two of these to spawn possible
                        new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.FIRE_ASPECT, 1).toItemStack(),
                        new ItemStack(Material.SNOW_BALL, 16),
                        new ItemStack(Material.SNOW_BALL, 8),
                        new ItemStack(Material.EGG, 8),
                        new ItemStack(Material.EGG, 16),
                        new ItemStack(Material.LAVA_BUCKET),
                        new ItemStack(Material.WATER_BUCKET),
                        new ItemStack(Material.LOG, 32),
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 15, 1), true).toItemStack(),
                        new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1).toItemStack(),
                        new ItemStack(Material.ARROW, 16),
                        new ItemStack(Material.EXP_BOTTLE, 8),
                        new ItemStack(Material.EXP_BOTTLE, 16),
                        new ItemStack(Material.EXP_BOTTLE, 32),
                        new ItemStack(Material.ENDER_PEARL, 3),
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1), true).toItemStack(),
//                            TODO: MAKE ONLY TWO OF THESE ARMOR PIECES SPAWNW IN A CHEST
                        new ItemBuilder(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack(),
//                            TODO: MAKE THIS ONLY SPAWN IN A SINGLE CHEST
                        new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 1).addEnchantment(Enchantment.FIRE_ASPECT, 2).toItemStack()
                ));

                break;
            }
        }

        return items;
    }
}