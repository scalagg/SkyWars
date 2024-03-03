package gg.pixelarcade.skywars.kit.impl.insane;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.KitRarity;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

public class RookieKit extends Kit {

    public RookieKit() {
        super("Rookie", Material.LEATHER_CHESTPLATE, 15000, GameType.INSANE, KitRarity.COMMON);
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.IRON_HELMET).setLeatherArmorColor(Color.GRAY).toItemStack(),
                new ItemBuilder(Material.IRON_CHESTPLATE).setLeatherArmorColor(Color.GRAY).toItemStack(),
                new ItemBuilder(Material.IRON_LEGGINGS).setLeatherArmorColor(Color.GRAY).toItemStack(),
                new ItemBuilder(Material.IRON_BOOTS).setLeatherArmorColor(Color.GRAY).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        player.getInventory().addItem(new ItemStack(Material.GLASS, 24));
        player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 5));
    }
}
