package xyz.refinedev.skywars.kit.impl.casual.normal;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.kit.Kit;
import xyz.refinedev.skywars.kit.enums.Rarity;

import java.util.Arrays;
import java.util.List;

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
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.CASUAL_SOLO, GameType.CASUAL_DOUBLES);
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.ENCHANTMENT_TABLE)
                .setName("&aEnchanter")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fEnchanting Table",
                        "&7■ &f64 XP Bottles",
                        "&7■ &f8 Bookshelf",
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
        player.getInventory().addItem(new ItemStack(Material.ENCHANTMENT_TABLE));
        player.getInventory().addItem(new ItemStack(Material.BOOKSHELF, 8));
        player.getInventory().addItem(new ItemStack(Material.EXP_BOTTLE, 64));
    }
}
