package xyz.refinedev.skywars.utils;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.utils
 * @date 06/10/2023 / 15:29
 */

public class VelocityUtil {

    public static void throwEntity(Entity entity, Vector vector, double strength, double yAdd, double yMax, boolean groundBoost) {
        if (Double.isNaN(vector.getX()) || Double.isNaN(vector.getY()) || Double.isNaN(vector.getZ()))
            return;

        vector.normalize();
        vector.multiply(strength);
        vector.setY(vector.getY() + yAdd);

        if (vector.getY() > yMax)
            vector.setY(yMax);

        if (groundBoost)
            vector.setY(vector.getY() + 0.2D);

        entity.setFallDistance(0);
        entity.setVelocity(vector);
    }

    public static void knockback(Player player, Location location, double strength, double yMax) {
        Vector vector = player.getLocation().toVector().subtract(location.toVector()).normalize();
        vector.multiply(strength).setY(yMax);

        player.setFallDistance(0);
        player.setVelocity(vector);
    }

}
