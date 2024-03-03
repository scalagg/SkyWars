package gg.pixelarcade.skywars.kit.impl.ranked;

import gg.pixelarcade.skywars.kit.Kit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.KitRarity;

public class PaladinKit extends Kit {

    public PaladinKit() {
        super("Paladin", Material.DIAMOND_SWORD, 0, GameType.RANKED, KitRarity.COMMON);
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {

    }
}
