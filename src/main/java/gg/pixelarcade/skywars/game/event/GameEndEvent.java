package gg.pixelarcade.skywars.game.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import gg.pixelarcade.skywars.game.SkyWarsGame;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.game.event
 * @date 27/09/2023 / 03:50
 */

@RequiredArgsConstructor
@Getter
public class GameEndEvent extends Event {

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