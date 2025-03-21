package gg.scala.skywars.battlepass.challenge.menu

import gg.scala.skywars.battlepass.challenge.Challenge
import gg.scala.skywars.battlepass.challenge.menu.button.ChallengeDailyButton
import gg.scala.skywars.battlepass.challenge.menu.button.ChallengeExperienceButton
import gg.scala.skywars.battlepass.challenge.menu.button.ChallengeNameButton
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.menu.buttons.BackButton
import org.bukkit.entity.Player

class ChallengeEditMenu(private val daily: Boolean, private val weekly: Boolean, private val challenge: Challenge) :
    Menu() {

    override fun size(buttons: Map<Int, Button>): Int {
        return 3 * 9
    }

    override fun getTitle(player: Player): String {
        return "Edit Challenge - ${this.challenge.name}"
    }

    override fun getButtons(player: Player): MutableMap<Int, Button> {
        return mutableMapOf(
            10 to ChallengeNameButton(this.daily, this.weekly, this.challenge),
            11 to ChallengeDailyButton(this.challenge),
            12 to ChallengeExperienceButton(this.daily, this.weekly, this.challenge),
            16 to BackButton(callback = { clicked -> ChallengeEditorMenu(this.daily, this.weekly).openMenu(clicked) })
        ).also { this.challenge.getType().getEditorButtons(this.challenge).forEach { button -> it[it.size] = button } }
    }

}