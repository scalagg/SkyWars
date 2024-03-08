package gg.pixelarcade.skywars.menus.utils;

import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

@RequiredArgsConstructor
public class BackButton extends Button {

    private final Menu previousMenu;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(XMaterial.ARROW)
                .setName(ChatColor.GOLD + "Go Back!")
                .setLore(
                        "",
                        ChatColor.GRAY + "Click to go back a page!"
                )
                .toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType){
        Phoenix.getInstance().getMenuHandler()
                .openMenu(previousMenu, player);
    }


}
