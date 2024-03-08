package gg.pixelarcade.skywars.menus.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

public class ExitButton extends Button {
    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(XMaterial.BARRIER)
                .setName(ChatColor.RED + "EXIT")
                .setLore(
                        "",
                        ChatColor.GRAY + "Click to Exit!")
                .toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType){
        player.closeInventory();
    }
}
