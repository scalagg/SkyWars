package xyz.refinedev.skywars.utils.events.arena;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.event.Event;
import xyz.refinedev.skywars.arena.Arena;

/**
 * Represents an event involving an {@link Arena}
 */
abstract class ArenaEvent extends Event {

    /**
     * The match involved in this event
     */
    @Getter private final Arena arena;

    ArenaEvent(Arena arena) {
        this.arena = Preconditions.checkNotNull(arena, "arena");
    }

}