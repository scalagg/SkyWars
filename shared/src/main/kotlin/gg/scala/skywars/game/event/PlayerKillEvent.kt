package gg.scala.skywars.game.event

import gg.scala.skywars.game.Game
import net.evilblock.cubed.event.PluginEvent


import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * @author alfie
 * @project Skywars / gg.scala.game.game.event
 * @date 26/09/2023 / 22:09
 */

class PlayerKillEvent(val player: Player, val killer: Player?, val game: Game) : PluginEvent() {

}
