package xyz.refinedev.skywars.utils.events.arena;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import xyz.refinedev.skywars.arena.Arena;

/**
 * Called when an {@link Arena} is allocated for use by a
 */
public final class ArenaAllocatedEvent extends ArenaEvent {

    @Getter
    private static HandlerList handlerList = new HandlerList();

    public ArenaAllocatedEvent(Arena arena) {
        super(arena);
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

}