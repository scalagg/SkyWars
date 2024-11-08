package gg.pixelarcade.skywars.cages

import org.bukkit.util.Vector
import java.util.*

data class LocalCage(
    val cage: SchemaCage,
    val location: Vector,
    val owner: UUID
)
