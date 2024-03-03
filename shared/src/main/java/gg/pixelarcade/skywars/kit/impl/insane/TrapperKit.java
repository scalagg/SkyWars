package gg.pixelarcade.skywars.kit.impl.insane;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.KitRarity;

public class TrapperKit extends Kit {

    public TrapperKit() {
        super("Trap Master", Material.PISTON_BASE, 20000, GameType.INSANE, KitRarity.RARE);
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.TRIPWIRE_HOOK, 4));
        player.getInventory().addItem(new ItemStack(Material.WEB, 24));
        player.getInventory().addItem(new ItemStack(Material.PISTON_BASE, 8));
        player.getInventory().addItem(new ItemStack(Material.SLIME_BALL, 8));
        player.getInventory().addItem(new ItemStack(Material.REDSTONE, 32));
        player.getInventory().addItem(new ItemStack(Material.LEVER, 2));
        player.getInventory().addItem(new ItemStack(Material.DISPENSER, 4));
        player.getInventory().addItem(new ItemStack(Material.ARROW, 32));
    }
}
