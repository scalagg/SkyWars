package gg.pixelarcade.skywars.kit.impl.insane;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.KitRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EndermanKit extends Kit {

    public EndermanKit() {
        super("Enderman", Material.ENDER_PEARL, 30000, GameType.INSANE, KitRarity.LEGENDARY);
    }

    //    TODO: Make sure the pearls have a cooldown of 30 seconds for this kit.

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 2));
    }
}
