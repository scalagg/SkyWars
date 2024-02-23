package gg.pixelarcade.skywars.queue.thread;

import gg.pixelarcade.skywars.SkyWarsLobby;
import gg.pixelarcade.skywars.game.state.GameState;
import gg.pixelarcade.skywars.queue.QueueHandler;
import gg.pixelarcade.skywars.server.Server;
import gg.pixelarcade.skywars.server.packet.ServerUpdatePacket;
import xyz.refinedev.phoenix.packet.api.PacketHandler;

/**
 * @author alfie
 * @project SkyWars
 * @date 30/10/2023 / 22:29
 */

public class QueueTickThread extends Thread {

    public QueueTickThread() {
        setName("SkyWars | Queue Tick Thread");
        setDaemon(true);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            tick();

            try {
                Thread.sleep(500L);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void tick() {
        QueueHandler handler = SkyWarsLobby.getLobbyInstance().getQueueHandler();

        if (handler.shouldCreateServer()) {
            SkyWarsLobby.getLobbyInstance().getServerHandler().createServer("skywars-game", (port, address) -> {
                if (SkyWarsLobby.getLobbyInstance().getServerHandler().getService(port) != null)
                    return;

                Server server = new Server();
                server.setPort(port);
                server.setFirstUpdate(System.currentTimeMillis());
                server.setLastUpdate(System.currentTimeMillis());

                PacketHandler.send(new ServerUpdatePacket(server));
            });
        }

        String sendTo = null;
        for (Server server : SkyWarsLobby.getLobbyInstance().getServerHandler().getServers().values()) {
            if (server.getGameState() != GameState.WAITING
                    && server.getGameState() != GameState.COUNTDOWN)
                continue;

            if (server.getOnlinePlayers() >= server.getMaxPlayers())
                continue;

            sendTo = server.getId();
            break;
        }

        if (sendTo == null)
            return;


    }

}
