package gg.pixelarcade.skywars.kit.impl.normal;

import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.Arrays;

public class MinerKit extends Kit {
    @Override
    public String getName() {
        return "Miner";
    }

    @Override
    public int getCost() {
        return 15000;
    }

    @Override
    public GameType getGameType() {
        return GameType.NORMAL;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.COMMON;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.DIAMOND_PICKAXE)
                .setName("&aMiner")
                .setLore(
                        "&6&m---------------------",
                        "&7■ &fIron Pick Efficiency 3 Sharpness 1 Unbreaking 3",
                        "&7■ &f16 Stone",
                        "&6&m---------------------"
                );
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[0];
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.IRON_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 3).addEnchant(Enchantment.DAMAGE_ALL, 1).addEnchant(Enchantment.DURABILITY, 3).toItemStack());
        player.getInventory().addItem(new ItemStack(Material.STONE, 16));
    }
}
