package gg.pixelarcade.skywars.game.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import gg.pixelarcade.skywars.game.SkyWarsGame;
import gg.pixelarcade.skywars.game.state.GameState;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.game.event
 * @date 26/09/2023 / 23:02
 */

@RequiredArgsConstructor
@Getter
public class GameStateChangeEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final SkyWarsGame game;
    private final GameState oldState;
    private final GameState newState;

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
