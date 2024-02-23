package gg.pixelarcade.skywars.kit.impl.casual.insane;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;
import java.util.List;

public class CannoneerKit extends Kit {
    @Override
    public String getName() {
        return "Canoneer";
    }

    @Override
    public int getCost() {
        return 30000;
    }

    @Override
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.INSANE_SOLO, GameType.INSANE_DOUBLES);
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.TNT)
                .setName("&aCanoneer")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &f24 TNT",
                        "&7■ &f6 Redstone Block",
                        "&7■ &fDiamond Boots Feather 4, Blast Protection 3",
                        "&7■ &fWater Bucket",
                        "&7■ &f4 Wooden Pressure Plate",
                        "&6&m---------------------"
                ))
                .toItemStack();
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 3).addEnchant(Enchantment.PROTECTION_FALL, 4).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.TNT, 24));
        player.getInventory().addItem(new ItemStack(Material.REDSTONE_BLOCK, 4));
        player.getInventory().addItem(new ItemStack(Material.WATER_BUCKET));
        player.getInventory().addItem(new ItemStack(Material.WOOD_PLATE, 4));
    }
}
