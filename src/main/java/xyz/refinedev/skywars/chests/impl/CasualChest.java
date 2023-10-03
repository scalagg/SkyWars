package xyz.refinedev.skywars.chests.impl;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.chests.LootableChest;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.game.state.RefillType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.chests.impl
 * @date 27/09/2023 / 20:22
 */

public class CasualChest extends LootableChest {

    @Override
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.CASUAL_SOLO, GameType.CASUAL_DOUBLES);
    }

    @Override
    public List<ItemStack> getSpawnLoot(RefillType type) {
        List<ItemStack> items = new ArrayList<>();

        switch (type) {
            case FIRST: {
                items.addAll(Arrays.asList(
                        new ItemBuilder(Material.IRON_HELMET).toItemStack(),
                        new ItemBuilder(Material.IRON_CHESTPLATE).toItemStack(),
                        new ItemBuilder(Material.IRON_LEGGINGS).toItemStack(),
                        new ItemBuilder(Material.IRON_BOOTS).toItemStack(),
                        new ItemBuilder(Material.GOLD_CHESTPLATE).toItemStack(),
                        new ItemBuilder(Material.GOLD_LEGGINGS).toItemStack(),
                        new ItemBuilder(Material.CHAINMAIL_LEGGINGS).toItemStack(),
                        new ItemBuilder(Material.STONE_SWORD).toItemStack(),
                        new ItemBuilder(Material.STONE_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack(),
                        new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack(),
                        new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1).toItemStack(),  //TODO: RARE
                        new ItemBuilder(Material.ARROW, 10).toItemStack(),
                        new ItemBuilder(Material.WATER_BUCKET).toItemStack(),
                        new ItemBuilder(Material.LAVA_BUCKET).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_AXE).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_PICKAXE).toItemStack(),
                        new ItemBuilder(Material.STONE_PICKAXE).toItemStack(),
                        new ItemBuilder(Material.STONE_AXE).toItemStack(),
                        new ItemBuilder(Material.WOOD_AXE).toItemStack(),
                        new ItemBuilder(Material.WOOD_PICKAXE).toItemStack(),
                        new ItemBuilder(Material.EXP_BOTTLE, 12).toItemStack(),
                        new ItemBuilder(Material.SNOW_BALL, 12).toItemStack(),
                        new ItemBuilder(Material.SNOW_BALL, 16).toItemStack(),
                        new ItemBuilder(Material.EGG, 12).toItemStack(),
                        new ItemBuilder(Material.EGG, 16).toItemStack(),
                        new ItemBuilder(Material.EGG, 8).toItemStack(),
                        new ItemBuilder(Material.COOKED_BEEF, 12).toItemStack(),
                        new ItemBuilder(Material.COOKED_BEEF, 8).toItemStack(),
                        new ItemBuilder(Material.STONE_PICKAXE).toItemStack(),
                        new ItemBuilder(Material.IRON_PICKAXE).toItemStack(),
                        new ItemBuilder(Material.EXP_BOTTLE, 12).toItemStack(),
                        new ItemBuilder(Material.GOLDEN_APPLE, 2).toItemStack(),
                        new ItemBuilder(Material.GOLDEN_APPLE, 1).toItemStack(),
                        new ItemBuilder(Material.WOOD, 64).toItemStack(),
                        new ItemBuilder(Material.STONE, 64).toItemStack()
                ));

                break;
            }

            case SECOND: {
                items.addAll(Arrays.asList(
                        new ItemBuilder(Material.ENDER_PEARL, 1).toItemStack()
                ));
            }
            case THIRD: {
                items.addAll(Arrays.asList(
                        new ItemBuilder(Material.ENDER_PEARL, 1).toItemStack()
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
                        new ItemBuilder(Material.IRON_HELMET).toItemStack(),
                        new ItemBuilder(Material.IRON_CHESTPLATE).toItemStack(),
                        new ItemBuilder(Material.IRON_LEGGINGS).toItemStack(),
                        new ItemBuilder(Material.IRON_BOOTS).toItemStack(),
                        new ItemBuilder(Material.GOLD_CHESTPLATE).toItemStack(),
                        new ItemBuilder(Material.GOLD_LEGGINGS).toItemStack(),
                        new ItemBuilder(Material.CHAINMAIL_LEGGINGS).toItemStack(),
                        new ItemBuilder(Material.STONE_SWORD).toItemStack(),
                        new ItemBuilder(Material.STONE_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack(),
                        new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack(),
                        new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1).toItemStack(),  //TODO: RARE
                        new ItemBuilder(Material.ARROW, 10).toItemStack(),
                        new ItemBuilder(Material.WATER_BUCKET).toItemStack(),
                        new ItemBuilder(Material.LAVA_BUCKET).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_AXE).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_PICKAXE).toItemStack(),
                        new ItemBuilder(Material.STONE_PICKAXE).toItemStack(),
                        new ItemBuilder(Material.STONE_AXE).toItemStack(),
                        new ItemBuilder(Material.WOOD_AXE).toItemStack(),
                        new ItemBuilder(Material.WOOD_PICKAXE).toItemStack(),
                        new ItemBuilder(Material.EXP_BOTTLE, 12).toItemStack(),
                        new ItemBuilder(Material.SNOW_BALL, 12).toItemStack(),
                        new ItemBuilder(Material.SNOW_BALL, 16).toItemStack(),
                        new ItemBuilder(Material.EGG, 12).toItemStack(),
                        new ItemBuilder(Material.EGG, 16).toItemStack(),
                        new ItemBuilder(Material.EGG, 8).toItemStack(),
                        new ItemBuilder(Material.COOKED_BEEF, 12).toItemStack(),
                        new ItemBuilder(Material.COOKED_BEEF, 8).toItemStack(),
                        new ItemBuilder(Material.STONE_PICKAXE).toItemStack(),
                        new ItemBuilder(Material.IRON_PICKAXE).toItemStack(),
                        new ItemBuilder(Material.EXP_BOTTLE, 12).toItemStack(),
                        new ItemBuilder(Material.GOLDEN_APPLE, 2).toItemStack(),
                        new ItemBuilder(Material.GOLDEN_APPLE, 1).toItemStack(),
                        new ItemBuilder(Material.WOOD, 64).toItemStack(),
                        new ItemBuilder(Material.STONE, 64).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(),
                        new ItemBuilder(Material.IRON_CHESTPLATE).addEnchant(Enchantment.PROTECTION_PROJECTILE, 3).toItemStack(),
                        new ItemBuilder(Material.IRON_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_FIRE, 5).toItemStack(),
                        new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.FIRE_ASPECT, 1).toItemStack(),
                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 1), true).toItemStack(),
                        new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1).toItemStack(),
                        new ItemBuilder(Material.ENDER_PEARL, 1).toItemStack(),
                        new ItemBuilder(Material.TNT, 10).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_AXE).addEnchant(Enchantment.DIG_SPEED, 1).toItemStack(),
                        new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 1).toItemStack(),
                        new ItemBuilder(Material.SNOW_BALL, 32).toItemStack(),
                        new ItemBuilder(Material.LAVA_BUCKET, 1).toItemStack(),
                        new ItemBuilder(Material.WATER_BUCKET, 1).toItemStack(),
                        new ItemBuilder(Material.EXP_BOTTLE, 64).toItemStack(),
                        new ItemBuilder(Material.GOLDEN_APPLE, 1).toItemStack(),
                        new ItemBuilder(Material.WOOD, 64).toItemStack()
                ));

                break;
            }

            case SECOND: {
                items.addAll(Arrays.asList(
                        new ItemBuilder(Material.ENDER_PEARL, 1).toItemStack()
                ));
            }
            case THIRD: {
                items.addAll(Arrays.asList(
                        new ItemBuilder(Material.ENDER_PEARL, 1).toItemStack()
                ));

                break;
            }
        }

        return items;
    }
}
