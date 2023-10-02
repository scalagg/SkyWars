package xyz.refinedev.skywars.kit.impl.casual.normal;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.kit.Kit;
import xyz.refinedev.skywars.kit.enums.Rarity;

import java.util.Arrays;
import java.util.List;

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
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.CASUAL_SOLO, GameType.CASUAL_DOUBLES);
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.FLINT_AND_STEEL)
                .setName("&aPyromancer")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fFlint and Steel",
                        "&7■ &f2 Lava Bucket",
                        "&7■ &fChain Chestplate",
                        "&7■ &fOrange Leather Pants",
                        "&7■ &fOrange Leather Boots",
                        "&7■ &fSplash Fire Resistance &7&o(45s)",
                        "&6&m---------------------"
                ))
                .toItemStack();
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
