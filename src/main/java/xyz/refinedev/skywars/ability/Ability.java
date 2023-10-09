package xyz.refinedev.skywars.ability;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.pxSkyWars;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.ability
 * @date 03/10/2023 / 06:01
 */

public abstract class Ability implements Listener {

    public Ability() {
        Bukkit.getServer().getPluginManager().registerEvents(this, pxSkyWars.getInstance());
    }

    public abstract String getName();
    public abstract ItemStack getItem();

    public boolean isSimilar(ItemStack item) {
        if (item == null)
            return false;

        if (!item.getItemMeta().getDisplayName().equals(this.getItem().getItemMeta().getDisplayName()))
            return false;

        if (!item.getItemMeta().getLore().equals(this.getItem().getItemMeta().getLore()))
            return false;

        return item.getType() == this.getItem().getType();
    }

    public void consume(Player player) {
        if (player.getItemInHand().getAmount() == 1)
            player.setItemInHand(null);
        else player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
    }

}
