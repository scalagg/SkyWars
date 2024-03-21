package net.mccade.skywars.handlers.server.packet;

import lombok.AllArgsConstructor;
import net.mccade.skywars.Limbo;
import xyz.refinedev.phoenix.utils.Packet;

@AllArgsConstructor
public class ServerUpdatePacket implements Packet {

    private final Server server;

    @Override
    public void onReceive() {
        Limbo.getLobbyInstance().getServerHandler().getServers().put(server.getId(), server);
    }
}
