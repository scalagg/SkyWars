package xyz.refinedev.skywars.kit.impl.ranked;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.kit.Kit;
import xyz.refinedev.skywars.kit.enums.Rarity;
import xyz.refinedev.skywars.pxSkyWars;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BlacksmithKit extends Kit {

    private final Material[] armorTypes = {
            Material.DIAMOND_HELMET,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_BOOTS
    };

    @Override
    public String getName() {
        return "Blacksmith";
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.RANKED_SOLO, GameType.RANKED_DOUBLES);
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.ANVIL)
                .setName("&aBlacksmith")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fIron Armor",
                        "&7■ &fIron Sword",
                        "&7■ &fIron Pickaxe",
                        "&7■ &fIron Axe",
                        "&7■ &f15 Levels",
                        "&7■ &f5 Random Enchanted Books",
                        "&7■ &f1 Random Diamond Armor Piece",
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
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public void apply(Player player) {
        for (int i = 0; i < 5; i++) {
            int enchantmentLevel = pxSkyWars.getInstance().getRandom().nextInt(3) + 1;
            Enchantment enchantment = Enchantment.values()[pxSkyWars.getInstance().getRandom().nextInt(Enchantment.values().length)];
            ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK);
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) enchantedBook.getItemMeta();
            meta.addStoredEnchant(enchantment, enchantmentLevel, true);
            enchantedBook.setItemMeta(meta);
            player.getInventory().addItem(enchantedBook);
        }

        int randomIndex = pxSkyWars.getInstance().getRandom().nextInt(armorTypes.length);
        Material randomArmorType = armorTypes[randomIndex];

        player.giveExpLevels(15);
        player.getInventory().addItem(new ItemBuilder(Material.ANVIL).toItemStack());
        player.getInventory().addItem(new ItemStack(randomArmorType));
        player.getInventory().addItem(new ItemBuilder(Material.IRON_SWORD).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_PICKAXE).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_AXE).toItemStack());
    }


}
