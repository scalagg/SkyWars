package gg.pixelarcade.skywars.game.event;

import gg.pixelarcade.skywars.game.SkyWarsGame;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.game.event
 * @date 07/10/2023 / 09:11
 */

@RequiredArgsConstructor
@Getter
public class GameStartEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final SkyWarsGame game;

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
