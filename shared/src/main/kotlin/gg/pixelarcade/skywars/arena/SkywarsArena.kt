package gg.pixelarcade.skywars.arena

import gg.scala.cgs.common.information.arena.CgsGameArena
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import java.nio.file.Path

/**
 * @date 11/5/2024
 * @author Moose1301
 */
object SkywarsArena : CgsGameArena
{
    override fun getBukkitWorldName(): String {
        return "world"
    }

    override fun getDescription(): String {
         return "default"
    }

    override fun getDirectory(): Path? = null
    override fun getId(): String {
        return "rabbit"
    }

    override fun getMaterial(): Pair<Material, Int> {
        return Pair(Material.STONE, 1)
    }

    override fun getName(): String {
        return "Rabbit"
    }

    override fun getPreLobbyLocation() = Location(
        Bukkit.getWorld("world"),
        61935.0, 41.0, 23.0
    )

    override fun getSpectatorLocation() = getPreLobbyLocation()

}
