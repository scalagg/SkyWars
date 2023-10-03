package xyz.refinedev.skywars.ability;

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

    public boolean isSimilar(ItemStack itemStack) {
        if (this.getItem().getItemMeta().getDisplayName() != itemStack.getItemMeta().getDisplayName())
            return false;

        if (this.getItem().getItemMeta().getLore().size() != itemStack.getItemMeta().getLore().size())
            return false;

        return true;
    }

}
