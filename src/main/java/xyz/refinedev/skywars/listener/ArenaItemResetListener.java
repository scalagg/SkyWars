package xyz.refinedev.skywars.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xyz.refinedev.skywars.arena.Arena;
import xyz.refinedev.skywars.utils.events.arena.ArenaReleasedEvent;

/**
 * Remove dropped items when {@link Arena}s are released.
 */
public final class ArenaItemResetListener implements Listener {

    @EventHandler
    public void onArenaReleased(ArenaReleasedEvent event) {
        Arena arena = event.getArena();
        // force load all chunks (can't iterate entities in an unload chunk)
        // that are at all covered by this map.
        // Only do this if chunk is NOT loaded
        arena.forEachChunk(chunk -> {
            if (!chunk.isLoaded())
                chunk.load();

            for (Entity entity : chunk.getEntities()) {
                // if we remove all entities we might call .remove()
                // on a player (breaks a lot of things)
                if (entity instanceof Item && arena.getBounds().contains(entity)) {
                    entity.remove();
                }
            }
        });
    }
}