//package xyz.refinedev.skywars.game.kit;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.bukkit.Material;
//import org.bukkit.enchantments.Enchantment;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.potion.PotionEffect;
//import org.bukkit.potion.PotionEffectType;
//import xyz.refinedev.phoenix.utils.item.ItemBuilder;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.function.Consumer;
//
///**
// * @author Creaxx
// * Created At: 5/13/2023
// */
//
//@Getter
//@RequiredArgsConstructor
//public enum PlayerKit {
//
//    SCOUT("Scout", Material.PAPER, Arrays.asList(
//            new ItemBuilder(Material.DIAMOND_AXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 1).addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1).toItemStack(),
//            new ItemBuilder(Material.DIAMOND_PICKAXE).addUnsafeEnchantment(Enchantment.DIG_SPEED, 1).toItemStack(),
//            new ItemBuilder(Material.POTION, 3).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 25 * 20, 1), true).toItemStack()
//    ), Arrays.asList(
//            new ItemStack(Material.IRON_HELMET),
//            new ItemStack(Material.IRON_CHESTPLATE),
//            new ItemStack(Material.IRON_LEGGINGS),
//            new ItemStack(Material.IRON_BOOTS)
//    )),
//
//    MAGICIAN("Magician", Material.PAPER, Arrays.asList(
//            new ItemStack(Material.DIAMOND_SWORD),
//            new ItemStack(Material.IRON_PICKAXE),
//            new ItemStack(Material.IRON_AXE),
//            new ItemStack(Material.MILK_BUCKET, 2),
//            new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 0), true).toItemStack(),
//            new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 0), true).toItemStack()
//    ), Arrays.asList(
//            new ItemStack(Material.IRON_HELMET),
//            new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 1).toItemStack(),
//            new ItemStack(Material.IRON_LEGGINGS),
//            new ItemStack(Material.IRON_BOOTS)
//    )),
//    ARMORER("Armorer", Material.PAPER, Arrays.asList(
//            new ItemStack(Material.IRON_PICKAXE),
//            new ItemStack(Material.IRON_AXE)
//    ), Arrays.asList(
//            new ItemStack(Material.IRON_HELMET),
//            new ItemBuilder(Material.DIAMOND_CHESTPLATE).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack(),
//            new ItemStack(Material.IRON_LEGGINGS),
//            new ItemStack(Material.DIAMOND_BOOTS)
//    )),
//    CHAMPION("Champion", Material.PAPER, Arrays.asList(
//            new ItemBuilder(Material.DIAMOND_SWORD).addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2).toItemStack(),
//            new ItemStack(Material.IRON_PICKAXE),
//            new ItemStack(Material.IRON_AXE),
//            new ItemStack(Material.ANVIL),
//            new ItemBuilder(Material.ENCHANTED_BOOK, 3).addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1).toItemStack()
//    ), Arrays.asList(
//            new ItemStack(Material.IRON_HELMET),
//            new ItemStack(Material.IRON_CHESTPLATE),
//            new ItemStack(Material.IRON_LEGGINGS),
//            new ItemStack(Material.IRON_BOOTS)
//    )),
//
//    BOWMAN("Bowman", Material.PAPER, Arrays.asList(
//            new ItemBuilder(Material.BOW).addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2).toItemStack(),
//            new ItemStack(Material.ARROW, 32),
//            new ItemStack(Material.IRON_SWORD),
//            new ItemStack(Material.IRON_PICKAXE),
//            new ItemStack(Material.IRON_AXE)
//    ), Arrays.asList(
//            new ItemBuilder(Material.DIAMOND_HELMET).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(),
//            new ItemStack(Material.IRON_CHESTPLATE),
//            new ItemStack(Material.IRON_LEGGINGS),
//            new ItemStack(Material.IRON_BOOTS)
//    )),
//
//    HEALER("Healer", Material.PAPER, Arrays.asList(
//            new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 12 * 20, 1), true).toItemStack(),
//            new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1), true).toItemStack(),
//            new ItemStack(Material.IRON_SWORD),
//            new ItemStack(Material.IRON_PICKAXE),
//            new ItemStack(Material.IRON_AXE),
//            new ItemStack(Material.GOLDEN_APPLE, 2)
//    ), Arrays.asList(
//            new ItemBuilder(Material.DIAMOND_HELMET).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack(),
//            new ItemStack(Material.IRON_CHESTPLATE),
//            new ItemStack(Material.IRON_LEGGINGS),
//            new ItemStack(Material.IRON_BOOTS)
//    )),
//
//    PYROMANCER("Pyromancer", Material.PAPER, Arrays.asList(
//            new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 90 * 20, 1), true).toItemStack(),
//            new ItemBuilder(Material.DIAMOND_SWORD).addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1).toItemStack(),
//            new ItemStack(Material.IRON_PICKAXE),
//            new ItemStack(Material.IRON_AXE),
//            new ItemStack(Material.LAVA_BUCKET, 1),
//            new ItemStack(Material.LAVA_BUCKET, 1)
//    ), Arrays.asList(
//            new ItemStack(Material.IRON_HELMET),
//            new ItemStack(Material.IRON_CHESTPLATE),
//            new ItemStack(Material.IRON_LEGGINGS),
//            new ItemBuilder(Material.DIAMOND_BOOTS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 6).toItemStack()
//    )),
//
//    Paladin("Paladin", Material.PAPER, Arrays.asList(
//            new ItemStack(Material.IRON_SWORD),
//            new ItemStack(Material.IRON_PICKAXE),
//            new ItemStack(Material.IRON_AXE),
//            new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 15 * 20, 1), true).toItemStack()
//    ), Arrays.asList(
//            new ItemStack(Material.IRON_HELMET),
//            new ItemStack(Material.IRON_CHESTPLATE),
//            new ItemStack(Material.IRON_LEGGINGS),
//            new ItemBuilder(Material.DIAMOND_BOOTS).addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).toItemStack()
//    )),
//
//
//    ;
//
//    private final String displayName;
//    private final Material material;
//    private final List<ItemStack> items;
//    private final List<ItemStack> armor;
//
//    public void giveKit(Player player) {
//        player.sendMessage("giving kit " + displayName);
//        player.getInventory().setContents(items.toArray(new ItemStack[36]));
//        player.updateInventory();
//    }
//
//}
