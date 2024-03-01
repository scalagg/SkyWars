package gg.pixelarcade.skywars.kit.impl.normal;

import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.Arrays;

public class MagicianKit extends Kit {
    @Override
    public String getName() {
        return "Magician";
    }

    @Override
    public int getCost() {
        return 20000;
    }

    @Override
    public GameType getGameType() {
        return GameType.NORMAL;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.RARE;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.POTION)
                .addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 15, 1), true)
                .setName("&aMagician")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fSplash Potions of Harming",
                        "&7■ &f2 Splash Potion of Weakness &7&o(10s)",
                        "&7■ &f2 Splash Potion of Poison &7&o(10s)",
                        "&7■ &fSplash Potion of Invisibility &7&o(10s)",
                        "&7■ &fKnockback 1 Stick",
                        "&6&m---------------------"
                ));
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 3).addCustomPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 1), true).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10, 1), true).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.POISON, 10, 1), true).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10, 1), true).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.STICK).addUnsafeEnchantment(Enchantment.KNOCKBACK, 1).toItemStack());
    }
}
