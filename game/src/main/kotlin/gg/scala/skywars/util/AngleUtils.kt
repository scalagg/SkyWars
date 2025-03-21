package gg.scala.skywars.util

import org.bukkit.block.BlockFace
import java.util.*

object AngleUtils {
    private val NOTCHES: MutableMap<BlockFace, Int> = EnumMap(
        BlockFace::class.java
    )

    init {
        val radials = arrayOf(
            BlockFace.WEST,
            BlockFace.NORTH_WEST,
            BlockFace.NORTH,
            BlockFace.NORTH_EAST,
            BlockFace.EAST,
            BlockFace.SOUTH_EAST,
            BlockFace.SOUTH,
            BlockFace.SOUTH_WEST
        )

        for (i in radials.indices) {
            NOTCHES[radials[i]] = i
        }
    }

    @JvmStatic
    fun faceToYaw(face: BlockFace): Int {
        return wrapAngle(45 * NOTCHES.getOrDefault(face, 0))
    }

    private fun wrapAngle(angle: Int): Int {
        var wrappedAngle = angle

        while (wrappedAngle <= -180) {
            wrappedAngle += 360
        }

        while (wrappedAngle > 180) {
            wrappedAngle -= 360
        }

        return wrappedAngle
    }
}