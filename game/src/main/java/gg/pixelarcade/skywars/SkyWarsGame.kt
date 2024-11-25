package gg.pixelarcade.skywars

import gg.pixelarcade.skywars.ability.AbilityHandler
import gg.pixelarcade.skywars.ability.command.AbilityCommand
import gg.pixelarcade.skywars.ability.listener.AbilityInteractListener
import gg.pixelarcade.skywars.chests.ChestHandler
import gg.pixelarcade.skywars.game.GameHandler
import gg.pixelarcade.skywars.game.commands.GameCommand
import gg.pixelarcade.skywars.game.listener.GameListener
import gg.pixelarcade.skywars.game.listener.PreventionListener
import gg.pixelarcade.skywars.game.listener.SpectatorListener
import gg.pixelarcade.skywars.arena.engine.CgsSkywarsArena
import gg.pixelarcade.skywars.gamemode.SkywarsCgsGameMode
import gg.scala.cgs.common.information.arena.CgsGameArenaHandler
import gg.scala.cgs.common.information.mode.CgsGameMode
import gg.scala.commons.ExtendedScalaPlugin

import org.bukkit.event.Listener
import java.util.function.Consumer

//import gg.pixelarcade.skywars.map.GameMapHandler;
/**
 * @author alfie
 * @project SkyWarsGame
 * @date 27/10/2023 / 04:35
 */

class SkyWarsGame(plugin: ExtendedScalaPlugin) : SkyWarsShared(plugin) {
    lateinit var gameHandler: GameHandler
    lateinit var chestHandler: ChestHandler
    lateinit var abilityHandler: AbilityHandler

    override fun init() {
        gameInstance = this

        val orchestration = plugin.loadConfig("orchestration.yml")

        val mode = orchestration.getString("mode") ?: "gg.pixelarcade.skywars.gamemode.solo.SkyWarsSoloNormalGamemode"

        val modeClass = Class.forName(mode)

        val modeClassObject: SkywarsCgsGameMode = modeClass.getField("INSTANCE").get(null) as SkywarsCgsGameMode

        val engine = SkyWarsEngine(
            plugin, SkyWarsGameInfo,
            modeClassObject
        )
        engine.initialLoad()
        val arena = modeClassObject.getArenas().random();

        engine.gameArena = arena
        CgsGameArenaHandler.arena = arena
        SkyWarsEngine.INSTANCE = engine

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
