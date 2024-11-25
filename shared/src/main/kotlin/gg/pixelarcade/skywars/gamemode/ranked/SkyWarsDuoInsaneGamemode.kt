package gg.pixelarcade.skywars.gamemode.ranked

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.gamemode.SkywarsCgsGameMode
import net.evilblock.cubed.util.CC
import org.bukkit.Material

/**
 * @date 11/1/2024
 * @author Moose1301
 */
object SkyWarsDuoInsaneGamemode : SkywarsCgsGameMode(GameType.INSANE, true)
{

    override fun getMaterial() = Pair(Material.EYE_OF_ENDER, 0)

    override fun getDescription() = "${CC.GRAY}An duo game of Insane Skywars!"

    override fun getTeamSize() = 2 // team size
    override fun getMaxTeams() = 12 // max teams
}
