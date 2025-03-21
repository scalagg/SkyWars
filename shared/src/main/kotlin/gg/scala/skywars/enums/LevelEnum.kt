package gg.scala.skywars.enums

import net.evilblock.cubed.util.CC


enum class LevelEnum(
    val priority: Int,
    val minLevel: Int,
    val color: String,
    val xpPerLevel: Int,
    val levelName: String,
    val star: String,
) {
    ROOKIE(0, 0, CC.GRAY, 50, "Rookie", "✯"),
    VOYAGER(1, 10, CC.WHITE, 100, "Voyager", "✯"),
    Sentinel(2, 20, CC.GOLD, 250, "Sentinel", "✯"),
    Phantom(3, 30, CC.AQUA, 500, "Phantom", "✯"),
    Ascendant(4, 40, CC.GREEN, 1000, "Ascendant", "✯"),
    Nebula(5, 50, CC.DARK_AQUA, 1500, "Nebula", "✯"),
    Apex(6, 60, CC.D_RED, 2500, "Apex", "✯"),
    Eclipse(7, 70, CC.PINK, 5000, "Eclipse", "✯"),
    Chrono(8, 80, CC.BLUE, 10000, "Chrono", "✯"),
    Celestial(9, 90, CC.D_PURPLE, 20000, "Celestial", "✯"),
}
