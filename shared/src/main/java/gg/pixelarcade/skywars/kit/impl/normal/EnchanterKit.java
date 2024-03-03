package gg.pixelarcade.skywars.kit.impl.normal;

import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.KitRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import gg.pixelarcade.skywars.game.state.GameType;

public class EnchanterKit extends Kit {

    public EnchanterKit() {
        super("Enchanter", Material.ENCHANTMENT_TABLE, 15000, GameType.NORMAL, KitRarity.COMMON);
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.ENCHANTMENT_TABLE));
        player.getInventory().addItem(new ItemStack(Material.BOOKSHELF, 8));
        player.getInventory().addItem(new ItemStack(Material.EXP_BOTTLE, 64));
    }
}
