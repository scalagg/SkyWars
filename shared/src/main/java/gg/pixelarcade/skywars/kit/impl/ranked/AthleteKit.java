package gg.pixelarcade.skywars.kit.impl.ranked;

import gg.pixelarcade.skywars.kit.Kit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.KitRarity;

public class AthleteKit extends Kit {

    public AthleteKit() {
        super("Athlete", Material.ARROW, 0, GameType.RANKED, KitRarity.COMMON);
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
