package gg.pixelarcade.skywars.items

import com.cryptomorin.xseries.XMaterial
import gg.pixelarcade.skywars.item.Item
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.item.impl
 * @date 25/09/2023 / 22:45
 */
class ProfileItem(private val player: Player) : Item(player) {
    override fun getItem(player: Player): ItemStack {
        val item = ItemBuilder(XMaterial.PLAYER_HEAD)
        item.owner(player.name)
        item.name(Color.translate("&6Profile &7(Right Click)"))
        return item.build()
    }

    override fun click(action: Action) {

    }
}
