package gg.scala.skywars.adapter

import gg.scala.cgs.common.player.CgsGamePlayer
import gg.scala.cgs.common.player.visibility.CgsGameVisibilityAdapter
import net.evilblock.cubed.visibility.VisibilityAction

object SkyWarsVisibilityAdapter : CgsGameVisibilityAdapter
{
    override fun computeVisibility(
        viewer: CgsGamePlayer, target: CgsGamePlayer
    ): VisibilityAction
    {
        return VisibilityAction.NEUTRAL
    }
}
