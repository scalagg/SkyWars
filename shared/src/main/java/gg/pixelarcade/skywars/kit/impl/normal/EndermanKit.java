package gg.pixelarcade.skywars.kit.impl.normal;

import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.Arrays;

public class EndermanKit extends Kit {
//    TODO: Make sure the pearls have a cooldown of 30 seconds for this kit.
    @Override
    public String getName() {
        return "Enderman";
    }

    @Override
    public int getCost() {
        return 30000;
    }

    @Override
    public GameType getGameType() {
        return GameType.NORMAL;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.LEGENDARY;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.ENDER_PEARL)
                .setName("&aEnderman")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &f1 Ender Pearl",
                        "&6&m---------------------"
                ));
    }
    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
    }
}
