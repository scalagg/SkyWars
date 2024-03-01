package gg.pixelarcade.skywars.kit.impl.insane;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;

import java.util.Arrays;

public class EnchanterKit extends Kit {
    @Override
    public String getName() {
        return "Enchanter";
    }

    @Override
    public int getCost() {
        return 15000;
    }

    @Override
    public GameType getGameType() {
        return GameType.INSANE;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.COMMON;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.ENCHANTMENT_TABLE)
                .setName("&aEnchanter")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &f2 Enchanting Table",
                        "&7■ &f64 XP Bottles",
                        "&7■ &f12 Bookshelf",
                        "&7■ &f4 Book",
                        "&7■ &fDiamond Helmet",
                        "&6&m---------------------"
                ));
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.DIAMOND_HELMET).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.ENCHANTMENT_TABLE, 2));
        player.getInventory().addItem(new ItemStack(Material.BOOKSHELF, 12));
        player.getInventory().addItem(new ItemStack(Material.EXP_BOTTLE, 64));
        player.getInventory().addItem(new ItemStack(Material.BOOK, 4));

    }
}
