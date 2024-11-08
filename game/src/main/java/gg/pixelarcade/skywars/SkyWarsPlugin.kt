package gg.pixelarcade.skywars

import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.commons.core.plugin.*


import org.bukkit.plugin.java.JavaPlugin

@Plugin(
    name = "SkyWars-Game",
    version = "%remote%/%branch%/%id%"
)
@PluginAuthor("Solara")
@PluginWebsite("https://scala.gg")
@PluginDependencyComposite(
    PluginDependency("scala-commons"),
    PluginDependency("Lemon"),
    PluginDependency("ScGameFramework"),
    PluginDependency("CoreGameExtensions"),
    PluginDependency("cloudsync", soft = true),
)
class SkyWarsPlugin : ExtendedScalaPlugin() {
    val debug = true


    override fun enable() {
        super.enable()
        instance = this


        SkyWarsGame(this)
    }

    override fun disable() {
        SkyWarsGame.gameInstance.stop()
        super.disable()
    }


    companion object {
        lateinit var instance: SkyWarsPlugin
    }
}
