package gg.scala.skywars.editor.arena

import gg.scala.skywars.SkywarsDevToolsPlugin
import gg.scala.skywars.arena.model.SkyWarsArena
import gg.scala.skywars.map.MapManageServices
import gg.scala.skywars.map.MapManageServices.loader
import gg.scala.skywars.map.MapManageServices.slimePlugin
import gg.scala.skywars.util.Callback
import gg.scala.skywars.util.cuboid.Cuboid
import gg.scala.skywars.utils.SafeLocation
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.AngleUtils.faceToYaw
import org.bukkit.*
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.Sign
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
        if (arena.boundsLower == null || arena.boundsUpper == null) {
            player.sendMessage(CC.RED + "Bounds are not set correctly")
            return
        }

        arena.zombieSpawns.clear()
        arena.spawns.clear()
        arena.chests.clear()

        forEachBlock(
            _root_ide_package_.gg.scala.skywars.util.cuboid.Cuboid(
                arena.boundsLower!!.toBukkit(player.world),
                arena.boundsUpper!!.toBukkit(player.world),
            )
        ) { block: Block ->
            val type = block.type
            if (type != Material.WALL_SIGN && type != Material.SIGN)
                return@forEachBlock
            val sign = block.state as Sign;
            if (sign.getLine(0).equals("[SuddenDeathSpawn]")) {
                arena.suddenDeathSpawnLoc = SafeLocation(block.location.clone().add(0.5, 0.0, 0.5))

                // remove the block after
                block.type = Material.AIR
            } else if (sign.getLine(0).equals("[ZombieSpawn]")) {
                arena.zombieSpawns.add(SafeLocation(block.location.clone().add(0.5, 1.0, 0.5)))

                // remove the block after
                block.type = Material.AIR
            } else if (sign.getLine(0).equals("[TntGeneratorSpawn]")) {
                arena.tntGeneratorSpawnLoc = SafeLocation(block.location.clone().add(0.5, 0.0, 0.5))

                // remove the block after
                block.type = Material.GOLD_BLOCK
            } else if (sign.getLine(0).equals("[LobbySpawn]")) {
                arena.lobbySpawnLoc = SafeLocation(block.location.clone().add(0.5, 0.5, 0.5))

                block.type = Material.AIR
            } else if (sign.getLine(0).equals("[SpectatorSpawn]")) {
                arena.spectatorSpawnLoc = SafeLocation(block.location.clone().add(0.5, 0.5, 0.5))

                block.type = Material.AIR
            } else if (sign.getLine(0).equals("[Spawn]")) {
                arena.spawns.add(SafeLocation(block.location.clone().add(0.5, 0.5, 0.5)))

                block.type = Material.AIR
            }else if (sign.getLine(0).equals("[Chest]")) {
                arena.chests[SafeLocation(block.location.clone().add(0.5, 0.0, 0.5))] = sign.getLine(1);

                block.type = Material.AIR
            }
        }
    }

    private fun forEachBlock(bounds: _root_ide_package_.gg.scala.skywars.util.cuboid.Cuboid, callback: Callback<Block>) {
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