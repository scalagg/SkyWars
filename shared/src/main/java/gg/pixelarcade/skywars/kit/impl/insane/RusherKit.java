package gg.pixelarcade.skywars.kit.impl.insane;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.KitRarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

public class RusherKit extends Kit {

    public RusherKit() {
        super("Rusher", Material.IRON_AXE, 20000, GameType.INSANE, KitRarity.RARE);
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
