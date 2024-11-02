package gg.pixelarcade.skywars.ability.impl;

import gg.pixelarcade.skywars.ability.Ability;
import gg.pixelarcade.skywars.ability.type.Interact;
import gg.pixelarcade.skywars.util.VelocityUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import net.evilblock.cubed.util.Color;
import net.evilblock.cubed.util.bukkit.ItemBuilder;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.ability.impl
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
                .name(getName())
                .addToLore(Color.translate("&7Right click to throw TNT"))
                .build();
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
        ItemStack item = event.getItemInHand();
        if (item == null || item.getType() != Material.TNT)
            return;

        if (!item.hasItemMeta() && !item.getItemMeta().hasDisplayName())
            return;

        if (!item.getItemMeta().getDisplayName().contains("Throwable"))
            return;

        event.setCancelled(true);
    }
}
