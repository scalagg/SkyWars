package xyz.refinedev.skywars.arena;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import xyz.refinedev.skywars.handler.ArenaHandler;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.utils.AngleUtils;
import xyz.refinedev.skywars.utils.Callback;
import xyz.refinedev.skywars.utils.cuboid.Cuboid;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents a pasted instance of an {@link ArenaSchematic}.
 * See {@link xyz.refinedev.skywars.arena} for a comparision of
 * {@link Arena}s and {@link ArenaSchematic}s.
 */
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

    /**
     * Bounding box for this arena
     */
    private Cuboid bounds;

    /**
     * Team 1 spawn location for this arena
     * For purposes of arena definition we ignore
     * non-two-teamed matches.
     */
    private Location spawn1;

    /**
     * Team 2 spawn location for this arena
     * For purposes of arena definition we ignore
     * non-two-teamed matches.
     */
    private Location spawn2;

    /**
     * Spectator spawn location for this arena
     */
    private Location spectatorSpawn;

    /**
     * If this arena is currently being used
     *
     * @see ArenaHandler#allocateUnusedArena(Predicate)
     * @see ArenaHandler#releaseArena(Arena)
     */
    // AccessLevel.NONE so arenas can only mark as in use
    // or not in use by the appropriate methods in ArenaHandler
    @Getter
    @Setter
    private transient boolean inUse;

    public Arena() {
    } // for gson

    public Arena(String schematic, int copy, Cuboid bounds) {
        this.schematic = Preconditions.checkNotNull(schematic);
        this.copy = copy;
        this.bounds = Preconditions.checkNotNull(bounds);

        scanLocations();
    }

    public Location getSpectatorSpawn() {
        // if it's been defined in the actual map file or calculated before
        if (spectatorSpawn != null) {
            return spectatorSpawn;
        }

        int xDiff = Math.abs(spawn1.getBlockX() - spawn2.getBlockX());
        int yDiff = Math.abs(spawn1.getBlockY() - spawn2.getBlockY());
        int zDiff = Math.abs(spawn1.getBlockZ() - spawn2.getBlockZ());

        int newX = Math.min(spawn1.getBlockX(), spawn2.getBlockX()) + (xDiff / 2);
        int newY = Math.min(spawn1.getBlockY(), spawn2.getBlockY()) + (yDiff / 2);
        int newZ = Math.min(spawn1.getBlockZ(), spawn2.getBlockZ()) + (zDiff / 2);

        ArenaHandler arenaHandler = pxSkyWars.getInstance().getArenaHandler();
        spectatorSpawn = new Location(arenaHandler.getArenaWorld(), newX, newY, newZ);

        while (spectatorSpawn.getBlock().getType().isSolid()) {
            spectatorSpawn = spectatorSpawn.add(0, 1, 0);
        }

        return spectatorSpawn;
    }

    private void scanLocations() {
        // iterating the cuboid doesn't work because
        // its iterator is broken :(
        forEachBlock(block -> {
            Material type = block.getType();

            if (type != Material.SKULL) {
                return;
            }

            Skull skull = (Skull) block.getState();
            Block below = block.getRelative(BlockFace.DOWN);

            Location skullLocation = block.getLocation().clone().add(0.5, 1.5, 0.5);
            skullLocation.setYaw(AngleUtils.faceToYaw(skull.getRotation()) + 90);

            switch (skull.getSkullType()) {
                case SKELETON:
                    spectatorSpawn = skullLocation;

                    block.setType(Material.AIR);

                    if (below.getType() == Material.FENCE) {
                        below.setType(Material.AIR);
                    }

                    break;
                case ZOMBIE:
                    spawn1 = skullLocation;

                    block.setType(Material.AIR);

                    if (below.getType() == Material.FENCE) {
                        below.setType(Material.AIR);
                    }
                    break;
                case CREEPER:
                    spawn2 = skullLocation;

                    block.setType(Material.AIR);

                    if (below.getType() == Material.FENCE) {
                        below.setType(Material.AIR);
                    }

                    break;
                default:
                    break;
            }
        });

        Preconditions.checkNotNull(spawn1, "Team 1 spawn (zombie head) cannot be null.");
        Preconditions.checkNotNull(spawn2, "Team 2 spawn (creeper head) cannot be null.");
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

    @Override
    public int hashCode() {
        return Objects.hash(schematic, copy);
    }

    public Location getSpawn1() {
        return spawn1;
    }

    public Location getSpawn2() {
        return spawn2;
    }

    public Cuboid getBounds() {
        return bounds;
    }
}