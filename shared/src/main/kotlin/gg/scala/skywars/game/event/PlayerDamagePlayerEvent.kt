package gg.scala.skywars.game.event

import gg.scala.skywars.game.Game
import net.evilblock.cubed.event.PluginEvent


import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList


class PlayerDamagePlayerEvent(val player: Player, val damager: Player, val game: Game) : PluginEvent() {

}
