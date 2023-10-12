package gg.pixelarcade.skywars.ability.listener;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.ability.Ability;
import gg.pixelarcade.skywars.ability.type.Interact;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.ability.listener
 * @date 03/10/2023 / 06:08
 */

@RequiredArgsConstructor
public class AbilityInteractListener implements Listener {

    //TODO: 03/10/2023 fix not being able to have a stack of abilities

    private final SkyWarsPlugin plugin;

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null)
            return;

        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        String displayName = ChatColor.stripColor(item.getItemMeta().getDisplayName().replaceAll(" ", "").toLowerCase());
        Ability ability = plugin.getAbilityHandler().getAbilityById(displayName);
        if (!(ability instanceof Interact))
            return;

        if (!ability.isSimilar(item))
            return;

        if (!plugin.getGameHandler().isPlayingGame(player))
            return;

        Interact interact = (Interact) ability;
        if (!interact.onPlayerInteract(event))
            return;

        ability.consume(player);
        event.setCancelled(true);
    }

}
