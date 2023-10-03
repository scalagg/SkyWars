package xyz.refinedev.skywars.ability;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.ability
 * @date 03/10/2023 / 06:01
 */

public abstract class Ability implements Listener {

    public abstract String getName();

    public abstract ItemStack getItem();

    public boolean isSimilar(ItemStack item) {
        if (item == null) {
            System.out.println("null");
            return false;
        }

        if (item.getItemMeta() == null) {
            System.out.println("null 2");
            return false;
        }

        if (item.getItemMeta().getDisplayName() == null) {
            System.out.println("null 3");
            return false;
        }

        if (!item.getItemMeta().getDisplayName().equals(getItem().getItemMeta().getDisplayName())) {
            System.out.println("display name");
            return false;
        }

        if (!item.getItemMeta().getLore().equals(getItem().getItemMeta().getLore())) {
            System.out.println("lore");
            return false;
        }

        System.out.println(item.getType() == getItem().getType() ? "true" : "false");
        return item.getType() == getItem().getType();
    }

    public void consume(Player player) {
        if (player.getItemInHand().getAmount() == 1)
            player.setItemInHand(null);
        else player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
    }

}
