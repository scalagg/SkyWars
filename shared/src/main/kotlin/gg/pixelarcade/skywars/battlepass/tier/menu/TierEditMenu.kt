package gg.pixelarcade.skywars.battlepass.tier.menu

import gg.pixelarcade.skywars.battlepass.tier.Tier
import gg.pixelarcade.skywars.battlepass.tier.menu.button.TierEditExperienceButton
import gg.pixelarcade.skywars.battlepass.tier.menu.button.TierEditRewardButton
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.menu.buttons.BackButton
import org.bukkit.entity.Player

class TierEditMenu(private val tier: Tier) : Menu() {
    init {
        updateAfterClick = true
    }
    override fun size(buttons: Map<Int, Button>): Int {
        return 3*9
    }

    override fun getTitle(player: Player): String {
        return "Edit Tier ${this.tier.number}"
    }

    override fun getButtons(player: Player): MutableMap<Int, Button> {
        return mutableMapOf(
            4 to BackButton(callback = { clicked -> TierEditorMenu().openMenu(clicked) } ),
            11 to TierEditExperienceButton(this.tier),
            13 to TierEditRewardButton(this.tier,true),
            15 to TierEditRewardButton(this.tier,false)
        )
    }

}