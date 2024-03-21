package gg.pixelarcade.skywars.game.event;

import gg.pixelarcade.skywars.game.Game;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.game.event
 * @date 26/09/2023 / 22:23
 */

@RequiredArgsConstructor
@Getter
public class PlayerLeavePreGameEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final Game game;

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}