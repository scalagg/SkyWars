package gg.pixelarcade.skywars.game.event

import gg.pixelarcade.skywars.game.Game
import lombok.Getter
import lombok.RequiredArgsConstructor
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.game.event
 * @date 26/09/2023 / 22:09
 */

class PlayerJoinGameEvent(val player: Player, val game: Game) : Event() {

    override fun getHandlers(): HandlerList {
        return handlerList
    }

    companion object {
        val handlerList: HandlerList = HandlerList()
    }
}
