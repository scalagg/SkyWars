package gg.pixelarcade.skywars.kit.impl.casual.normal;

import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.Arrays;
import java.util.List;

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
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.CASUAL_SOLO, GameType.CASUAL_DOUBLES);
    }

    @Override
    public Rarity getRarity() {
        return Rarity.RARE;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.IRON_AXE)
                .setName("&aRusher")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fIron Axe Efficiency 1",
                        "&7■ &f16 Oak Wood",
                        "&7■ &f2 Speed 1 Potions &7&o(15s)",
                        "&7■ &fIron Boots Feather Falling 2",
                        "&6&m---------------------"
                ))
                .toItemStack();
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_FALL, 2).toItemStack()
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.IRON_AXE));
        player.getInventory().addItem(new ItemStack(Material.WOOD, 16));
        player.getInventory().addItem(new ItemBuilder(Material.POTION, 2).addCustomPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 1), true).toItemStack());
    }
}
