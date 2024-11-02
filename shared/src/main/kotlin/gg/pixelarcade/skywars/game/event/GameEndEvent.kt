package gg.pixelarcade.skywars.game.event

import gg.pixelarcade.skywars.game.Game
import lombok.Getter
import lombok.RequiredArgsConstructor
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.game.event
 * @date 27/09/2023 / 03:50
 */

class GameEndEvent(val game: Game) : Event() {

    override fun getHandlers(): HandlerList {
        return handlerList
    }

    companion object {
        val handlerList: HandlerList = HandlerList()
    }
}