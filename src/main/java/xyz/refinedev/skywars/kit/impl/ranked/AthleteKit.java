package xyz.refinedev.skywars.kit.impl.ranked;

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

public class AthleteKit extends Kit {
    @Override
    public String getName() {
        return "Athlete";
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.RANKED_SOLO, GameType.RANKED_DOUBLES);
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.ARROW)
                .setName("&aAthlete")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fSpeed 3 for 3 seconds",
                        "&7■ &fIron Armor",
                        "&7■ &fDiamond Sword",
                        "&7■ &fIron Axe",
                        "&7■ &fIron Pickaxe",
                        "&7■ &f2 Golden Apples",
                        "&7■ &fFishing Rod",
                        "&7■ &fWater Bucket",
                        "&7■ &fStrength 2 for 45 seconds",
                        "&6&m---------------------"
                ))
                .toItemStack();
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemStack(Material.IRON_HELMET),
                new ItemStack(Material.IRON_CHESTPLATE),
                new ItemStack(Material.IRON_LEGGINGS),
                new ItemStack(Material.IRON_BOOTS)
        };
    }

    @Override
    public Rarity getRarity() {
        return Rarity.RARE;
    }

    @Override
    public void apply(Player player) {
            player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).toItemStack());
            player.getInventory().addItem(new ItemBuilder(Material.IRON_AXE).toItemStack());
            player.getInventory().addItem(new ItemBuilder(Material.IRON_PICKAXE).toItemStack());
            player.getInventory().addItem(new ItemBuilder(Material.GOLDEN_APPLE, 2).toItemStack());
            player.getInventory().addItem(new ItemBuilder(Material.FISHING_ROD).toItemStack());
            player.getInventory().addItem(new ItemBuilder(Material.WATER_BUCKET).toItemStack());
            player.getInventory().addItem(new ItemBuilder(Material.POTION, 1).addCustomPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 45, 1), false).toItemStack());
            player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 3, 3), true).toItemStack());
    }
}
