package xyz.refinedev.skywars.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import xyz.refinedev.phoenix.utils.general.Tasks;
import xyz.refinedev.skywars.arena.Arena;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.utils.cuboid.Cuboid;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
        ArenaSchematic schematic = plugin.getArenaHandler().getRandomArena(gameType.getPlayerLimit(), gameType.isRanked());

        if (schematic == null) {
            plugin.getLogger().log(Level.SEVERE, "No arena found for game type " + gameType.name());
            return null;
        }

        World world = pxSkyWars.getInstance().getArenaHandler().getWorldManager().createArenaWorld(schematic);
        if (world == null) {
            pxSkyWars.getInstance().getLogger().warning("Failed to start game: Failed to create world for schematic " + schematic.getName());
            return null;
        }

        Cuboid bounds = schematic.pasteArena(world);
        if (bounds == null) {
            pxSkyWars.getInstance().getLogger().warning("Failed to start game: Failed to paste schematic " + schematic.getName());
            return null;
        }

        Arena arena = new Arena(schematic, world, bounds);
        SkyWarsGame game = new SkyWarsGame(plugin, gameType, null);

        int timeout = 10 * 20;
        AtomicInteger counter = new AtomicInteger();
        Tasks.runTimer(plugin, () -> {

            if (game.getArena() != null)
                return;

            if (!bounds.isEmpty()) {
                game.setArena(arena);
                game.waiting();
                return;
            }

            if (counter.incrementAndGet() > timeout) {
                plugin.getArenaHandler().getWorldManager().deleteWorld(world);
                plugin.getLogger().warning("Failed to start game: timed out while waiting for schematic " + schematic.getName());
            }
        }, 0L, 2L);

        games.add(game);
        return game;
    }

    public SkyWarsGame getAvailableGame(GameType gameType) {
        for (SkyWarsGame game : games) {
            if (!game.hasStarted() || game.getArena().getArenaSchematic().isEnabled()) {
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
