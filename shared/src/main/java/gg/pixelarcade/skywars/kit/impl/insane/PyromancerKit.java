package gg.pixelarcade.skywars.kit.impl.insane;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

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
        return GameType.INSANE;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.LEGENDARY;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.FLINT_AND_STEEL)
                .setName("&aPyromancer")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fFlint and Steel",
                        "&7■ &f3 Lava Bucket",
                        "&7■ &fDiamond Chestplate",
                        "&7■ &fOrange Leather Pants",
                        "&7■ &fSplash Fire Resistance &7&o(90s)",
                        "&6&m---------------------"
                ));
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.DIAMOND_CHESTPLATE).toItemStack(),
                new ItemBuilder(Material.LEATHER_LEGGINGS).setLeatherArmorColor(Color.ORANGE).toItemStack(),
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.FLINT_AND_STEEL));
        player.getInventory().addItem(new ItemStack(Material.LAVA_BUCKET, 3));
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 90, 1), true).toItemStack());
    }
}
