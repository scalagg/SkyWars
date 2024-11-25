package gg.pixelarcade.skywars.gamemode.solo

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.gamemode.SkywarsCgsGameMode
import net.evilblock.cubed.util.CC
import org.bukkit.Material

/**
 * @date 11/1/2024
 * @author Moose1301
 */
object SkyWarsSoloInsaneGamemode : SkywarsCgsGameMode(GameType.INSANE, false)
{

    override fun getMaterial() = Pair(Material.EYE_OF_ENDER, 0)

    override fun getDescription() = "${CC.GRAY}An solo game of Insane Skywars!"

    override fun getTeamSize() = 1 // team size
    override fun getMaxTeams() = 12 // max teams
}
