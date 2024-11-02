package gg.pixelarcade.skywars.items

import com.cryptomorin.xseries.XMaterial
import gg.pixelarcade.skywars.game.state.GameTeamType
import gg.pixelarcade.skywars.item.Item
import gg.pixelarcade.skywars.menus.GameModeSelectorMenu
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
class SoloQueueItem(private val player: Player) : Item(player) {
    private val gameType = GameTeamType.SOLO

    override fun getItem(player: Player): ItemStack {
        return ItemBuilder(XMaterial.BOOK, 1)
            .name(Color.translate("&aQueue Solo SkyWars &7(Right Click)"))
            .build()
    }

    override fun click(action: Action) {
        GameModeSelectorMenu(gameType).openMenu(player)
    }
}
