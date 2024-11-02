package gg.pixelarcade.skywars.region.menu

import gg.pixelarcade.skywars.SkyWarsLobby
import gg.pixelarcade.skywars.region.menu.buttons.RegionsButton
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.pagination.PaginatedMenu
import org.bukkit.entity.Player

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.region.menu
 * @date 19/10/2023 / 19:20
 */
class RegionEditorMenu : PaginatedMenu() {
    override fun getPrePaginatedTitle(player: Player): String {
        return "Regions"
    }

    override fun getAllPagesButtons(player: Player): Map<Int, Button> {
        val buttons: MutableMap<Int, Button> = HashMap()

        for (regions in SkyWarsLobby.lobbyInstance.regionHandler.getRegions()) {
            buttons[buttons.size] = RegionsButton(regions)
        }

        return buttons
    }
}
