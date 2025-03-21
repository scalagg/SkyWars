package gg.scala.skywars.battlepass.tier.menu

import gg.scala.skywars.battlepass.menu.editor.BattlePassEditorMenu
import gg.scala.skywars.battlepass.tier.TierHandler
import gg.scala.skywars.battlepass.tier.menu.button.TierEditButton
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.buttons.BackButton
import net.evilblock.cubed.menu.pagination.PaginatedMenu

import org.bukkit.entity.Player

class TierEditorMenu : PaginatedMenu() {
    init {
        updateAfterClick = true
    }
    override fun size(buttons: Map<Int, Button>): Int {
        return 4 * 9
    }

    override fun getPrePaginatedTitle(player: Player): String {
        return "Tier Editor"
    }


    override fun getAllPagesButtons(player: Player): MutableMap<Int, Button> {
        return TierHandler.getAllTiers()
            .withIndex()
            .associate { it.index to TierEditButton(it.value) }
            .toMutableMap()
    }

    override fun getGlobalButtons(player: Player): Map<Int, Button> {
        return mutableMapOf(
            4 to BackButton(callback = { clicked -> BattlePassEditorMenu().openMenu(clicked) })
        )
    }

    override fun getMaxItemsPerPage(player: Player): Int {
        return 3 * 9
    }


}