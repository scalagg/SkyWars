package gg.scala.skywars.util

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.util.Vector

/**
 * @author alfie
 * @project Skywars / gg.scala.game.utils
 * @date 06/10/2023 / 15:29
 */
object VelocityUtil {
    @JvmStatic
    fun throwEntity(
        entity: Entity,
        vector: Vector,
        strength: Double,
        yAdd: Double,
        yMax: Double,
        groundBoost: Boolean
    ) {
        if (java.lang.Double.isNaN(vector.x) || java.lang.Double.isNaN(vector.y) || java.lang.Double.isNaN(vector.z)) return

        vector.normalize()
        vector.multiply(strength)
        vector.setY(vector.y + yAdd)

        if (vector.y > yMax) vector.setY(yMax)

        if (groundBoost) vector.setY(vector.y + 0.2)

        entity.fallDistance = 0f
        entity.velocity = vector
    }

    @JvmStatic
    fun knockback(player: Player, location: Location, strength: Double, yMax: Double) {
        val vector = player.location.toVector().subtract(location.toVector()).normalize()
        vector.multiply(strength).setY(yMax)

        player.fallDistance = 0f
        player.velocity = vector
    }
}
