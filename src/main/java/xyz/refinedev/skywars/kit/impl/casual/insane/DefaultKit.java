package xyz.refinedev.skywars.kit.impl.casual.insane;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.kit.Kit;
import xyz.refinedev.skywars.kit.enums.Rarity;

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
        return Arrays.asList(GameType.INSANE_SOLO, GameType.INSANE_DOUBLES);
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.IRON_SWORD)
                .setName("&aDefault")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fIron Sword",
                        "&7■ &fIron Pickaxe",
                        "&7■ &fIron Axe",
                        "&7■ &fIron Shovel",
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
        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
        player.getInventory().addItem(new ItemStack(Material.IRON_AXE));
        player.getInventory().addItem(new ItemStack(Material.IRON_SPADE));
    }
}
