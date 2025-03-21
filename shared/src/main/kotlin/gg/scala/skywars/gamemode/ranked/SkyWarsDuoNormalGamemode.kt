package gg.scala.skywars.gamemode.ranked

import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.gamemode.SkywarsCgsGameMode
import net.evilblock.cubed.util.CC
import org.bukkit.Material

/**
 * @date 11/1/2024
 * @author Moose1301
 */
object SkyWarsDuoNormalGamemode : SkywarsCgsGameMode(GameType.NORMAL, true)
{

    override fun getMaterial() = Pair(Material.EYE_OF_ENDER, 0)

    override fun getDescription() = "${CC.GRAY}An duo game of Normal Skywars!"

    override fun getTeamSize() = 2 // team size
    override fun getMaxTeams() = 12 // max teams
}
