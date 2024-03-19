package gg.pixelarcade.skywars.map;

import com.google.common.base.Preconditions;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.util.cuboid.Cuboid;
import gg.pixelarcade.skywars.util.AngleUtils;
import gg.pixelarcade.skywars.util.Callback;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.metadata.FixedMetadataValue;
import xyz.refinedev.phoenix.utils.Exclude;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public final class GameMap {

    private final String name;
    private String displayName;

    private final GameType type;
    private final Cuboid bounds;

    @Exclude private final List<Location> spawns = new ArrayList<>();
    @Exclude private final List<Location> usedSpawns = new ArrayList<>();
    @Exclude private final List<Location> zombieSpawns = new ArrayList<>();

    @Exclude private final Map<Location, String> chests = new HashMap<>();

    @Exclude private Location spectatorSpawn;
    @Exclude private Location lobbySpawn;
    @Exclude private Location suddenDeathSpawn;

    public GameMap(String name, Cuboid bounds, GameType type) {
        this.name = name;
        this.bounds = bounds;
        this.type = type;

        scanLocations();
    }

    public Location getLobbySpawn() {
        if (lobbySpawn != null) {
            return lobbySpawn;
        }

        return spawns.get(0);
    }

    public Location getSpectatorSpawn() {
        if (spectatorSpawn != null) {
            return spectatorSpawn;
        }

        System.out.println("Spectator spawn is null");
        return spawns.get(0);
    }

    public Location getRandomSpawn() {
        List<Location> availableSpawns = new ArrayList<>(spawns);
        availableSpawns.removeAll(usedSpawns);

        if (!availableSpawns.isEmpty()) {
            int index = ThreadLocalRandom.current().nextInt(availableSpawns.size());
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
        forEachBlock(block -> {
            Material type = block.getType();

            if (type == Material.BEACON) {
                suddenDeathSpawn = block.getLocation().clone().add(0.5, 0, 0.5);

                // remove the block after
                block.setType(Material.AIR);
            }

            if (type == Material.BEDROCK) {
                zombieSpawns.add(block.getLocation().clone().add(0.5, 1, 0.5));

                // remove the block after
                block.setType(Material.AIR);
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
                    System.out.println("Found spectator spawn");
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
                    chests.put(block.getLocation().clone().add(0.5, -1, 0.5), "SPAWN");

                    block.setType(Material.AIR);
                    block.setMetadata("placedByGame", new FixedMetadataValue(SkyWarsPlugin.getInstance(), true));

                    if (below.getType() == Material.FENCE) {
                        below.setType(Material.CHEST);
                    }

                    break;
                case WITHER:
                    chests.put(block.getLocation().clone().add(0.5, -1, 0.5), "MIDDLE");

                    block.setType(Material.AIR);
                    block.setMetadata("placedByGame", new FixedMetadataValue(SkyWarsPlugin.getInstance(), true));

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

}