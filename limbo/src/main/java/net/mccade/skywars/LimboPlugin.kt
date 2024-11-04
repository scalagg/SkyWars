package net.mccade.skywars

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

        Limbo(this)
    }

    override fun  disable() {
        Limbo.lobbyInstance.stop()
    }

    companion object {
        lateinit var instance: LimboPlugin
    }
}
