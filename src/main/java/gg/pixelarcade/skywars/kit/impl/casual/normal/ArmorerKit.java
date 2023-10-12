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

public class ArmorerKit extends Kit {
    @Override
    public String getName() {
        return "Armorer";
    }

    @Override
    public int getCost() {
        return 20000;
    }

    @Override
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.CASUAL_SOLO, GameType.CASUAL_DOUBLES);
    }

    @Override
    public Rarity getRarity() {
        return Rarity.RARE;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.IRON_CHESTPLATE)
                .setName("&aArmorer")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fIron Chestplate",
                        "&7■ &fLeather Pants",
                        "&7■ &fLeather Boots",
                        "&6&m---------------------"
                ))
                .toItemStack();
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.IRON_CHESTPLATE).toItemStack(),
                new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherArmorColor(Color.GRAY).toItemStack(),
                new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.GRAY).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {

    }
}
