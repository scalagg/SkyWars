package gg.pixelarcade.skywars

import gg.pixelarcade.skywars.ability.AbilityHandler
import gg.pixelarcade.skywars.ability.command.AbilityCommand
import gg.pixelarcade.skywars.ability.listener.AbilityInteractListener
import gg.pixelarcade.skywars.chests.ChestHandler
import gg.pixelarcade.skywars.config.GameConfig
import gg.pixelarcade.skywars.game.GameHandler
import gg.pixelarcade.skywars.game.commands.GameCommand
import gg.pixelarcade.skywars.game.listener.GameListener
import gg.pixelarcade.skywars.game.listener.PreventionListener
import gg.pixelarcade.skywars.game.listener.SpectatorListener
import gg.pixelarcade.skywars.adapter.SkywarsScoreboardAdapter
import gg.pixelarcade.skywars.arena.SkywarsArena
import gg.pixelarcade.skywars.cages.command.SchemaCageCommandCustomizers
import gg.scala.cgs.common.information.arena.CgsGameArenaHandler
import gg.scala.cgs.common.information.mode.CgsGameMode
import gg.scala.commons.ExtendedScalaPlugin

import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import java.util.function.Consumer

//import gg.pixelarcade.skywars.map.GameMapHandler;
/**
 * @author alfie
 * @project SkyWarsGame
 * @date 27/10/2023 / 04:35
 */

class SkyWarsGame(plugin: ExtendedScalaPlugin) : SkyWarsShared(plugin) {
    lateinit var gameHandler: GameHandler
    lateinit var gameConfig: GameConfig
    lateinit var chestHandler: ChestHandler
    lateinit var abilityHandler: AbilityHandler

    override fun init() {
        gameInstance = this

        val orchestration = plugin.loadConfig("orchestration.yml")

        val mode = orchestration.getString("mode") ?: "gg.pixelarcade.skywars.gamemode.SkyWarsSoloGamemode"

        val modeClass = Class.forName(mode)

        val modeClassObject = modeClass.getField("INSTANCE").get(null)

        val engine = SkyWarsEngine(
            plugin, SkyWarsGameInfo,
            modeClassObject as CgsGameMode
        )
        engine.initialLoad()

        engine.gameArena = SkywarsArena
        CgsGameArenaHandler.arena = SkywarsArena
        SkyWarsEngine.INSTANCE = engine

        gameConfig = GameConfig

        gameHandler = GameHandler()
        chestHandler = ChestHandler()
        abilityHandler = AbilityHandler()

        listOf(
            AbilityInteractListener(this),
            GameListener(this),
            SpectatorListener(this),
            PreventionListener(this)
        ).forEach(Consumer { listener: Listener? ->
            SkyWarsPlugin.instance.server.pluginManager.registerEvents(
                listener,
                SkyWarsPlugin.instance
            )
        })

        commandHandler.registerCommand(GameCommand())
        commandHandler.registerCommand(AbilityCommand())
    }

    override fun stop() {}

    companion object {
        lateinit var gameInstance: SkyWarsGame
    }
}
