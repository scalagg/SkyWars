package gg.pixelarcade.skywars.kit.impl.normal;

import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.KitRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import gg.pixelarcade.skywars.game.state.GameType;

public class TrapperKit extends Kit {

    public TrapperKit() {
        super("Trap Master", Material.PISTON_BASE, 20000, GameType.NORMAL, KitRarity.RARE);
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.TRIPWIRE_HOOK, 2));
        player.getInventory().addItem(new ItemStack(Material.WEB, 8));
        player.getInventory().addItem(new ItemStack(Material.PISTON_BASE, 4));
        player.getInventory().addItem(new ItemStack(Material.SLIME_BALL, 4));
        player.getInventory().addItem(new ItemStack(Material.REDSTONE, 16));
        player.getInventory().addItem(new ItemStack(Material.LEVER, 1));
        player.getInventory().addItem(new ItemStack(Material.DISPENSER, 2));
        player.getInventory().addItem(new ItemStack(Material.ARROW, 32));
    }
}
