package gg.pixelarcade.skywars.game.event

import gg.pixelarcade.skywars.game.Game
import gg.pixelarcade.skywars.game.state.GameState
import net.evilblock.cubed.event.PluginEvent


import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.game.event
 * @date 26/09/2023 / 23:02
 */

class GameStateChangeEvent(val game: Game, val oldState: GameState, val newState: GameState) : PluginEvent() {


}
