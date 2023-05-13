package xyz.refinedev.skywars.game;

import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.refinedev.skywars.arena.Arena;
import xyz.refinedev.skywars.game.state.GameState;
import xyz.refinedev.skywars.game.state.GameType;
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

    private final GameType gameType;
    private final Arena arena;
    private final List<Player> originalPlayers = new ArrayList<>();
    private final List<Player> alivePlayers = new ArrayList<>();
    private GameState state = GameState.WAITING;

    public void start() {
        this.state = GameState.STARTING;

        for (int i = 0; i < alivePlayers.size(); i++) {
            Player player = alivePlayers.get(i);
            Location spawn = arena.getSpawns().get(i);

            player.teleport(spawn);
        }
    }

    public void addPlayer(Player player) {
        if (state != GameState.WAITING) {
            player.sendMessage("game has started");
            return;
        }

        if (gameType.getPlayerLimit() >= alivePlayers.size()) {
            player.sendMessage("gaqme is full");
            return;
        }

        originalPlayers.add(player);
        alivePlayers.add(player);
    }

}
