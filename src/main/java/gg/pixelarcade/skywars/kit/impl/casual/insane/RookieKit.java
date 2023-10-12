package gg.pixelarcade.skywars.kit.impl.casual.insane;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.Rarity;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

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
        return Arrays.asList(GameType.INSANE_SOLO, GameType.INSANE_DOUBLES);
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
                        "&7■ &fFull Iron Armor",
                        "&7■ &fIron Sword",
                        "&7■ &f24 Glass",
                        "&7■ &f5 Steak",
                        "&6&m---------------------"
                ))
                .toItemStack();
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.IRON_HELMET).setLeatherArmorColor(Color.GRAY).toItemStack(),
                new ItemBuilder(Material.IRON_CHESTPLATE).setLeatherArmorColor(Color.GRAY).toItemStack(),
                new ItemBuilder(Material.IRON_LEGGINGS).setLeatherArmorColor(Color.GRAY).toItemStack(),
                new ItemBuilder(Material.IRON_BOOTS).setLeatherArmorColor(Color.GRAY).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        player.getInventory().addItem(new ItemStack(Material.GLASS, 24));
        player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 5));
    }
}
