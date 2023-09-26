package xyz.refinedev.skywars.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.game.state.RefillType;
import xyz.refinedev.skywars.pxSkyWars;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Creaxx
 * Created At: 5/21/2023
 */

@UtilityClass
public class RefillUtil {

    public List<ItemStack> getRandomizedSpawnItems(GameType gameType, RefillType refillType) {
        Random random = pxSkyWars.getInstance().getRandom();

        List<ItemStack> items = new ArrayList<>();

        switch (refillType) {
            case FIRST: {
                items.add(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 2), false).toItemStack());
                items.add(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 8, 1), true).toItemStack());
                items.add(new ItemBuilder(Material.GOLDEN_APPLE, random.nextInt(1, 2)).toItemStack());
                items.add(new ItemBuilder(Material.DIAMOND_AXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 2).toItemStack());
                items.add(new ItemBuilder(Material.DIAMOND_PICKAXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 2).toItemStack());

                int chests = 6 + 3 * gameType.getMaxPlayers();
                double percent = 100D / chests;

                if (random.nextInt(0, 100) <= percent) {
                    items.add(new ItemBuilder(Material.DIAMOND_SWORD).addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2).toItemStack());
                }

                items.add(new ItemBuilder(Material.SNOW_BALL, random.nextInt(8, 16)).toItemStack());
                items.add(new ItemBuilder(Material.FISHING_ROD).toItemStack());
                items.add(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 2), true).toItemStack());
                items.add(new ItemBuilder(Material.LAVA_BUCKET).toItemStack());
                items.add(new ItemBuilder(Material.WATER_BUCKET).toItemStack());
                items.add(new ItemBuilder(Material.WOOD, random.nextInt(16, 64)).toItemStack());
                items.add(new ItemBuilder(Material.WOOD, random.nextInt(16, 64)).toItemStack());

                int armor = random.nextInt(1, 4);

                switch (armor) {
                    case 1: {
                        items.add(new ItemBuilder(Material.DIAMOND_LEGGINGS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                        break;
                    }
                    case 2: {
                        items.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                        break;
                    }
                    case 3: {
                        items.add(new ItemBuilder(Material.DIAMOND_HELMET).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                        break;
                    }
                    case 4: {
                        items.add(new ItemBuilder(Material.DIAMOND_BOOTS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                        break;
                    }
                }

                int otherArmor = random.nextInt(1, 4);

                while (otherArmor == armor) {
                    otherArmor = random.nextInt(1, 4);
                }

                switch (otherArmor) {
                    case 1: {
                        items.add(new ItemBuilder(Material.DIAMOND_LEGGINGS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                        break;
                    }
                    case 2: {
                        items.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                        break;
                    }
                    case 3: {
                        items.add(new ItemBuilder(Material.DIAMOND_HELMET).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                        break;
                    }
                    case 4: {
                        items.add(new ItemBuilder(Material.DIAMOND_BOOTS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                        break;
                    }
                }


                items.add(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 15, 1), false).toItemStack());

                double bowpercent = (3D * 100D) / chests;

                if (random.nextInt(0, 100) <= bowpercent) {
                    items.add(new ItemBuilder(Material.BOW).addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1).toItemStack());
                }

                items.add(new ItemBuilder(Material.ARROW, random.nextInt(16, 32)).toItemStack());
                items.add(new ItemBuilder(Material.EXP_BOTTLE, random.nextInt(16, 32)).toItemStack());
                break;
            }

            case SECOND: {
                items.add(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 2), false).toItemStack());
                items.add(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 8, 1), true).toItemStack());
                items.add(new ItemBuilder(Material.GOLDEN_APPLE, random.nextInt(1, 2)).toItemStack());
                items.add(new ItemBuilder(Material.DIAMOND_AXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 2).toItemStack());
                items.add(new ItemBuilder(Material.DIAMOND_PICKAXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 2).toItemStack());
                items.add(new ItemBuilder(Material.EGG, random.nextInt(8, 16)).toItemStack());
                items.add(new ItemBuilder(Material.SNOW_BALL, random.nextInt(8, 16)).toItemStack());
                items.add(new ItemBuilder(Material.FISHING_ROD).toItemStack());
                items.add(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 2), true).toItemStack());
                items.add(new ItemBuilder(Material.LAVA_BUCKET).toItemStack());
                items.add(new ItemBuilder(Material.WATER_BUCKET).toItemStack());
                items.add(new ItemBuilder(Material.WOOD, random.nextInt(16, 64)).toItemStack());
                items.add(new ItemBuilder(Material.WOOD, random.nextInt(16, 64)).toItemStack());
                items.add(new ItemBuilder(Material.ENDER_PEARL, random.nextInt(1, 12)).toItemStack());
                items.add(new ItemBuilder(Material.ARROW, random.nextInt(16, 32)).toItemStack());
                items.add(new ItemBuilder(Material.EXP_BOTTLE, random.nextInt(8, 64)).toItemStack());
                items.add(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 1), false).toItemStack());
                int chests = 6 + 3 * gameType.getMaxPlayers();
                double percent = 100D / chests;
                if (random.nextInt(0, 100) <= percent) {
                    items.add(new ItemBuilder(Material.BOW).addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1).toItemStack());
                }
                int armor = random.nextInt(1, 4);
                switch (armor) {
                    case 1: {
                        items.add(new ItemBuilder(Material.DIAMOND_LEGGINGS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());
                        break;
                    }
                    case 2: {
                        items.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());
                        break;
                    }
                    case 3: {
                        items.add(new ItemBuilder(Material.DIAMOND_HELMET).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());
                        break;
                    }
                    case 4: {
                        items.add(new ItemBuilder(Material.DIAMOND_BOOTS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());
                        break;
                    }
                }
                int otherArmor = random.nextInt(1, 4);
                while (otherArmor == armor) {
                    otherArmor = random.nextInt(1, 4);
                }
                switch (otherArmor) {
                    case 1: {
                        items.add(new ItemBuilder(Material.DIAMOND_LEGGINGS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());
                        break;
                    }
                    case 2: {
                        items.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());
                        break;
                    }
                    case 3: {
                        items.add(new ItemBuilder(Material.DIAMOND_HELMET).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());
                        break;
                    }
                    case 4: {
                        items.add(new ItemBuilder(Material.DIAMOND_BOOTS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());
                        break;
                    }
                }
                break;
            }

            case THIRD: {
                items.add(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 8, 1), true).toItemStack());
                items.add(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 8, 1), true).toItemStack());
                items.add(new ItemBuilder(Material.GOLDEN_APPLE, random.nextInt(1, 2)).toItemStack());
                items.add(new ItemBuilder(Material.DIAMOND_AXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 2).toItemStack());
                items.add(new ItemBuilder(Material.DIAMOND_PICKAXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 2).toItemStack());
                items.add(new ItemBuilder(Material.EGG, random.nextInt(8, 16)).toItemStack());
                items.add(new ItemBuilder(Material.SNOW_BALL, random.nextInt(8, 16)).toItemStack());
                items.add(new ItemBuilder(Material.FISHING_ROD).toItemStack());
                items.add(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 2), true).toItemStack());
                items.add(new ItemBuilder(Material.LAVA_BUCKET).toItemStack());
                items.add(new ItemBuilder(Material.WATER_BUCKET).toItemStack());
                items.add(new ItemBuilder(Material.WOOD, random.nextInt(16, 64)).toItemStack());
                items.add(new ItemBuilder(Material.WOOD, random.nextInt(16, 64)).toItemStack());
                items.add(new ItemBuilder(Material.ENDER_PEARL, random.nextInt(1, 12)).toItemStack());
                items.add(new ItemBuilder(Material.ARROW, random.nextInt(16, 32)).toItemStack());
                items.add(new ItemBuilder(Material.EXP_BOTTLE, random.nextInt(8, 64)).toItemStack());
                items.add(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 1), false).toItemStack());
                int chests = 6 + 3 * gameType.getMaxPlayers();
                double percent = 100D / chests;
                if (random.nextInt(0, 100) <= percent) {
                    items.add(new ItemBuilder(Material.BOW).addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1).toItemStack());
                }
                int armor = random.nextInt(1, 4);
                switch (armor) {
                    case 1: {
                        items.add(new ItemBuilder(Material.DIAMOND_LEGGINGS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack());
                        break;
                    }
                    case 2: {
                        items.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack());
                        break;
                    }
                    case 3: {
                        items.add(new ItemBuilder(Material.DIAMOND_HELMET).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack());
                        break;
                    }
                    case 4: {
                        items.add(new ItemBuilder(Material.DIAMOND_BOOTS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack());
                        break;
                    }
                }
                int otherArmor = random.nextInt(1, 4);
                while (otherArmor == armor) {
                    otherArmor = random.nextInt(1, 4);
                }
                switch (otherArmor) {
                    case 1: {
                        items.add(new ItemBuilder(Material.DIAMOND_LEGGINGS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack());
                        break;
                    }
                    case 2: {
                        items.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack());
                        break;
                    }
                    case 3: {
                        items.add(new ItemBuilder(Material.DIAMOND_HELMET).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack());
                        break;
                    }
                    case 4: {
                        items.add(new ItemBuilder(Material.DIAMOND_BOOTS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack());
                        break;
                    }
                }
                break;
            }
        }

        return items;
    }

    public List<ItemStack> getRandomizedMiddleItems(GameType gameType, RefillType refillType) {
        Random random = pxSkyWars.getInstance().getRandom();

        List<ItemStack> items = new ArrayList<>();

        switch (refillType) {
            case FIRST: {
                items.add(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 0, 2), false).toItemStack());
                items.add(new ItemBuilder(Material.GOLDEN_APPLE, 2).toItemStack());

                int chests = 6 + 3 * gameType.getMaxPlayers();
                double percent = 100D / chests;

                if (random.nextInt(0, 100) <= percent) {
                    items.add(new ItemBuilder(Material.DIAMOND_SWORD).addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2).toItemStack());
                }

                items.add(new ItemBuilder(Material.LAVA_BUCKET).toItemStack());
                items.add(new ItemBuilder(Material.IRON_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                items.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                break;
            }

            case SECOND: {
                int chests = 6 + 3 * gameType.getMaxPlayers();
                double percent = 100D / chests;

                if (random.nextInt(0, 100) <= percent) {
                    items.add(new ItemBuilder(Material.DIAMOND_SWORD).addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3).addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2).toItemStack());
                }

                items.add(new ItemBuilder(Material.LAVA_BUCKET).toItemStack());
                items.add(new ItemBuilder(Material.IRON_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());
                items.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());
                break;
            }

            case THIRD: {
                int chests = 6 + 3 * gameType.getMaxPlayers();
                double percent = 100D / chests;

                if (random.nextInt(0, 100) <= percent) {
                    items.add(new ItemBuilder(Material.DIAMOND_SWORD).addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3).addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3).toItemStack());
                }

                items.add(new ItemBuilder(Material.LAVA_BUCKET).toItemStack());
                items.add(new ItemBuilder(Material.IRON_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());
                items.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack());
                break;
            }
        }

        return items;
    }

}
