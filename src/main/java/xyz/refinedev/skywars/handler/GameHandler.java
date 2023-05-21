package xyz.refinedev.skywars.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import xyz.refinedev.skywars.arena.Arena;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.pxSkyWars;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

/**
 * @author Creaxx
 * Created At: 5/8/2023
 */

@Getter
@RequiredArgsConstructor
public class GameHandler {
    private final pxSkyWars plugin;
    private final List<SkyWarsGame> games = new ArrayList<>();

    public SkyWarsGame createGame(GameType gameType) {
        Optional<Arena> arena = plugin.getArenaHandler().allocateUnusedArena(arenaSchematic -> gameType == arenaSchematic.getGameType());

        if (!arena.isPresent()) {
            pxSkyWars.getInstance().getLogger().log(Level.SEVERE, "No arenas available to start game!");
            return null;
        }

        SkyWarsGame game = new SkyWarsGame(plugin, gameType, arena.get());
        games.add(game);

        return game;
    }

    public SkyWarsGame getAvailableGame(GameType gameType) {
        for (SkyWarsGame game : games) {
            if (!game.hasStarted()) {
                return game;
            }
        }

        return createGame(gameType);
    }

    public boolean isInGame(Player player) {
        for (SkyWarsGame game : this.games) {
            if (game.isInGame(player)) return true;
        }
        return false;
    }

    public SkyWarsGame getGame(Player player) {
        for (SkyWarsGame game : this.games) {
            if (game.isInGame(player)) return game;
        }

        return null;
    }

    public int getPlaying() {
        int playing = 0;

        for (SkyWarsGame game : games) {
            playing = +game.getAlivePlayers().size();
        }

        return playing;
    }
}
