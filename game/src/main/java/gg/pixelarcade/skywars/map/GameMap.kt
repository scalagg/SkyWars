package gg.pixelarcade.skywars.map

import com.google.common.base.Preconditions
import gg.pixelarcade.skywars.SkyWarsPlugin
import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.util.AngleUtils.faceToYaw
import gg.pixelarcade.skywars.util.Callback
import gg.pixelarcade.skywars.util.cuboid.Cuboid


import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.SkullType
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.Skull
import org.bukkit.metadata.FixedMetadataValue
import java.util.concurrent.ThreadLocalRandom

class GameMap(val name: String, val bounds: Cuboid, val type: GameType) {
    @Transient
    val spawns: MutableList<Location> = ArrayList()

    @Transient
    val usedSpawns: MutableList<Location> = ArrayList()

    @Transient
    val zombieSpawns: MutableList<Location> = ArrayList()

    @Transient
    val chests: MutableMap<Location, String> = HashMap()

    @Transient
    var spectatorSpawnLoc: Location? = null

    @Transient
    var lobbySpawnLoc: Location? = null

    @Transient
    var suddenDeathSpawnLoc: Location? = null

    init {
        scanLocations()
    }

    fun getLobbySpawn(): Location {
        if (lobbySpawnLoc != null) {
            return lobbySpawnLoc as Location
        }

        return spawns[0]
    }

    fun getSpectatorSpawn(): Location {
        if (spectatorSpawnLoc != null) {
            return spectatorSpawnLoc as Location
        }

        println("Spectator spawn is null")
        return spawns[0]
    }

    val randomSpawn: Location?
        get() {
            val availableSpawns: MutableList<Location> =
                ArrayList(spawns)
            availableSpawns.removeAll(usedSpawns)

            if (availableSpawns.isNotEmpty()) {
                val index = ThreadLocalRandom.current().nextInt(availableSpawns.size)
                val spawn = availableSpawns[index]

                usedSpawns.add(spawn)
                return spawn
            }

            return null
        }

    fun removeUsedSpawn(location: Location) {
        usedSpawns.remove(location)
    }

    private fun scanLocations() {
        println("Scanning Locations Bounds: (${bounds.lowerCorner} -> ${bounds.upperCorner})")
        forEachBlock { block: Block ->
            val type = block.type


            if (type == Material.BEACON) {
                suddenDeathSpawnLoc = block.location.clone().add(0.5, 0.0, 0.5)

                // remove the block after
                block.type = Material.AIR
            }

            if (type == Material.BEDROCK) {
                zombieSpawns.add(block.location.clone().add(0.5, 1.0, 0.5))

                // remove the block after
                block.type = Material.AIR
            }

            if (type != Material.SKULL) {
                return@forEachBlock
            }

            val skull = block.state as Skull
            val below = block.getRelative(BlockFace.DOWN)

            val skullLocation = block.location.clone().add(0.5, 1.5, 0.5)
            skullLocation.yaw = (faceToYaw(skull.rotation) + 90).toFloat()

            when (skull.skullType) {
                SkullType.ZOMBIE -> {
                    lobbySpawnLoc = skullLocation

                    block.type = Material.AIR

                    if (below.type == Material.FENCE) {
                        below.type = Material.AIR
                    }
                }

                SkullType.SKELETON -> {
                    spectatorSpawnLoc = skullLocation

                    block.type = Material.AIR

                    if (below.type == Material.FENCE) {
                        below.type = Material.AIR
                    }
                }

                SkullType.PLAYER -> {
                    spawns.add(skullLocation)

                    block.type = Material.AIR

                    if (below.type == Material.FENCE) {
                        below.type = Material.AIR
                    }
                }

                SkullType.CREEPER -> {
                    chests[block.location.clone().add(0.5, -1.0, 0.5)] = "SPAWN"

                    block.type = Material.AIR
                    block.setMetadata("placedByGame", FixedMetadataValue(SkyWarsPlugin.instance, true))

                    if (below.type == Material.FENCE) {
                        below.type = Material.CHEST
                    }
                }

                SkullType.WITHER -> {
                    chests[block.location.clone().add(0.5, -1.0, 0.5)] = "MIDDLE"

                    block.type = Material.AIR
                    block.setMetadata("placedByGame", FixedMetadataValue(SkyWarsPlugin.instance, true))

                    if (below.type == Material.FENCE) {
                        below.type = Material.CHEST
                    }
                }

                else -> {}
            }
        }

       Preconditions.checkNotNull<List<Location>>(spawns, "Spawns (player head) cannot be null.")
       Preconditions.checkArgument(spawns.isNotEmpty(), "Spawns (player head) cannot be empty.")
    }

    private fun forEachBlock(callback: Callback<Block>) {
        val start = bounds.lowerCorner
        val end = bounds.upperCorner
        val world = bounds.world

        for (x in start.blockX until end.blockX) {
            for (y in start.blockY until end.blockY) {
                for (z in start.blockZ until end.blockZ) {
                    callback.callback(world.getBlockAt(x, y, z))
                }
            }
        }
    }
}