package gg.scala.skywars.arena.engine

import com.grinderwolf.swm.api.SlimePlugin
import com.grinderwolf.swm.plugin.config.WorldData
import gg.scala.skywars.arena.model.SkyWarsArena
import gg.scala.cgs.common.information.arena.CgsGameArena
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import java.nio.file.Path

/**
 * @date 11/5/2024
 * @author Moose1301
 */
class CgsSkywarsArena(@JvmField val id: String, val arena: SkyWarsArena) : CgsGameArena {


    init {
        val worldData = WorldData()
        worldData.isPvp = true
        worldData.difficulty = "normal"
        worldData.environment = "NORMAL"
        worldData.worldType = "DEFAULT"

        worldData.isAllowAnimals = true
        worldData.isAllowMonsters = true

        val slime = (Bukkit.getPluginManager().getPlugin("SlimeWorldManager") as SlimePlugin)
        val loader = slime.getLoader("mongodb")
        val world = slime.loadWorld(loader, getId(), true, worldData.toPropertyMap()).clone(getBukkitWorldName())
        slime.generateWorld(world)
    }

    override fun getBukkitWorldName(): String {
        return "${getId()}-clone"
    }

    override fun getDescription(): String {
         return "default"
    }

    override fun getDirectory(): Path? = null

    override fun getId(): String {
        return id;
    }

    override fun getMaterial(): Pair<Material, Int> {
        return Pair(Material.STONE, 1)
    }

    override fun getName(): String {
        return arena.name
    }

    override fun getPreLobbyLocation() = arena.lobbySpawnLoc!!.toBukkit(Bukkit.getWorld(getBukkitWorldName()),)

    override fun getSpectatorLocation() = getPreLobbyLocation()

}
