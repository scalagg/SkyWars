package gg.pixelarcade.skywars.kit.impl.casual.normal;

import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.Rarity;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.Arrays;
import java.util.List;

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
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.CASUAL_SOLO, GameType.CASUAL_DOUBLES);
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.LEATHER_CHESTPLATE)
                .setName("&aRookie")
                .setLore(Arrays.asList(
                                "&6&m---------------------",
                                "&7■ &fFull Leather Armor",
                                "&7■ &fStone Sword",
                                "&7■ &f16 Glass",
                                "&7■ &f16 Steak",
                                "&6&m---------------------"
                        ))
                .toItemStack();
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
