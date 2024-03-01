package gg.pixelarcade.skywars.kit.impl.normal;

import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.Arrays;

public class RookieKit extends Kit {
    @Override
    public String getName() {
        return "Rookie";
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
        return new ItemBuilder(Material.LEATHER_CHESTPLATE)
                .setName("&aRookie")
                .setLore(
                        "&6&m---------------------",
                        "&7■ &fFull Leather Armor",
                        "&7■ &fStone Sword",
                        "&7■ &f16 Glass",
                        "&7■ &f16 Steak",
                        "&6&m---------------------"
                );
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.GRAY).toItemStack(),
                new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherArmorColor(Color.GRAY).toItemStack(),
                new ItemBuilder(Material.LEATHER_CHESTPLATE).setLeatherArmorColor(Color.GRAY).toItemStack(),
                new ItemBuilder(Material.LEATHER_HELMET).setLeatherArmorColor(Color.GRAY).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
        player.getInventory().addItem(new ItemStack(Material.GLASS, 16));
        player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));
    }
}
