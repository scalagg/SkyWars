package gg.pixelarcade.skywars.kit.impl.normal;

import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.Arrays;

public class PyromancerKit extends Kit {
    @Override
    public String getName() {
        return "Pyromancer";
    }

    @Override
    public int getCost() {
        return 30000;
    }

    @Override
    public GameType getGameType() {
        return GameType.NORMAL;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.LEGENDARY;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.FLINT_AND_STEEL)
                .setName("&aPyromancer")
                .setLore(
                        "&6&m---------------------",
                        "&7■ &fFlint and Steel",
                        "&7■ &f2 Lava Bucket",
                        "&7■ &fChain Chestplate",
                        "&7■ &fOrange Leather Pants",
                        "&7■ &fOrange Leather Boots",
                        "&7■ &fSplash Fire Resistance &7&o(45s)",
                        "&6&m---------------------"
                );
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).toItemStack(),
                new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherArmorColor(Color.ORANGE).toItemStack(),
                new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.ORANGE).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.FLINT_AND_STEEL));
        player.getInventory().addItem(new ItemStack(Material.LAVA_BUCKET, 2));
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 45, 1), true).toItemStack());
    }
}
