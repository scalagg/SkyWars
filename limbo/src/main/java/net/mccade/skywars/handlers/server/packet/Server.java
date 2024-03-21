package net.mccade.skywars.handlers.server.packet;

import gg.pixelarcade.skywars.game.state.GameTeamType;
import gg.pixelarcade.skywars.game.state.GameType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Server {

    private final String id = UUID.randomUUID().toString().substring(0, 7);
    private String mapName;

    private GameType gameType;
    private GameTeamType gameTeamType;

    private int port;
    private int currentPlayers;
    private int maxPlayers;

    private long firstUpdate;
    private long lastUpdate;
}
