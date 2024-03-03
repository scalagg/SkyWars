package gg.pixelarcade.skywars.kit.impl.insane;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.KitRarity;

public class EnchanterKit extends Kit {

    public EnchanterKit() {
        super("Enchanter", Material.ENCHANTMENT_TABLE, 15000, GameType.INSANE, KitRarity.COMMON);
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.DIAMOND_HELMET).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.ENCHANTMENT_TABLE, 2));
        player.getInventory().addItem(new ItemStack(Material.BOOKSHELF, 12));
        player.getInventory().addItem(new ItemStack(Material.EXP_BOTTLE, 64));
        player.getInventory().addItem(new ItemStack(Material.BOOK, 4));

    }
}
