package gg.pixelarcade.skywars.ability.impl;

import gg.pixelarcade.skywars.ability.Ability;
import gg.pixelarcade.skywars.ability.type.Interact;
import gg.pixelarcade.skywars.utils.VelocityUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.ability.impl
 * @date 06/10/2023 / 15:20
 */

public class ThrowableTNT extends Ability implements Interact {

    private static final int FUSE_TICKS = 60;

    @Override
    public String getName() {
        return Color.translate("&6&lThrowable TNT");
    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.TNT)
                .setName(getName())
                .setLore(Color.translate("&7Right click to throw TNT"))
                .toItemStack();
    }

    @Override
    public boolean onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location location = player.getEyeLocation();

        TNTPrimed primedTNT = player.getWorld().spawn(location, TNTPrimed.class);
        primedTNT.setFuseTicks(FUSE_TICKS);

        VelocityUtil.throwEntity(primedTNT, location.getDirection(), 1.3, 0.3, 1, false);
        return true;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (this.getItem().isSimilar(event.getItemInHand()))
            event.setCancelled(true);
    }
}
