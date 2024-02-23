package gg.pixelarcade.skywars.kit.impl.casual.insane;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.Rarity;

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
        return Arrays.asList(GameType.INSANE_SOLO, GameType.INSANE_DOUBLES);
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
                        "&7■ &fDiamond Chestplate",
                        "&7■ &fChain Pants",
                        "&7■ &fAqua Leather Boots",
                        "&6&m---------------------"
                ))
                .toItemStack();
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.DIAMOND_CHESTPLATE).toItemStack(),
                new ItemBuilder(Material.CHAINMAIL_LEGGINGS).toItemStack(),
                new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.AQUA).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {

    }
}
