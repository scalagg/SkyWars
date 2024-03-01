package gg.pixelarcade.skywars.kit.impl.normal;

import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;

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
        return GameType.NORMAL;
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
                        "&7■ &fEnchanting Table",
                        "&7■ &f64 XP Bottles",
                        "&7■ &f8 Bookshelf",
                        "&6&m---------------------"
                ));
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.ENCHANTMENT_TABLE));
        player.getInventory().addItem(new ItemStack(Material.BOOKSHELF, 8));
        player.getInventory().addItem(new ItemStack(Material.EXP_BOTTLE, 64));
    }
}
