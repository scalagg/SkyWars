package gg.pixelarcade.skywars.game.event

import gg.pixelarcade.skywars.game.Game
import net.evilblock.cubed.event.PluginEvent


import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.game.event
 * @date 26/09/2023 / 22:24
 */

class PlayerLeaveGameEvent(val player: Player, val game: Game) : PluginEvent() {


}