package net.mccade.skywars.handlers.server.packet;

import lombok.Getter;
import net.mccade.skywars.LimboPlugin;
import net.mccade.skywars.handlers.server.packet.thread.ServerCheckerThread;
import org.bukkit.entity.Player;
import studio.pinkcloud.deploy.spigot.api.Deployer;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.server.ServerData;
import xyz.refinedev.phoenix.utils.Utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

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

    public void createServer(String templateName, Consumer<String> consumer) {
        if (!deployerSupported)
            return;

        Deployer.deploy(templateName,
                SharedAPI.getInstance().getNetworkHandler().getRegion(),
                consumer);
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

        Utilities.sendPlayer(player, server.getServerName(), LimboPlugin.getInstance());
    }

    public void stopThreads() {
        checkerThread.interrupt();
    }


}

