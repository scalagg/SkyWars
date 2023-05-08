package xyz.refinedev.skywars.game;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import xyz.refinedev.skywars.game.state.SkyWarsGameState;
import xyz.refinedev.skywars.pxSkyWars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Creaxx
 * Created At: 5/8/2023
 */

@RequiredArgsConstructor
public class SkyWarsGame {
    private final pxSkyWars plugin;
    private final UUID gameId = UUID.randomUUID();

    private SkyWarsGameState state = SkyWarsGameState.WAITING;

    private final List<Player> originalPlayers = new ArrayList<>();
    private final List<Player> alivePlayers = new ArrayList<>();

    public void start() {
        this.state = SkyWarsGameState.STARTING;

        for (Player player : alivePlayers) {

        }
    }

}
