package gg.pixelarcade.skywars.editor.arena

import gg.pixelarcade.skywars.SkywarsDevToolsPlugin
import gg.pixelarcade.skywars.arena.model.SkyWarsArena
import gg.pixelarcade.skywars.map.MapManageServices
import gg.pixelarcade.skywars.map.MapManageServices.loader
import gg.pixelarcade.skywars.map.MapManageServices.slimePlugin
import gg.pixelarcade.skywars.util.Callback
import gg.pixelarcade.skywars.util.cuboid.Cuboid
import gg.pixelarcade.skywars.utils.SafeLocation
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.AngleUtils.faceToYaw
import org.bukkit.*
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.Skull
import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue

/**
 * @date 11/19/2024
 * @author Moose1301
 */
object ArenaEditorSystem {


    fun startEditing(player: Player, arena: SkyWarsArena) {
        val slimeWorld = slimePlugin
            .loadWorld(
                loader,
                arena.id,
                false,
                MapManageServices.propertyMap
            )

        slimePlugin.generateWorld(slimeWorld)

        val newWorld = Bukkit.getWorld(arena.id)
            ?: return run {
                player.sendMessage("${CC.RED}Failed...")
                player.playSound(player.location, Sound.NOTE_PLING, 1.0f, 0.2f)
            }

        var spawn = Location(newWorld, 0.0, 96.0, 0.0, -35.0f, 50.0f)

        if (arena.lobbySpawnLoc != null)
            spawn = arena.lobbySpawnLoc!!.toBukkit(newWorld)

        player.gameMode = org.bukkit.GameMode.CREATIVE
        player.allowFlight = true
        player.isFlying = true
        player.teleport(spawn)

        player.sendMessage("${CC.GREEN}Visiting ${CC.WHITE}${arena.id}${CC.GREEN}...")
        player.playSound(player.location, Sound.NOTE_PLING, 1.0f, 1.0f)

        sendRequiredSetup(player, arena)
        player.setMetadata("ARENA-EDIT", FixedMetadataValue(SkywarsDevToolsPlugin.instance, arena.id))
    }


    fun scanPlayer(player: Player, arena: SkyWarsArena) {
        if(arena.boundsLower == null || arena.boundsUpper == null) {
            player.sendMessage(CC.RED + "Bounds are not set correctly")
            return
        }

        arena.zombieSpawns.clear()
        arena.spawns.clear()
        arena.chests.clear()

        forEachBlock(Cuboid(
            arena.boundsLower!!.toBukkit(player.world),
            arena.boundsUpper!!.toBukkit(player.world),
        )) { block: Block ->
            val type = block.type


            if (type == Material.BEACON) {
                arena.suddenDeathSpawnLoc = SafeLocation(block.location.clone().add(0.5, 0.0, 0.5))

                // remove the block after
                block.type = Material.AIR
            }

            if (type == Material.BEDROCK) {
                arena.zombieSpawns.add(SafeLocation(block.location.clone().add(0.5, 1.0, 0.5)))

                // remove the block after
                block.type = Material.AIR
            }
            if (type == Material.EYE_OF_ENDER) {
                arena.tntGeneratorSpawnLoc = SafeLocation(block.location.clone().add(0.5, 0.0, 0.5))

                // remove the block after
                block.type = Material.GOLD_BLOCK
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
                    arena.lobbySpawnLoc = SafeLocation(skullLocation)

                    block.type = Material.AIR

                    if (below.type == Material.FENCE) {
                        below.type = Material.AIR
                    }
                }

                SkullType.SKELETON -> {
                    arena.spectatorSpawnLoc = SafeLocation(skullLocation)

                    block.type = Material.AIR

                    if (below.type == Material.FENCE) {
                        below.type = Material.AIR
                    }
                }

                SkullType.PLAYER -> {
                    arena.spawns.add(SafeLocation(skullLocation))

                    block.type = Material.AIR

                    if (below.type == Material.FENCE) {
                        below.type = Material.AIR
                    }
                }

                SkullType.CREEPER -> {
                    arena.chests[SafeLocation(block.location.clone().add(0.5, -1.0, 0.5))] = "SPAWN"

                    block.type = Material.AIR
                    //block.setMetadata("placedByGame", FixedMetadataValue(SkyWarsPlugin.instance, true))

                    if (below.type == Material.FENCE) {
                        below.type = Material.CHEST
                    }
                }

                SkullType.WITHER -> {
                    arena.chests[SafeLocation(block.location.clone().add(0.5, -1.0, 0.5))] = "MIDDLE"

                    block.type = Material.AIR
                    //block.setMetadata("placedByGame", FixedMetadataValue(SkyWarsPlugin.instance, true))

                    if (below.type == Material.FENCE) {
                        below.type = Material.CHEST
                    }
                }

                else -> {}
            }
        }
    }

    private fun forEachBlock(bounds: Cuboid, callback: Callback<Block>) {
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

    fun sendRequiredSetup(player: Player, arena: SkyWarsArena) {
        if (arena.boundsLower == null)
            player.sendMessage("${CC.RED}Bounds lower not set")

        if (arena.boundsUpper == null)
            player.sendMessage("${CC.RED}Bounds lower not set")

        if (arena.spawns.isEmpty())
            player.sendMessage("${CC.RED}No player spawns set")

        if (arena.chests.isEmpty())
            player.sendMessage("${CC.RED}No chests spawns set")

        if (arena.spectatorSpawnLoc == null)
            player.sendMessage("${CC.RED}No spectator spawn set")

        if (arena.lobbySpawnLoc == null)
            player.sendMessage("${CC.RED}No lobby spawn set")

        if (arena.suddenDeathSpawnLoc == null)
            player.sendMessage("${CC.RED}No sudden death spawn set")

        if (arena.tntGeneratorSpawnLoc == null)
            player.sendMessage("${CC.RED}No tnt generator spawn set")


    }
}