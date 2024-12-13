package gg.pixelarcade.skywars.battlepass.menu.editor


import gg.pixelarcade.skywars.battlepass.menu.editor.button.*
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import org.bukkit.entity.Player

class BattlePassEditorMenu : Menu() {


    override fun size(buttons: Map<Int, Button>): Int {
        return 3*9
    }

    override fun getTitle(player: Player): String {
        return "BattlePass Editor"
    }

    override fun getButtons(player: Player): MutableMap<Int,Button> {
        return mutableMapOf(
            11 to EditTiersButton(),
            13 to EditChallengesButton(),
            15 to ResetDailyChallengesButton(),
            17 to ResetWeeklyChallengesButton(),
        )
    }


}