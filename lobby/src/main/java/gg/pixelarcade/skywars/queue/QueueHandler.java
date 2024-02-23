package gg.pixelarcade.skywars.queue;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.game.Game;
import gg.pixelarcade.skywars.game.state.GameState;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.server.Server;
import lombok.Getter;
import xyz.refinedev.phoenix.utils.Utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alfie
 * @project SkyWars
 * @date 30/10/2023 / 22:20
 */

@Getter
public class QueueHandler {

    private final Map<GameType, Queue> queues = new HashMap<>();

    public boolean shouldCreateServer() {
        boolean shouldCreate = true;

        for (Server server : SkyWarsShared.getInstance().getServerHandler().getServers().values()) {
            if (server.getGameState() != GameState.WAITING && server.getGameState() != GameState.COUNTDOWN)
                continue;

            if (server.getOnlinePlayers() >= server.getGameType().getMaxPlayers())
                continue;

            shouldCreate = false;
        }

        return shouldCreate;
    }

}
