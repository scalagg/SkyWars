package gg.scala.skywars.battlepass.challenge.menu

import gg.scala.skywars.battlepass.BattlePassHandler
import gg.scala.skywars.battlepass.challenge.ChallengeHandler
import gg.scala.skywars.battlepass.challenge.menu.button.ChallengeButton
import gg.scala.skywars.battlepass.daily.DailyChallengeHandler
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.pagination.PaginatedMenu
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.text.TextSplitter
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player

class ChallengeMenu(private val daily: Boolean) : PaginatedMenu() {

    override fun getPrePaginatedTitle(player: Player): String {
        return "Challenges"
    }


    override fun getPageButtonSlots(): Pair<Int, Int> {
        return 45 to 53
    }

    override fun getAllPagesButtons(player: Player): Map<Int, Button> {
        val buttons = hashMapOf<Int, Button>()
        val offset = (this.page - 1) * SLOTS.size

        val challenges = if (this.daily) {
            DailyChallengeHandler.getSession().getChallenges()
        } else {
            ChallengeHandler.getChallenges()
        }

        val battlePass = BattlePassHandler.getBattlePassByPlayer(player)

        for ((index, challenge) in challenges.sortedBy{it.rewardXp}.drop(offset).withIndex()) {
            if (index >= SLOTS.size) {
                break
            }

            buttons[SLOTS[index]] = ChallengeButton(challenge,battlePass)
        }

        if (this.daily) {
            buttons[4] = Button.placeholder(
                ItemBuilder.of(Material.BOOK)
                .name("${ChatColor.GOLD}${ChatColor.BOLD}Daily Challenges")
                .setLore(
                    TextSplitter.split(
                    text = "New daily challenges will be picked in ${DailyChallengeHandler.getSession().getTimeRemaining()}.",
                    linePrefix = ChatColor.GRAY.toString())
                )
                .build()
            )
        }

        //buttons[49] = BackButton(BattlePassMenu(battlePass))

        return buttons
    }

    override fun getAllPagesButtonSlots(): List<Int> {
        return SLOTS
    }

    override fun getMaxItemsPerPage(player: Player): Int {
        return 18
    }

    private val SLOTS = arrayListOf(
        10, 12, 14, 16,
        28, 30, 32, 34
    )

}