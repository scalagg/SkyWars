package gg.pixelarcade.skywars.kit.impl.normal;

import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.Arrays;

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
    public GameType getGameType() {
        return GameType.NORMAL;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.LEGENDARY;
    }

    @Override
    public ItemBuilder getDisplayItem() {
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
                ));
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.LEATHER_HELMET).setLeatherArmorColor(Color.RED).addEnchant(Enchantment.WATER_WORKER, 1).addEnchant(Enchantment.OXYGEN, 3).toItemStack(),
                new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherArmorColor(Color.RED).toItemStack(),
                new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherArmorColor(Color.RED).toItemStack(),
                new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.RED).addEnchant(Enchantment.DEPTH_STRIDER, 3).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.WATER_BUCKET, 3));
    }
}
