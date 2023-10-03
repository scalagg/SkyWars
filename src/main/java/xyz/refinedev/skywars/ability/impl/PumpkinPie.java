package xyz.refinedev.skywars.ability.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.ability.Ability;
import xyz.refinedev.skywars.ability.type.Interact;
import xyz.refinedev.skywars.pxSkyWars;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.ability.impl
 * @date 03/10/2023 / 06:12
 */

public class PumpkinPie extends Ability implements Interact {

    // speed 1 = 0, speed 2 = 1 etc
    private final static int SPEED_LEVEL = 0;
    private final static int SPEED_DISPLAY_LEVEL = 1;
    private final static int SPEED_DURATION = 4;

    @Override
    public String getName() {
        return Color.translate("&6&lPumpkin Pie");
    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.PUMPKIN_PIE)
                .setName(getName())
                .setLore(Color.translate("&7Right click to gain &a&lSpeed " + SPEED_DISPLAY_LEVEL + " &7for " + SPEED_DURATION + " seconds"))
                .toItemStack();
    }

    @Override
    public boolean onPlayerInteract(PlayerInteractEvent event) {
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, SPEED_DURATION * 20, SPEED_LEVEL));
        return true;
    }
}
