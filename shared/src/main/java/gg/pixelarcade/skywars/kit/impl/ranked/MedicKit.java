package gg.pixelarcade.skywars.kit.impl.ranked;

import gg.pixelarcade.skywars.kit.Kit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.enums.KitRarity;

import java.util.Arrays;

public class MedicKit extends Kit {

    @Override
    public String getName() {
        return "Medic";
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public GameType getGameType() {
        return GameType.RANKED;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.POTION).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 25 * 20, 1), false).setName("&aMedic").setLore(Arrays.asList(
                "&6&m---------------------",
                "&7■ &fSplash Potion of Regeneration II (12 seconds) x2",
                "&7■ &fSplash Potion of Instant Health II x2",
                "&7■ &fIron Sword",
                "&7■ &fIron Pickaxe",
                "&7■ &fIron Axe",
                "&7■ &fIron Helmet",
                "&7■ &fIron Chestplate",
                "&7■ &fIron Leggings",
                "&7■ &fIron Boots",
                "&7■ &fGolden Apple x2",
                "&6&m---------------------"
        ));
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.COMMON;
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
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 25 * 12, 1), true).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1), true).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_SWORD).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_AXE).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_PICKAXE).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.GOLDEN_APPLE, 2).toItemStack());

    }
}
