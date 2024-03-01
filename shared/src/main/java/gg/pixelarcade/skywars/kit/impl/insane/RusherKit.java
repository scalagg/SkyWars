package gg.pixelarcade.skywars.kit.impl.insane;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;

public class RusherKit extends Kit {
    @Override
    public String getName() {
        return "Rusher";
    }

    @Override
    public int getCost() {
        return 20000;
    }

    @Override
    public GameType getGameType() {
        return GameType.INSANE;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.RARE;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.IRON_AXE)
                .setName("&aRusher")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fDiamond Axe Efficiency 1 Sharpness 1",
                        "&7■ &f16 Oak Wood",
                        "&7■ &f2 Speed 2 Potions &7&o(20s)",
                        "&7■ &f2 Jump 2 Potions &7&o(20s)",
                        "&7■ &fIron Chestplate",
                        "&7■ &fIron Boots Feather Falling 2",
                        "&6&m---------------------"
                ));
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.IRON_BOOTS).addEnchant(Enchantment.PROTECTION_FALL, 2).toItemStack(),
                new ItemBuilder(Material.IRON_CHESTPLATE).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_AXE).addEnchant(Enchantment.DAMAGE_ALL, 1).addEnchant(Enchantment.DIG_SPEED, 1).toItemStack());
        player.getInventory().addItem(new ItemStack(Material.WOOD, 16));
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 2), true).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, 2), true).toItemStack());
    }
}
