package gg.scala.skywars.region.selection.listeners

import gg.scala.skywars.region.RegionHandler
import gg.scala.skywars.region.selection.RegionSelection
import gg.scala.commons.annotations.Listeners

import net.evilblock.cubed.util.Color
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

/**
 * @author alfie
 * @project Skywars / gg.scala.game.region.selection.listeners
 * @date 19/10/2023 / 16:22
 */
@Listeners
object RegionSelectionListener : Listener {

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val item = event.item
        val clickedBlock = event.clickedBlock

        val selection = RegionHandler.getSelection(player)
        var position = 0

        if (item != null && item.type == RegionSelection.SELECTION_WAND.type && item.itemMeta.hasDisplayName()
            && item.itemMeta.displayName.contains("Selection Wand")
        ) {
            when (event.action) {
                Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK -> {
                    position = 2
                    selection.position2 = clickedBlock.location
                }

                Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK -> {
                    position = 1
                    selection.position1 = clickedBlock.location
                }

                Action.PHYSICAL -> {}
            }

            event.isCancelled = true
            event.setUseItemInHand(Event.Result.DENY)
            event.setUseInteractedBlock(Event.Result.DENY)

            var message = "&e" + (if (position == 1) "First" else "Second") + " position set to " +
                    "&6(" + clickedBlock.x + ", " + clickedBlock.y + ", " + clickedBlock.z + ")&e."

            if (selection.isValidSelection) message += " &7(&e" + selection.region!!.bounds.volume() + "&7 blocks)"

            player.sendMessage(Color.translate(message))
        }
    }
}
