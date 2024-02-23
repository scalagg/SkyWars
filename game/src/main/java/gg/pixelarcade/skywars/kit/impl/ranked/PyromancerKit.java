package gg.pixelarcade.skywars.kit.impl.ranked;

import gg.pixelarcade.skywars.kit.Kit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.enums.Rarity;

import java.util.Arrays;
import java.util.List;

public class PyromancerKit extends Kit {
    @Override
    public String getName() {
        return "Pyromancer";
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
        return new ItemBuilder(Material.LAVA_BUCKET)
                .setName("&aPyromancer").setLore(Arrays.asList(
                "&6&m---------------------",
                "&7■ &fSplash Potion of Fire Resistance (90seconds)",
                "&7■ &fDiamond Sword (Fire Aspect II)",
                "&7■ &fDiamond Boots (Protection III, Fire Protection IV)",
                "&7■ &fLava Bucket x2 (using 2 hotbars)",
                "&7■ &fIron Pickaxe",
                "&7■ &fIron Axe",
                "&7■ &fIron Helmet",
                "&7■ &fIron Chestplate",
                "&7■ &fIron Leggings",
                "&6&m---------------------"
        )).toItemStack();
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemStack(new ItemBuilder(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchant(Enchantment.PROTECTION_FIRE, 4).toItemStack()),
                new ItemStack(Material.IRON_CHESTPLATE),
                new ItemStack(Material.IRON_LEGGINGS),
                new ItemStack(Material.IRON_BOOTS)
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 25 * 90, 1), true).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.FIRE_ASPECT, 2).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.LAVA_BUCKET, 2).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_AXE).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_PICKAXE).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_HELMET).toItemStack());
    }
}
