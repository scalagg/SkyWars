package gg.pixelarcade.skywars.game.event

import gg.pixelarcade.skywars.game.Game
import lombok.Getter
import lombok.RequiredArgsConstructor
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList


class PlayerDamagePlayerEvent(val player: Player, val damager: Player, val game: Game) : Event() {

    override fun getHandlers(): HandlerList {
        return handlerList
    }

    companion object {
        val handlerList: HandlerList = HandlerList()
    }
}
