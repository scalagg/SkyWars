package gg.pixelarcade.skywars.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@UtilityClass
public class SpawnUtil {

    public void teleportToSpawn(Player player) {
        player.teleport(Bukkit.getWorld("world").getSpawnLocation());
    }

}
