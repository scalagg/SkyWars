package gg.pixelarcade.skywars.region.selection.listeners;

import gg.pixelarcade.skywars.region.RegionHandler;
import gg.pixelarcade.skywars.region.selection.RegionSelection;
import lombok.RequiredArgsConstructor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.Color;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.region.selection.listeners
 * @date 19/10/2023 / 16:22
 */

@RequiredArgsConstructor
public class RegionSelectionListener implements Listener {

    private final RegionHandler handler;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Block clickedBlock = event.getClickedBlock();

        RegionSelection selection = handler.getSelection(player);
        int position = 0;

        if (item != null && item.getType() == RegionSelection.SELECTION_WAND.getType()
                && item.getItemMeta().hasDisplayName()
                && item.getItemMeta().getDisplayName().contains("Selection Wand")) {

            switch (event.getAction()) {
                case RIGHT_CLICK_AIR, RIGHT_CLICK_BLOCK -> {
                    position = 2;
                    selection.setPosition2(clickedBlock.getLocation());
                }

                case LEFT_CLICK_AIR, LEFT_CLICK_BLOCK -> {
                    position = 1;
                    selection.setPosition1(clickedBlock.getLocation());
                }
            }

            event.setCancelled(true);
            event.setUseItemInHand(Event.Result.DENY);
            event.setUseInteractedBlock(Event.Result.DENY);

            String message = "&e" + (position == 1 ? "First" : "Second") + " position set to " +
                    "&6(" + clickedBlock.getX() + ", " + clickedBlock.getY() + ", " + clickedBlock.getZ() + ")&e.";

            if (selection.isValidSelection())
                message += " &7(&e" + selection.getRegion().getBounds().volume() + "&7 blocks)";

            player.sendMessage(Color.translate(message));
        }
    }

}
