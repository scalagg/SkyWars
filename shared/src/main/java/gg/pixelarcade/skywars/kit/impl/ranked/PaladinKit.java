package gg.pixelarcade.skywars.kit.impl.ranked;

import gg.pixelarcade.skywars.kit.Kit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.enums.KitRarity;

import java.util.Arrays;

public class PaladinKit extends Kit {
    @Override
    public String getName() {
        return "Paladin";
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public GameType getGameType() {
        return GameType.RANKED;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.DIAMOND_SWORD).setName("&aPaladin").setLore(Arrays.asList(
                "&6&m---------------------",
                "&7■ &fIron Sword",
                "&7■ &fIron Pickaxe",
                "&7■ &fIron Axe",
                "&7■ &fIron Armor",
                "&7■ &fDiamond Leggings (Protection II)",
                "&7■ &fSplash Potion of Resistance (15seconds)",
                "&6&m---------------------"
        ));
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.COMMON;
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {

    }
}
