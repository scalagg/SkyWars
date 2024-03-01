package gg.pixelarcade.skywars.kit.impl.insane;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.Kit;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;

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
    public GameType getGameType() {
        return GameType.INSANE;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.RARE;
    }

    @Override
    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.BOW)
                .setName("&aSniper")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fBow Power 2",
                        "&7■ &f24 Arrows",
                        "&6&m---------------------"
                ));
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
