package gg.pixelarcade.skywars.ability.impl;

import gg.pixelarcade.skywars.ability.Ability;
import gg.pixelarcade.skywars.ability.type.Interact;
import gg.pixelarcade.skywars.utils.Vector3D;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.ability.impl
 * @date 17/10/2023 / 17:06
 */

public class Magnet extends Ability implements Interact {

    @Override
    public String getName() {
        return Color.translate("&d&lMagnet");
    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.IRON_BARDING)
                .setName(getName())
                .addLoreLine(Color.translate("&7Click in the direction of a nearby player to pull them towards you."))
                .addLoreLine(" ")
                .addLoreLine(Color.translate("&e&lNOTE: &fThe heavier the armor the greater the force."))
                .toItemStack();
    }

    @Override
    public boolean onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Player target = getTargetPlayer(player);

        if (target == null) {
            player.sendMessage(Color.translate("&c&lERROR: &fCould not find a player to pull."));
            return false;
        }

        double armorWeight = getArmorForce(target.getInventory().getArmorContents());
        double power = armorWeight * 0.5;

        Location location = player.getLocation();
        Location targetLocation = target.getLocation();
        Vector direction = targetLocation.toVector().subtract(location.toVector()).normalize();
        direction.multiply(-1);

        target.setVelocity(direction.multiply(power));
        return true;
    }

    private Player getTargetPlayer(Player player) {
        Player targetPlayer = null;
        Location playerPos = player.getEyeLocation();
        Vector3D playerDir = new Vector3D(playerPos.getDirection());
        Vector3D playerStart = new Vector3D(playerPos);
        Vector3D playerEnd = playerStart.add(playerDir.multiply(100));

        for (Player p : player.getWorld().getPlayers()) {
            Vector3D targetPos = new Vector3D(p.getLocation());
            Vector3D minimum = targetPos.add(-0.5, 0, -0.5);
            Vector3D maximum = targetPos.add(0.5, 1.67, 0.5);

            if (p != player && hasIntersection(playerStart, playerEnd, minimum, maximum)) {
                if (targetPlayer == null || targetPlayer.getLocation().distanceSquared(playerPos) > p.getLocation().distanceSquared(playerPos)) {
                    targetPlayer = p;
                }
            }
        }

        return targetPlayer;
    }

    private boolean hasIntersection(Vector3D p1, Vector3D p2, Vector3D min, Vector3D max) {
        final double epsilon = 0.0001f;
        Vector3D d = p2.subtract(p1).multiply(0.5);
        Vector3D e = max.subtract(min).multiply(0.5);
        Vector3D c = p1.add(d).subtract(min.add(max).multiply(0.5));
        Vector3D ad = d.abs();

        if (Math.abs(c.x) > e.x + ad.x) return false;
        if (Math.abs(c.y) > e.y + ad.y) return false;
        if (Math.abs(c.z) > e.z + ad.z) return false;

        if (Math.abs(d.y * c.z - d.z * c.y) > e.y * ad.z + e.z * ad.y + epsilon) return false;
        if (Math.abs(d.z * c.x - d.x * c.z) > e.z * ad.x + e.x * ad.z + epsilon) return false;

        return !(Math.abs(d.x * c.y - d.y * c.x) > e.x * ad.y + e.y * ad.x + epsilon);
    }

    // might need to change values, would be good to test on closed beta while playing
    private double getArmorForce(ItemStack[] contents) {
        double armorWeight = 0;
        for (ItemStack armor : contents) {
            switch (armor.getType()) {
                case DIAMOND_HELMET, DIAMOND_CHESTPLATE, DIAMOND_LEGGINGS, DIAMOND_BOOTS -> armorWeight = 4;
                case IRON_HELMET, IRON_CHESTPLATE, IRON_LEGGINGS, IRON_BOOTS -> armorWeight = 3;
                case CHAINMAIL_HELMET, CHAINMAIL_CHESTPLATE, CHAINMAIL_LEGGINGS, CHAINMAIL_BOOTS -> armorWeight = 2;
                case GOLD_HELMET, GOLD_CHESTPLATE, GOLD_LEGGINGS, GOLD_BOOTS -> armorWeight = 1;
                case LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_LEGGINGS, LEATHER_BOOTS -> armorWeight = 0.8;
                case AIR -> armorWeight = 0.4;
            }
        }

        return armorWeight;
    }

}
