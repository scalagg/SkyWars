package gg.pixelarcade.skywars.server.packet;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.server.Server;
import lombok.AllArgsConstructor;
import xyz.refinedev.phoenix.utils.Packet;

/**
 * @author alfie
 * @project Skywars
 * @date 27/10/2023 / 01:18
 */

@AllArgsConstructor
public class ServerUpdatePacket implements Packet {

    private final Server server;

    @Override
    public void onReceive() {
        SkyWarsShared.getInstance().getServerHandler().getServers().put(server.getId(), server);
    }
}
