package gg.pixelarcade.skywars

import gg.pixelarcade.skywars.gamemode.SkyWarsSoloInsaneGamemode
import gg.pixelarcade.skywars.gamemode.SkyWarsSoloNormalGamemode
import gg.scala.cgs.common.information.CgsGameGeneralInfo

/**
 * @date 11/1/2024
 * @author Moose1301
 */
object SkyWarsGameInfo : CgsGameGeneralInfo(
    "SkyWars", 1F, 2,
    10, SkyWarsGameRewards, false, true,
    true, true, true,
    true, listOf(SkyWarsSoloNormalGamemode, SkyWarsSoloInsaneGamemode)
) {
    init {
        configureCombatLog = false
    }
}