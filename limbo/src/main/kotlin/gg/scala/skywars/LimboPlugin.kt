package gg.scala.skywars

import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.commons.core.plugin.*


import org.bukkit.plugin.java.JavaPlugin

@Plugin(
    name = "SkyWars-Limbo",
    version = "%remote%/%branch%/%id%"
)
@PluginAuthor("Solara")
@PluginWebsite("https://scala.gg")
@PluginDependencyComposite(
    PluginDependency("scala-commons"),
    PluginDependency("Lemon"),
    PluginDependency("Deployer"),
    PluginDependency("cloudsync", soft = true),
)
class LimboPlugin : ExtendedScalaPlugin() {
    var debug: Boolean = true

    override fun enable() {
        instance = this

        gg.scala.skywars.Limbo(this)
    }

    override fun  disable() {
        gg.scala.skywars.Limbo.lobbyInstance.shutdown()
    }

    companion object {
        lateinit var instance: LimboPlugin
    }
}
