package xyz.refinedev.skywars.kit.impl.casual.insane;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.kit.Kit;
import xyz.refinedev.skywars.kit.enums.Rarity;

import java.util.Arrays;
import java.util.List;

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
    public List<GameType> getGameType() {
        return Arrays.asList(GameType.INSANE_SOLO, GameType.INSANE_DOUBLES);
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }
    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.DIAMOND_PICKAXE)
                .setName("&aMiner")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fIron Pick Efficiency 3 Sharpness 1 Unbreaking 3",
                        "&7■ &f24 Stone",
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
        player.getInventory().addItem(new ItemBuilder(Material.IRON_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 3).addEnchant(Enchantment.DAMAGE_ALL, 1).addEnchant(Enchantment.DURABILITY, 3).toItemStack());
        player.getInventory().addItem(new ItemStack(Material.STONE, 24));
    }
}
