package gg.pixelarcade.skywars.kit.impl.ranked;

import gg.pixelarcade.skywars.kit.Kit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.enums.KitRarity;

import java.util.Arrays;

public class ArmorerKit extends Kit {

    @Override
    public String getName() {
        return "Armorer";
    }

    @Override
    public int getCost() {
        return 0;
    }

    public ItemBuilder getDisplayItem() {
        return new ItemBuilder(Material.DIAMOND_CHESTPLATE)
                .setName("&aArmorer")
                .setLore(Arrays.asList(
                        "&6&m---------------------",
                        "&7■ &fDiamond Chestplate (Protection I)",
                        "&7■ &fDiamond Boots (Protection I)",
                        "&7■ &fIron Pickaxe",
                        "&7■ &fIron Axe",
                        "&6&m---------------------"
                ));
    }

    @Override
    public GameType getGameType() {
        return GameType.RANKED;
    }

    @Override
    public KitRarity getRarity() {
        return KitRarity.COMMON;
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.DIAMOND_CHESTPLATE)
                        .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                        .toItemStack(),
                new ItemBuilder(Material.DIAMOND_BOOTS).toItemStack()

        };

    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.IRON_PICKAXE).toItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.IRON_AXE).toItemStack());
    }
}
