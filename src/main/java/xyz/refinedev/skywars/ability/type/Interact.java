package xyz.refinedev.skywars.ability.type;

import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author alfie
 * @project Skywars
 * @date 03/10/2023 / 06:02
 */

public interface Interact {

    boolean onPlayerInteract(PlayerInteractEvent event);

}
