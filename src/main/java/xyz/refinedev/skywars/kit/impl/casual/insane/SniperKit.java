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

public class SniperKit extends Kit {
    @Override
    public String getName() {
        return "Sniper";
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
        return new ItemBuilder(Material.BOW)
                .setName("&aSniper")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fBow Power 2",
                        "&7■ &f24 Arrows",
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
        player.getInventory().addItem(new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 2).toItemStack());
        player.getInventory().addItem(new ItemStack(Material.ARROW, 24));
    }
}
