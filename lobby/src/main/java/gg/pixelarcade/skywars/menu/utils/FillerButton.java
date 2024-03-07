package gg.pixelarcade.skywars.menu.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

public class FillerButton extends Button{

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 4)
                .setName("&r")
                .toItemStack();
    }
}
