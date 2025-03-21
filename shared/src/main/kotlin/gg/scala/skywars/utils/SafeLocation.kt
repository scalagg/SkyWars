package gg.scala.skywars.utils

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World

/**
 * @date 11/15/2024
 * @author Moose1301
 */
class SafeLocation(val x: Double, var y: Double, var z: Double, var yaw: Float, var pitch: Float) {


    constructor(location: Location) : this(location.x, location.y, location.z, location.yaw, location.pitch)

    fun toBukkit(world: World): Location = Location(world, x, y, z, yaw, pitch)

}