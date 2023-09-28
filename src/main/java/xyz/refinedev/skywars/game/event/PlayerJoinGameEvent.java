package xyz.refinedev.skywars.game.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import xyz.refinedev.skywars.game.SkyWarsGame;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.game.event
 * @date 26/09/2023 / 22:09
 */

@RequiredArgsConstructor
@Getter
public class PlayerJoinGameEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final SkyWarsGame game;

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
