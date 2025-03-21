package gg.scala.skywars.battlepass.challenge.menu

import gg.scala.skywars.battlepass.challenge.ChallengeHandler
import gg.scala.skywars.battlepass.challenge.menu.button.ChallengeAddButton
import gg.scala.skywars.battlepass.challenge.menu.button.ChallengeRewardButton
import gg.scala.skywars.battlepass.menu.editor.BattlePassEditorMenu
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.buttons.BackButton
import net.evilblock.cubed.menu.pagination.PaginatedMenu

import org.bukkit.entity.Player

class ChallengeEditorMenu(private val daily: Boolean, private val weekly: Boolean) : PaginatedMenu() {
    override fun size(buttons: Map<Int, Button>): Int {
        return 6*9
    }

    override fun getPrePaginatedTitle(player: Player): String {
        return "BattlePass Challenge Editor"
    }

    override fun getGlobalButtons(player: Player): Map<Int, Button> {
        return mutableMapOf(
            3 to ChallengeAddButton(this.daily, this.weekly),
            5 to BackButton(callback = { clicked -> BattlePassEditorMenu().openMenu(clicked) } )
        )
    }

    override fun getAllPagesButtons(player: Player): Map<Int, Button> {
        return ChallengeHandler.getChallenges()
            .filter{it.daily == this.daily}
            .filter{it.weekly == this.weekly}
            .sortedBy{it.rewardXp}
            .withIndex()
            .associate{it.index to ChallengeRewardButton(this.daily, this.weekly, it.value)}
            .toMutableMap()
    }

    override fun getMaxItemsPerPage(player: Player): Int {
        return 3*9
    }



}