package gg.pixelarcade.skywars


import gg.pixelarcade.skywars.gamemode.ranked.SkyWarsDuoInsaneGamemode
import gg.pixelarcade.skywars.gamemode.ranked.SkyWarsDuoNormalGamemode
import gg.pixelarcade.skywars.gamemode.solo.SkyWarsSoloInsaneGamemode
import gg.pixelarcade.skywars.gamemode.solo.SkyWarsSoloNormalGamemode
import gg.scala.cgs.common.information.CgsGameGeneralInfo

/**
 * @date 11/1/2024
 * @author Moose1301
 */
object SkyWarsGameInfo : CgsGameGeneralInfo(
    "SkyWars", 1F, 2,
    10, SkyWarsGameRewards, false, true,
    true, true, true,
    true, listOf(
        SkyWarsSoloNormalGamemode, SkyWarsSoloInsaneGamemode,
        SkyWarsDuoNormalGamemode, SkyWarsDuoInsaneGamemode
    )
) {
    init {
        configureCombatLog = false
    }
}