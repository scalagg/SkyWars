package xyz.refinedev.skywars.handler;

import lombok.Data;
import lombok.Getter;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.refinedev.skywars.arena.Arena;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.utils.cuboid.Cuboid;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.handler
 * @date 26/09/2023 / 21:16
 */

@Getter
public class GameHandler {

    private final pxSkyWars plugin = pxSkyWars.getInstance();
    private final List<SkyWarsGame> games = new ArrayList<>();

    public SkyWarsGame createGame(GameType type) {
        ArenaSchematic schematic = plugin.getArenaHandler().getRandomArena(type.getMaxPlayers(), type.isRanked());

        if (schematic == null) {
            plugin.getLogger().info("No arena found for game type " + type.name());
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
        SkyWarsGame game = new SkyWarsGame(null, type);
        games.add(game);

        int timeout = 10 * 20;
        new BukkitRunnable() {
            private int time = 0;

            @Override
            public void run() {
                if (game.getArena() != null) {
                    cancel();
                    return;
                }

                if (!bounds.isEmpty()) {
                    game.setArena(arena);
                    cancel();
                    return;
                }

                if (++time > timeout) {
                    game.endGame();
                    plugin.getArenaHandler().getWorldManager().deleteWorld(world);
                    plugin.getLogger().info("Failed to start game: Failed to paste schematic " + schematic.getName() + " (TIMEOUT_REACHED)");
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 2L);

        return game;
    }

    public SkyWarsGame getAvailableGame(GameType type) {
        for (SkyWarsGame game : games) {
            if (!game.hasStarted() || game.getArena().getArenaSchematic().isEnabled())
                return game;
        }

        return createGame(type);
    }

    public void removeGame(SkyWarsGame game) {
        games.remove(game);
    }

    public boolean isPlayingGame(UUID id) {
        for (SkyWarsGame game : games) {
            if (game.isInGame(id)) {
                return true;
            }
        }

        return false;
    }

    public boolean isSpectating(UUID uuid) {
        for (SkyWarsGame game : games) {
            if (game.isSpectating(uuid)) {
                return true;
            }
        }

        return false;
    }

    public boolean isSpectating(Player player) {
        return isSpectating(player.getUniqueId());
    }

    public boolean isPlayingGame(Player player) {
        return isPlayingGame(player.getUniqueId());
    }

    public SkyWarsGame getGame(UUID id) {
        for (SkyWarsGame game : games) {
            if (game.getId().equals(id)) {
                return game;
            }
        }

        return null;
    }

    public SkyWarsGame getGame(Player player) {
        for (SkyWarsGame game : games) {
            for (UUID uuid : game.getPlayers()) {
                if (uuid.equals(player.getUniqueId()))
                    return game;
            }
        }

        return null;
    }

    public SkyWarsGame getGame(Arena arena) {
        for (SkyWarsGame game : games) {
            if (game.getArena().equals(arena)) {
                return game;
            }
        }

        return null;
    }

}
