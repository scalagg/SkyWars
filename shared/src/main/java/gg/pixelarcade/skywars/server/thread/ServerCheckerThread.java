package gg.pixelarcade.skywars.server.thread;

import gg.pixelarcade.skywars.server.Server;
import gg.pixelarcade.skywars.server.ServerHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author alfie
 * @project Skywars
 * @date 27/10/2023 / 01:23
 */

public class ServerCheckerThread extends Thread {

    private final ServerHandler handler;

    public ServerCheckerThread(ServerHandler handler) {
        super("SkyWars | Server Checker Thread");
        this.handler = handler;

        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            checkServers();

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ignored) {}
        }
    }

    private void checkServers() {
        for (Server server : handler.getServers().values()) {
            if (server.getLastUpdate() < TimeUnit.SECONDS.toMillis(10) + System.currentTimeMillis()) {
                handler.getServers().remove(server.getId());
            }
        }
    }
}
