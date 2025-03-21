package gg.scala.skywars.util

import com.boydti.fawe.util.EditSessionBuilder
import com.sk89q.worldedit.CuboidClipboard
import com.sk89q.worldedit.EditSession
import com.sk89q.worldedit.MaxChangedBlocksException
import com.sk89q.worldedit.Vector
import com.sk89q.worldedit.blocks.BaseBlock
import com.sk89q.worldedit.bukkit.BukkitWorld
import com.sk89q.worldedit.regions.CuboidRegion
import com.sk89q.worldedit.regions.Region
import com.sk89q.worldedit.schematic.SchematicFormat
import com.sk89q.worldedit.world.World
import gg.scala.skywars.util.cuboid.Cuboid
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import java.io.File

/**
 * @author alfie
 * @project SkyWars
 * @date 18/03/2024 / 21:55
 */

class WorldEditUtils {
    fun getVectorByLocation(location: Location): Vector {
        return Vector(location.x, location.y, location.z)
    }

    companion object {
        private var EDIT_SESSION: EditSession? = null
        private var WORLD_EDIT_WORLD: BukkitWorld? = null

        fun initWorldEdit() {
            if (EDIT_SESSION != null) return

            WORLD_EDIT_WORLD = BukkitWorld(Bukkit.getWorld("world"))
            EDIT_SESSION = EditSessionBuilder(WORLD_EDIT_WORLD!!)
                .allowedRegionsEverywhere()
                .limitUnlimited()
                .autoQueue(true)
                .fastmode(true)
                .build()
        }

        @Throws(Exception::class)
        fun pasteSchematic(schematicFile: File?, pasteAt: Vector?): CuboidClipboard {
            initWorldEdit()

            val clipboard = SchematicFormat.MCEDIT.load(schematicFile)
            clipboard.offset = Vector(0, 0, 0)
            clipboard.paste(EDIT_SESSION, pasteAt, true)

            return clipboard
        }

        fun clear(bounds: _root_ide_package_.gg.scala.skywars.util.cuboid.Cuboid) {
            clear(
                Vector(bounds.lowerX, bounds.lowerY, bounds.lowerZ),
                Vector(bounds.upperX, bounds.upperY, bounds.upperZ)
            )
        }

        fun clear(lower: Vector, upper: Vector) {
            initWorldEdit()

            val air = BaseBlock(Material.AIR.id)
            val region: Region = CuboidRegion(WORLD_EDIT_WORLD, lower, upper)

            try {
                EDIT_SESSION!!.setBlocks(region, air)
            } catch (ex: MaxChangedBlocksException) {
                // our block change limit is Integer.MAX_VALUE, so will never
                // have to worry about this happening
                throw RuntimeException(ex)
            }
        }
    }
}
