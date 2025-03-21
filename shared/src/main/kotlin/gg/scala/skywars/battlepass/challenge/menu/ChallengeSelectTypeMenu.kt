package gg.scala.skywars.battlepass.challenge.menu

import gg.scala.skywars.battlepass.challenge.ChallengeHandler
import gg.scala.skywars.battlepass.challenge.ChallengeType
import gg.scala.skywars.battlepass.challenge.menu.button.ChallengeTypeButton
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import org.bukkit.entity.Player

class ChallengeSelectTypeMenu(private val lambda: (ChallengeType) -> Unit) : Menu() {

    override fun getTitle(player: Player): String {
        return "Select Challenge Type"
    }


    override fun getButtons(player: Player): MutableMap<Int, Button> {
        val buttons = hashMapOf<Int, Button>()

        for (challengeType in ChallengeHandler.getAllChallengeTypes()) {
            buttons[buttons.size] = ChallengeTypeButton(this.lambda,challengeType)
        }

        return buttons
    }

}