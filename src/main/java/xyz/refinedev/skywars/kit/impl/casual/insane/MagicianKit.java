package xyz.refinedev.skywars.kit.impl.casual.insane;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.INSANE_SOLO, GameType.INSANE_DOUBLES);
    }

    @Override
    public Rarity getRarity() {
        return Rarity.RARE;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.POTION)
                .addCustomPotionEffect(new PotionEffect(PotionEffectType.HEAL, 15, 1), true)
                .setName("&aMagician")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fSplash Potions of Harming 2",
                        "&7■ &f2 Splash Potion of Weakness &7&o(15s)",
                        "&7■ &f2 Splash Potion of Poison &7&o(15s)",
                        "&7■ &fSplash Potion of Invisibility &7&o(10s)",
                        "&7■ &fKnockback 1 Stick",
                        "&6&m---------------------"
                ))
                .toItemStack();
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 3).addCustomPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 2), true).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 15, 1), true).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.POISON, 15, 1), true).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10, 1), true).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.STICK).addUnsafeEnchantment(Enchantment.KNOCKBACK, 1).toItemStack());
    }
}
