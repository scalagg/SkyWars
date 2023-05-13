package xyz.refinedev.skywars.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import xyz.refinedev.basic.config.BasicConfig;
import xyz.refinedev.phoenix.utils.server.LocationSerilization;

/**
 * @author Creaxx
 * Created At: 5/13/2023
 */

@UtilityClass
public class SpawnUtil {

    public void teleportToSpawn(Player player) {
        player.teleport(LocationSerilization.deserializeLocation(BasicConfig.IMP.SETTINGS.TELEPORT_LOCATION));
    }

}
