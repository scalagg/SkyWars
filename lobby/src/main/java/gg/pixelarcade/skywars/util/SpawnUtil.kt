package gg.pixelarcade.skywars.util

import org.bukkit.Bukkit
import org.bukkit.entity.Player

object SpawnUtil {
    fun teleportToSpawn(player: Player) {
        player.teleport(Bukkit.getWorld("world").spawnLocation)
    }
}
