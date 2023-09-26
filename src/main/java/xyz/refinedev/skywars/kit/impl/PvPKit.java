package xyz.refinedev.skywars.kit.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.kit.Kit;

import java.util.List;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.kit.impl
 * @date 26/09/2023 / 02:44
 */

public class PvPKit extends Kit {

    @Override
    public String getName() {
        return "PvP";
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.IRON_SWORD)
                .setName(Color.translate("&bPvP Kit"))
                .toItemStack();
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.IRON_LEGGINGS).toItemStack(),
                new ItemBuilder(Material.IRON_CHESTPLATE).toItemStack(),
        };
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemBuilder(Material.IRON_SWORD).toItemStack());
    }
}
