package gg.pixelarcade.skywars.utils;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author Creaxx
 * Created At: 5/13/2023
 */

@UtilityClass
public class SpawnUtil {

    public void teleportToSpawn(Player player) {
        player.teleport(Bukkit.getWorld("world").getSpawnLocation());

        SkyWarsPlugin.getInstance().giveItems(player);
    }

}
