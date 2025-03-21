package gg.scala.skywars.items

import gg.scala.skywars.SkyWarsGame
import gg.scala.skywars.item.Item
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack

/**
 * @author alfie
 * @project Skywars / gg.scala.game.item.impl
 * @date 08/10/2023 / 15:21
 */
class ForceStartItem(private val player: Player) : Item(player) {
    override fun getItem(player: Player): ItemStack {
        return ItemBuilder(Material.DIAMOND)
            .name("&aForce Start &7(Right Click)")
            .build()
    }

    override fun click(action: Action) {
        SkyWarsGame.gameInstance.gameHandler.game.forceStarted = true
        SkyWarsGame.gameInstance.gameHandler.startCountdown()
    }
}
