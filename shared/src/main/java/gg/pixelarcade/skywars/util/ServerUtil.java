package gg.pixelarcade.skywars.util;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.scala.commons.agnostic.sync.ServerSync;
import gg.scala.commons.agnostic.sync.server.AbstractServer;
import gg.scala.commons.agnostic.sync.server.ServerContainer;
import gg.scala.commons.agnostic.sync.server.impl.GameServer;
import net.evilblock.cubed.util.bungee.BungeeUtil;
import org.bukkit.entity.Player;

/**
 * @author alfie
 * @project SkyWars
 * @date 20/03/2024 / 22:28
 */

public class ServerUtil {

    public static GameServer getBestLobby() {
        double x = 0;

        GameServer bestLobby = null;
        for (AbstractServer runningService : ServerContainer.INSTANCE.getServersInGroup("skywars-lobby")) {
            if(!(runningService instanceof GameServer server))
                continue;

            double percentage = (server.getPlayersCount() * 100.0 / server.getMaxPlayers());
            if (percentage <= x) {
                bestLobby = server;
                x = percentage;
            }
        }


        return bestLobby;
    }

    public static void sendToLobby(Player player) {
        AbstractServer server = getBestLobby();
        if (server == null) {
            player.kickPlayer("§cThere are no available servers, please try again later.");
            return;
        }

        BungeeUtil.sendToServer(player, server.getId());
    }

}
