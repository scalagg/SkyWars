package xyz.refinedev.skywars.kit.impl.casual.normal;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.kit.Kit;
import xyz.refinedev.skywars.kit.enums.Rarity;

import java.util.Arrays;
import java.util.List;

public class SalmonKit extends Kit {
    @Override
    public String getName() {
        return "Salmon";
    }

    @Override
    public int getCost() {
        return 30000;
    }

    @Override
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.CASUAL_SOLO, GameType.CASUAL_DOUBLES);
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.COOKED_FISH)
                .setName("&aSalmon")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fRed Leather Cap Aqua Affinity 1 Respiration 3",
                        "&7■ &fRed Leather Chestplate",
                        "&7■ &fRed Leather Leggings",
                        "&7■ &fRed Leather Boots Depth Strider 3",
                        "&7■ &f3 Water bucket",
                        "&6&m---------------------"
                ))
                .toItemStack();
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.LEATHER_HELMET).setLeatherArmorColor(Color.RED).addEnchantment(Enchantment.WATER_WORKER, 1).addEnchantment(Enchantment.OXYGEN, 3).toItemStack(),
                new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherArmorColor(Color.RED).toItemStack(),
                new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherArmorColor(Color.RED).toItemStack(),
                new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.RED).addEnchantment(Enchantment.DEPTH_STRIDER, 3).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.WATER_BUCKET, 3));
    }
}
