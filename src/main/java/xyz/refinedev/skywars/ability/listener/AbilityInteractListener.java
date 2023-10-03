package xyz.refinedev.skywars.ability.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.skywars.ability.Ability;
import xyz.refinedev.skywars.ability.type.Interact;
import xyz.refinedev.skywars.pxSkyWars;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.ability.listener
 * @date 03/10/2023 / 06:08
 */

@RequiredArgsConstructor
public class AbilityInteractListener implements Listener {

    private final pxSkyWars plugin;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();

        if (item == null || action == Action.PHYSICAL || action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK)
            return;

        if (!plugin.getGameHandler().isPlayingGame(player)) {
            player.sendMessage(Color.translate("&cYou must be in a game to use this item."));
            return;
        }

        Ability ability = plugin.getAbilityHandler().getAbility(item);
        if (!(ability instanceof Interact))
            return;

        Interact interact = (Interact) ability;
        if (!interact.onPlayerInteract(event))
            return;

        if (player.getItemInHand().getAmount() == 1)
            player.setItemInHand(null);
        else player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);

        player.updateInventory();
        interact.onPlayerInteract(event);
    }

}
