package gg.pixelarcade.skywars.leveling.enums

import lombok.Getter

@Getter
enum class LevelEnum(
    val priority: Int,
    val minLevel: Int,
    val color: String,
    val xpPerLevel: Int,
    val levelName: String
) {
    ROOKIE(0, 0, "&7", 50, "Rookie"),
    VOYAGER(1, 10, "&f", 100, "Voyager"),
    Sentinel(2, 20, "&6", 250, "Sentinel"),
    Phantom(3, 30, "&b", 500, "Phantom"),
    Ascendant(4, 40, "&2", 1000, "Ascendant"),
    Nebula(5, 50, "&3", 1500, "Nebula"),
    Apex(6, 60, "&4", 2500, "Apex"),
    Eclipse(7, 70, "&d", 5000, "Eclipse"),
    Chrono(8, 80, "&9", 10000, "Chrono"),
    Celestial(9, 90, "&5", 20000, "Celestial"),
}
