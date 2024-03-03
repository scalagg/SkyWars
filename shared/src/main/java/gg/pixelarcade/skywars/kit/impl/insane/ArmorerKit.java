package gg.pixelarcade.skywars.kit.impl.insane;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.KitRarity;

public class ArmorerKit extends Kit {

    public ArmorerKit() {
        super("Armorer", Material.IRON_CHESTPLATE, 20000, GameType.INSANE, KitRarity.RARE);
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.DIAMOND_CHESTPLATE).toItemStack(),
                new ItemBuilder(Material.CHAINMAIL_LEGGINGS).toItemStack(),
                new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.AQUA).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {

    }
}
