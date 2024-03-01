package gg.pixelarcade.skywars.kit.impl.normal;

import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.Arrays;

public class DefaultKit extends Kit {

    @Override
    public String getName() {
        return "Default";
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public GameType getGameType() {
        return GameType.NORMAL;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.COMMON;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.STONE_SWORD)
                .setName("&aDefault")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fStone Sword",
                        "&7■ &fStone Pickaxe",
                        "&7■ &fStone Axe",
                        "&7■ &fStone Shovel",
                        "&6&m---------------------"
                ));
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
        player.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
        player.getInventory().addItem(new ItemStack(Material.STONE_AXE));
        player.getInventory().addItem(new ItemStack(Material.STONE_SPADE));
    }
}
