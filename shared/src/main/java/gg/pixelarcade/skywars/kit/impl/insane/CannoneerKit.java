package gg.pixelarcade.skywars.kit.impl.insane;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.KitRarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

public class CannoneerKit extends Kit {

    public CannoneerKit() {
        super("Canoneer", Material.TNT, 30000, GameType.INSANE, KitRarity.LEGENDARY);
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.DIAMOND_BOOTS)
                        .addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 3)
                        .addEnchant(Enchantment.PROTECTION_FALL, 4)
                        .toItemStack()
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
