package xyz.refinedev.skywars.arena;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.metadata.FixedMetadataValue;
import xyz.refinedev.skywars.game.state.ChestType;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.utils.AngleUtils;
import xyz.refinedev.skywars.utils.Callback;
import xyz.refinedev.skywars.utils.cuboid.Cuboid;

import java.util.*;

/**
 * Represents a pasted instance of an {@link ArenaSchematic}.
 * See {@link xyz.refinedev.skywars.arena} for a comparision of
 * {@link Arena}s and {@link ArenaSchematic}s.
 */

@Getter
public final class Arena {

    /**
     * The name of the {@link ArenaSchematic} this Arena is
     * copied from.
     *
     * @see xyz.refinedev.skywars.arena
     */
    @Getter
    private String schematic;

    /**
     * What number copy this arena is.
     *
     * @see xyz.refinedev.skywars.arena
     */
    @Getter
    private int copy;

    @Getter
    @Setter
    private transient World world;

    /**
     * Bounding box for this arena
     */
    private Cuboid bounds;

    /**
     * For purposes of arena definition
     */
    private final List<Location> spawns = new ArrayList<>();
    private final List<Location> usedSpawns = new ArrayList<>();

    private final Map<Location, ChestType> chests = new HashMap<>();

    /**
     * Spectator spawn location for this arena
     */
    private Location spectatorSpawn;
    private Location lobbySpawn;
    private Location suddenDeathSpawn;

    public Arena() {
    } // for gson

    public Arena(ArenaSchematic schematic, World world, Cuboid bounds) {
        this.schematic = schematic.getName();
        this.world = world;
        this.bounds = bounds;

        scanLocations();
    }

    public Location getLobbySpawn() {
        // if it's been defined in the actual map file or calculated before
        if (lobbySpawn != null) {
            return lobbySpawn;
        }

        return spawns.get(0);
    }

    public Location getSpectatorSpawn() {
        // if it's been defined in the actual map file or calculated before
        if (spectatorSpawn != null) {
            return spectatorSpawn;
        }

        return spawns.get(0);
    }

    public Location getRandomSpawn() {
        List<Location> availableSpawns = new ArrayList<>(spawns);

        availableSpawns.removeAll(usedSpawns);

        if (!availableSpawns.isEmpty()) {
            int index = pxSkyWars.getInstance().getRandom().nextInt(availableSpawns.size());
            Location spawn = availableSpawns.get(index);

            usedSpawns.add(spawn);
            return spawn;
        }

        return null;
    }

    public void removeUsedSpawn(Location location) {
        usedSpawns.remove(location);
    }

    private void scanLocations() {
        // iterating the cuboid doesn't work because
        // its iterator is broken :(
        forEachBlock(block -> {
            Material type = block.getType();

            if (type == Material.BEACON) {
                suddenDeathSpawn = block.getLocation().clone().add(0.5, 0, 0.5);
            }

            if (type != Material.SKULL) {
                return;
            }

            Skull skull = (Skull) block.getState();
            Block below = block.getRelative(BlockFace.DOWN);

            Location skullLocation = block.getLocation().clone().add(0.5, 1.5, 0.5);
            skullLocation.setYaw(AngleUtils.faceToYaw(skull.getRotation()) + 90);

            switch (skull.getSkullType()) {
                case ZOMBIE:
                    lobbySpawn = skullLocation;

                    block.setType(Material.AIR);

                    if (below.getType() == Material.FENCE) {
                        below.setType(Material.AIR);
                    }

                    break;
                case SKELETON:
                    spectatorSpawn = skullLocation;

                    block.setType(Material.AIR);

                    if (below.getType() == Material.FENCE) {
                        below.setType(Material.AIR);
                    }

                    break;
                case PLAYER:
                    spawns.add(skullLocation);

                    block.setType(Material.AIR);

                    if (below.getType() == Material.FENCE) {
                        below.setType(Material.AIR);
                    }

                    break;
                case CREEPER:
                    chests.put(block.getLocation().clone().add(0.5, -1, 0.5), ChestType.SPAWN);

                    block.setType(Material.AIR);
                    block.setMetadata("placedByGame", new FixedMetadataValue(pxSkyWars.getInstance(), true));

                    if (below.getType() == Material.FENCE) {
                        below.setType(Material.CHEST);
                    }

                    break;
                case WITHER:
                    chests.put(block.getLocation().clone().add(0.5, -1, 0.5), ChestType.MIDDLE);

                    block.setType(Material.AIR);
                    block.setMetadata("placedByGame", new FixedMetadataValue(pxSkyWars.getInstance(), true));

                    if (below.getType() == Material.FENCE) {
                        below.setType(Material.CHEST);
                    }

                    break;
                default:
                    break;
            }
        });

        Preconditions.checkNotNull(spawns, "Spawns (player head) cannot be null.");
        Preconditions.checkArgument(!spawns.isEmpty(), "Spawns (player head) cannot be empty.");
    }

    private void forEachBlock(Callback<Block> callback) {
        Location start = bounds.getLowerCorner();
        Location end = bounds.getUpperCorner();
        World world = bounds.getWorld();

        for (int x = start.getBlockX(); x < end.getBlockX(); x++) {
            for (int y = start.getBlockY(); y < end.getBlockY(); y++) {
                for (int z = start.getBlockZ(); z < end.getBlockZ(); z++) {
                    callback.callback(world.getBlockAt(x, y, z));
                }
            }
        }
    }

    public void forEachChunk(Callback<Chunk> callback) {
        int lowerX = bounds.getLowerX() >> 4;
        int lowerZ = bounds.getLowerZ() >> 4;
        int upperX = bounds.getUpperX() >> 4;
        int upperZ = bounds.getUpperZ() >> 4;

        World world = bounds.getWorld();

        for (int x = lowerX; x <= upperX; x++) {
            for (int z = lowerZ; z <= upperZ; z++) {
                callback.callback(world.getChunkAt(x, z));
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Arena) {
            Arena a = (Arena) o;
            return a.schematic.equals(schematic) && a.copy == copy;
        } else {
            return false;
        }
    }

    public ArenaSchematic getArenaSchematic() {
        return pxSkyWars.getInstance().getArenaHandler().getSchematic(schematic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schematic, copy);
    }
}