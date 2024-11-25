package gg.pixelarcade.skywars.map


import com.grinderwolf.swm.api.SlimePlugin
import com.grinderwolf.swm.api.loaders.SlimeLoader
import com.grinderwolf.swm.api.world.properties.SlimePropertyMap
import com.grinderwolf.swm.plugin.config.WorldData
import com.mojang.authlib.properties.PropertyMap
import gg.pixelarcade.skywars.SkywarsDevToolsPlugin
import gg.scala.flavor.inject.Inject
import gg.scala.flavor.service.Configure
import gg.scala.flavor.service.Service
import net.kyori.adventure.platform.bukkit.BukkitAudiences

/**
 * @author GrowlyX
 * @since 9/22/2023
 */
@Service
object MapManageServices
{
    @Inject
    lateinit var plugin: SkywarsDevToolsPlugin

    @Inject
    lateinit var audiences: BukkitAudiences

    lateinit var slimePlugin: SlimePlugin
    lateinit var loader: SlimeLoader
    lateinit var propertyMap: SlimePropertyMap

    @Configure
    fun configure()
    {
        slimePlugin = plugin.server.pluginManager
            .getPlugin("SlimeWorldManager") as SlimePlugin

        loader = slimePlugin.getLoader("mongodb")

        val worldData = WorldData()
        worldData.isPvp = true
        worldData.difficulty = "normal"
        worldData.environment = "NORMAL"
        worldData.worldType = "DEFAULT"

        worldData.isAllowAnimals = true
        worldData.isAllowMonsters = true

        propertyMap = worldData.toPropertyMap();
    }
}
