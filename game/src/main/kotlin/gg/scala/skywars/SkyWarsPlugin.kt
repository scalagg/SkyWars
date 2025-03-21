package gg.scala.skywars

import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.commons.core.plugin.*

@Plugin(
    name = "SkyWars-Game",
    version = "%remote%/%branch%/%id%"
)
@PluginAuthor("Scala")
@PluginWebsite("https://scala.gg")
@PluginDependencyComposite(
    PluginDependency("scala-commons"),
    PluginDependency("Lemon"),
    PluginDependency("ScGameFramework"),
    PluginDependency("CoreGameExtensions"),
    PluginDependency("Apollo-Bukkit"),
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
        SkyWarsGame.gameInstance.shutdown()
        super.disable()
    }


    companion object {
        lateinit var instance: SkyWarsPlugin
    }
}
