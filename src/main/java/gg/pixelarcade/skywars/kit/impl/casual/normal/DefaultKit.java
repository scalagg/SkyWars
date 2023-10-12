package gg.pixelarcade.skywars.kit.impl.casual.normal;

import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.Arrays;
import java.util.List;

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
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.CASUAL_SOLO, GameType.CASUAL_DOUBLES);
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.STONE_SWORD)
                .setName("&aDefault")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fStone Sword",
                        "&7■ &fStone Pickaxe",
                        "&7■ &fStone Axe",
                        "&7■ &fStone Shovel",
                        "&6&m---------------------"
                ))
                .toItemStack();
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
