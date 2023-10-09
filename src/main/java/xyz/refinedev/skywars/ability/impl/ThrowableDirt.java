package xyz.refinedev.skywars.ability.impl;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;
import xyz.refinedev.skywars.ability.Ability;
import xyz.refinedev.skywars.ability.type.Interact;
import xyz.refinedev.skywars.utils.VelocityUtil;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.ability.impl
 * @date 06/10/2023 / 15:53
 */

public class ThrowableDirt extends Ability implements Interact {

    private final static int RADIUS = 4;

    @Override
    public String getName() {
        return Color.translate("&d&lThrowable Dirt");
    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.DIRT)
                .setName(getName())
                .setLore(Color.translate("&7Right click to throw dirt, anyone hit by it will be knocked back"))
                .addUnsafeEnchantment(Enchantment.DURABILITY, 1)
                .toItemStack();
    }

    @Override
    public boolean onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location location = player.getEyeLocation();

        FallingBlock fallingBlock = player.getWorld().spawnFallingBlock(location, Material.DIRT, (byte) 0);
        fallingBlock.setDropItem(false);

        VelocityUtil.throwEntity(fallingBlock, location.getDirection(), 1.3, 0.3, 1, false);

        return true;
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if (!(event.getEntity() instanceof FallingBlock fallingBlock))
            return;

        if (fallingBlock.getMaterial() != Material.DIRT)
            return;

        for (Entity entity : fallingBlock.getNearbyEntities(RADIUS, RADIUS, RADIUS)) {
            if (!(entity instanceof Player target))
                continue;

            VelocityUtil.knockback(target, fallingBlock.getLocation(), 1, 0.5);
            target.playSound(target.getLocation(), Sound.EXPLODE, 1, 1);
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (this.getItem().isSimilar(event.getItemInHand())) {
            event.setCancelled(true);
        }
    }

}
