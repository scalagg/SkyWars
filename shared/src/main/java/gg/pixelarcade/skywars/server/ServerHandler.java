package gg.pixelarcade.skywars.server;


import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.server.thread.ServerCheckerThread;
import lombok.Getter;
import org.bukkit.entity.Player;
import studio.pinkcloud.deploy.spigot.api.Deployer;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.server.ServerData;
import xyz.refinedev.phoenix.utils.Utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author alfie
 * @project Skywars
 * @date 27/10/2023 / 00:12
 */

@Getter
public class ServerHandler {

    private final Map<String, Server> servers = new HashMap<>();

    private final ServerCheckerThread checkerThread;

    private boolean deployerSupported;

    public ServerHandler() {
        try {
            Class.forName("studio.pinkcloud.deploy.spigot.api.Deployer");

            deployerSupported = true;
        } catch (ClassNotFoundException e) {
            System.out.println("DeployerSpigot not found, disabling server handler");

            deployerSupported = false;
        }

        checkerThread = new ServerCheckerThread(this);
    }

    public void createServer(String templateName, BiConsumer<Integer, String> consumer) {
        if (!deployerSupported)
            return;
        Deployer.deploy(templateName, SharedAPI.getInstance().getNetworkHandler().getRegion(), (address, port) -> consumer.accept(port, address));
    }

    public ServerData getBestLobby() {
        if (!deployerSupported)
            return null;
        double x = 0;

        ServerData bestLobby = null;
        for (ServerData runningService : SharedAPI.getInstance().getNetworkHandler().getServersInGroup("skywars-lobby")) {
            double percentage = (runningService.getOnlinePlayers().size() * 100.0 / runningService.getMaxPlayers());

            if (percentage < x) {
                bestLobby = runningService;
                x = percentage;
            }
        }


        return bestLobby;
    }

    public ServerData getService(int port) {
        if (!deployerSupported)
            return null;

        for (ServerData server : SharedAPI.getInstance().getNetworkHandler().getServers()) {
            if(server.getPort() == port) {
                return server;
            }
        }

        return null;
    }


    public void sendToLobby(Player player) {
        ServerData server = this.getBestLobby();
        if (server == null) {
            player.kickPlayer("§cThere are no available servers, please try again later.");
            return;
        }

        Utilities.sendPlayer(player, server.getServerName(), SkyWarsShared.getInstance().getPlugin());
    }

    public void stopThreads() {
        checkerThread.interrupt();
    }
}