package gg.pixelarcade.skywars.util;

import gg.pixelarcade.skywars.SkyWarsShared;
import org.bukkit.entity.Player;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.server.ServerData;
import xyz.refinedev.phoenix.utils.Utilities;

/**
 * @author alfie
 * @project SkyWars
 * @date 20/03/2024 / 22:28
 */

public class ServerUtil {

    public static ServerData getBestLobby() {
        double x = 0;

        ServerData bestLobby = null;
        for (ServerData runningService : SharedAPI.getInstance().getNetworkHandler().getServersInGroup("GAMELOBBY")) {
            if (!runningService.getServerName().contains("skywars"))
                continue;

            double percentage = (runningService.getOnlinePlayers().size() * 100.0 / runningService.getMaxPlayers());

            if (percentage < x) {
                bestLobby = runningService;
                x = percentage;
            }
        }


        return bestLobby;
    }

    public static void sendToLobby(Player player) {
        ServerData server = getBestLobby();
        if (server == null) {
            player.kickPlayer("§cThere are no available servers, please try again later.");
            return;
        }

        Utilities.sendPlayer(player, server.getServerName(), SkyWarsShared.getInstance().getPlugin());
    }

}
