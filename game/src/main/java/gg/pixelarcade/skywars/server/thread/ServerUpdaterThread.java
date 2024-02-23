package gg.pixelarcade.skywars.server.thread;

import gg.pixelarcade.skywars.SkyWarsGame;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.server.Server;
import gg.pixelarcade.skywars.server.packet.ServerUpdatePacket;
import xyz.refinedev.phoenix.packet.api.PacketHandler;

/**
 * @author alfie
 * @project Skywars
 * @date 27/10/2023 / 01:22
 */

public class ServerUpdaterThread extends Thread {

    public ServerUpdaterThread() {
        super("SkyWars | Server Updater Thread");
        setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            updater();

            try {
                Thread.sleep(500L);
            } catch (InterruptedException ignored) {}
        }
    }

    private void updater() {
        SkyWarsGame plugin = SkyWarsGame.getGameInstance();
        Server server = plugin.getGameServer();

        server.setLastUpdate(System.currentTimeMillis());
        server.setGameState(SkyWarsGame.getGameInstance().getGameHandler().getGame().getState());
        server.setOnlinePlayers(SkyWarsPlugin.getInstance().getServer().getOnlinePlayers().size());

        PacketHandler.send(new ServerUpdatePacket(server));
    }
}
