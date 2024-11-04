package gg.pixelarcade.skywars.items

import gg.pixelarcade.skywars.SkyWarsGame
import gg.pixelarcade.skywars.item.Item
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.item.impl
 * @date 08/10/2023 / 14:09
 */
class LeaveGameItem(private val player: Player) : Item(player) {
    override fun getItem(player: Player): ItemStack {
        return ItemBuilder(Material.BED)
            .name(Color.translate("&cLeave Game &7(Right Click)"))
            .build()
    }

    override fun click(action: Action) {
        player.sendMessage(Color.translate("&cYou have left the game."))
        SkyWarsGame.gameInstance.gameHandler.removePlayer(player)
    }
}
