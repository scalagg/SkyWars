package gg.pixelarcade.skywars.server;

import gg.pixelarcade.skywars.game.state.GameState;
import gg.pixelarcade.skywars.game.state.GameType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * @author alfie
 * @project Skywars
 * @date 27/10/2023 / 01:13
 */

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Server {

    private final String id = UUID.randomUUID().toString().substring(0, 7);
    private String mapName;

    private GameType gameType;
    private GameState gameState;

    private int port;
    private int onlinePlayers;
    private int maxPlayers;

    private long firstUpdate;
    private long lastUpdate;

}
