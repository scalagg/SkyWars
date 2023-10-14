//package gg.pixelarcade.skywars.chests.impl;
//
//import org.bukkit.Material;
//import org.bukkit.enchantments.Enchantment;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.potion.PotionEffect;
//import org.bukkit.potion.PotionEffectType;
//import xyz.refinedev.phoenix.utils.item.ItemBuilder;
//import gg.pixelarcade.skywars.chests.LootableChest;
//import gg.pixelarcade.skywars.game.state.GameType;
//import gg.pixelarcade.skywars.game.state.RefillType;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author alfie
// * @project Skywars / gg.pixelarcade.skywars.chests.impl
// * @date 27/09/2023 / 20:39
// */
//
//public class InsaneChest extends LootableChest {
//
//    @Override
//    public List<GameType> getGameType() {
//        return Arrays.asList(GameType.INSANE_SOLO, GameType.INSANE_DOUBLES);
//    }
//
//
//    @Override
//    public List<ItemStack> getSpawnLoot(RefillType type) {
//        List<ItemStack> items = new ArrayList<>();
//
//        switch (type) {
//            case FIRST: {
//                items.addAll(Arrays.asList(
//                        new ItemBuilder(Material.DIAMOND_HELMET).toItemStack(),
//                        new ItemBuilder(Material.DIAMOND_CHESTPLATE).toItemStack(),
//                        new ItemBuilder(Material.DIAMOND_LEGGINGS).toItemStack(),
//                        new ItemBuilder(Material.DIAMOND_BOOTS).toItemStack(),
//                        new ItemBuilder(Material.IRON_BOOTS).toItemStack(),
//                        new ItemBuilder(Material.IRON_CHESTPLATE).toItemStack(),
//                        new ItemBuilder(Material.IRON_LEGGINGS).toItemStack(),
//                        new ItemBuilder(Material.IRON_HELMET).toItemStack(),
//                        new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack(),
//                        new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).toItemStack(),
//                        new ItemBuilder(Material.DIAMOND_SWORD).toItemStack(),
//                        new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1).toItemStack(),
//                        new ItemBuilder(Material.ARROW, 10).toItemStack(),
//                        new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 3).toItemStack(),
//                        new ItemBuilder(Material.ARROW, 16).toItemStack(),
//                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 33, 1), true).toItemStack(),
//                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 16, 2), true).toItemStack(),
//                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.POISON, 16, 1), true).toItemStack(),
//                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 67, 2), true).toItemStack(),
//                        new ItemBuilder(Material.EGG, 16).toItemStack(),
//                        new ItemBuilder(Material.SNOW_BALL, 16).toItemStack(),
//                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 180, 1), false).toItemStack(),
//                        new ItemBuilder(Material.FISHING_ROD, 1).toItemStack(),
//                        new ItemBuilder(Material.COOKED_BEEF, 16).toItemStack(),
//                        new ItemBuilder(Material.EXP_BOTTLE, 32).toItemStack(),
//                        new ItemBuilder(Material.EXP_BOTTLE, 64).toItemStack(),
//                        new ItemBuilder(Material.DIAMOND_AXE, 1).toItemStack(),
//                        new ItemBuilder(Material.DIAMOND_PICKAXE, 1).toItemStack(),
//                        new ItemBuilder(Material.WATER_BUCKET).toItemStack(),
//                        new ItemBuilder(Material.LAVA_BUCKET).toItemStack(),
//                        new ItemBuilder(Material.WOOD, 64).toItemStack(),
//                        new ItemBuilder(Material.STONE, 64).toItemStack(),
//                        new ItemBuilder(Material.ENCHANTMENT_TABLE, 1).toItemStack()));
//
//                break;
//            }
//
//            case SECOND: {
//                items.addAll(Arrays.asList(
//                        new ItemBuilder(Material.ENDER_PEARL, 1).toItemStack()
//                ));
//            }
//            case THIRD: {
//                items.addAll(Arrays.asList(
//                        new ItemBuilder(Material.ENDER_PEARL, 1).toItemStack()
//                ));
//
//                break;
//            }
//        }
//
//        return items;
//    }
//
//
//    @Override
//    public List<ItemStack> getMiddleLoot(RefillType type) {
//        List<ItemStack> items = new ArrayList<>();
//
//        switch (type) {
//            case FIRST: {
//                items.addAll(Arrays.asList(
//                        new ItemBuilder(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(),
//                        new ItemBuilder(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack(),
//                        new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_PROJECTILE, 3).toItemStack(),
//                        new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack(),
//                        new ItemBuilder(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchantment(Enchantment.PROTECTION_FIRE, 5).toItemStack(),
//                        new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).addEnchantment(Enchantment.FIRE_ASPECT, 2).toItemStack(),
//                        new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 5).toItemStack(),
//                        new ItemBuilder(Material.ARROW, 16).toItemStack(),
//                        new ItemBuilder(Material.ARROW, 64).toItemStack(),
//                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 16, 2), true).toItemStack(),
//                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 67, 2), true).toItemStack(),
//                        new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.POISON, 16, 1), true).toItemStack(),
//                        new ItemBuilder(Material.SNOW_BALL, 32).toItemStack(),
//                        new ItemBuilder(Material.EXP_BOTTLE, 64).toItemStack(),
//                        new ItemBuilder(Material.DIAMOND_AXE, 1).addEnchantment(Enchantment.DIG_SPEED, 3).toItemStack(),
//                        new ItemBuilder(Material.DIAMOND_PICKAXE, 1).addEnchantment(Enchantment.DIG_SPEED, 3).toItemStack(),
//                        new ItemBuilder(Material.ENDER_PEARL, 3).toItemStack(),
//                        new ItemBuilder(Material.ENDER_PEARL, 5).toItemStack(),
//                        new ItemBuilder(Material.GOLDEN_APPLE, 5).toItemStack(),
//                        new ItemBuilder(Material.GOLDEN_APPLE, 8).toItemStack(),
//                        new ItemBuilder(Material.TNT, 10).toItemStack(),
//                        new ItemBuilder(Material.FLINT_AND_STEEL, 1).toItemStack(),
//                        new ItemBuilder(Material.WOOD, 1).toItemStack()
//                ));
//
//                break;
//            }
//
//            case SECOND: {
//                items.addAll(Arrays.asList(
//                        new ItemBuilder(Material.ENDER_PEARL, 1).toItemStack()
//                ));
//            }
//            case THIRD: {
//                items.addAll(Arrays.asList(
//                        new ItemBuilder(Material.ENDER_PEARL, 1).toItemStack(),
//                        new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack()
//                ));
//
//                break;
//            }
//        }
//
//        return items;
//    }
//}
