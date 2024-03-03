package gg.pixelarcade.skywars.kit.impl.insane;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.KitRarity;

public class SalmonKit extends Kit {

    public SalmonKit() {
        super("Salmon", Material.COOKED_FISH, 30000, GameType.INSANE, KitRarity.LEGENDARY);
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.IRON_HELMET).addEnchant(Enchantment.WATER_WORKER, 1).addEnchant(Enchantment.OXYGEN, 3).toItemStack(),
                new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherArmorColor(Color.RED).toItemStack(),
                new ItemBuilder(Material.IRON_LEGGINGS).toItemStack(),
                new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.RED).addEnchant(Enchantment.DEPTH_STRIDER, 3).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.WATER_BUCKET, 3));
    }
}
