package gg.pixelarcade.skywars.game;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.arena.ArenaSchematic;
import gg.pixelarcade.skywars.utils.cuboid.Cuboid;
import lombok.Getter;
import org.bukkit.World;
import org.bukkit.entity.Player;
import xyz.refinedev.party.party.Party;
import xyz.refinedev.party.pxParty;
import gg.pixelarcade.skywars.arena.Arena;
import gg.pixelarcade.skywars.game.state.GameType;

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

    private final SkyWarsPlugin plugin = SkyWarsPlugin.getInstance();
    private final List<SkyWarsGame> games = new ArrayList<>();

    public SkyWarsGame createGame(GameType type) {
        ArenaSchematic schematic = plugin.getArenaHandler().getRandomArena(type.getMaxPlayers(), type.isRanked());

        if (schematic == null) {
            plugin.getLogger().info("No arena found for game type " + type.name());
            return null;
        }

        World world = SkyWarsPlugin.getInstance().getArenaHandler().getWorldManager().createArenaWorld(schematic);
        if (world == null) {
            SkyWarsPlugin.getInstance().getLogger().warning("Failed to start game: Failed to create world for schematic " + schematic.getName());
            return null;
        }

        Cuboid bounds = schematic.pasteArena(world);
        if (bounds == null) {
            SkyWarsPlugin.getInstance().getLogger().warning("Failed to start game: Failed to paste schematic " + schematic.getName());
            return null;
        }

        SkyWarsGame game = new SkyWarsGame(new Arena(schematic, world, bounds), type);
        games.add(game);

        return game;
    }

    public SkyWarsGame getAvailableGame(Player player, GameType gameType) {
        Party party = pxParty.getInstance().getPartyHandler().getByPlayer(player.getUniqueId());

        for (SkyWarsGame game : games) {
            if (game.getGameType() != gameType)
                continue;

            if (party != null) {
                if (party.getMembers().size() >= game.getPlayers().size())
                    continue;
            }

            if (!game.hasStarted() && game.getArena().getArenaSchematic().isEnabled())
                return game;
        }

        return createGame(gameType);
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

    public boolean isPlayingGame(Player player) {
        return isPlayingGame(player.getUniqueId());
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

    public int getPlayerCount() {
        int count = 0;

        for (SkyWarsGame game : games)
            count += game.getPlayers().size();

        return count;
    }
}
