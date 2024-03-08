package gg.pixelarcade.skywars.menus.utils;

import gg.pixelarcade.skywars.SkyWarsShared;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

public class CoinsButton extends Button {
    @Override
    public ItemStack getItem(Player player) {
        int totalCoins = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getCoins();

        return new ItemBuilder(XMaterial.EMERALD)
                .setName(ChatColor.GOLD + "Your Coins: " + totalCoins)
                .toItemStack();
    }
}
