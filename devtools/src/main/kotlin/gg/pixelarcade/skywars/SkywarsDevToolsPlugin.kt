package gg.pixelarcade.skywars

import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.commons.core.plugin.*

/**
 * @date 11/18/2024
 * @author Moose1301
 */

@Plugin(
    name = "SkyWars-DevTools",
    version = "%remote%/%branch%/%id%"
)
@PluginAuthor("Solara")
@PluginWebsite("https://scala.gg")
@PluginDependencyComposite(
    PluginDependency("scala-commons"),
    PluginDependency("Lemon"),
    PluginDependency("ScBasics", soft = true),
    PluginDependency("cloudsync", soft = true),
)
class SkywarsDevToolsPlugin : ExtendedScalaPlugin() {
    override fun enable() {
        super.enable()
        instance = this

    }

    override fun disable() {
        super.disable()
    }


    companion object {

        lateinit var instance: SkywarsDevToolsPlugin
    }
}
