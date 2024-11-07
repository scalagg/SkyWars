package gg.pixelarcade.skywars

import gg.pixelarcade.skywars.gamemode.SkyWarsSoloGamemode
import gg.scala.cgs.common.information.CgsGameGeneralInfo

/**
 * @date 11/1/2024
 * @author Moose1301
 */
object SkyWarsGameInfo : CgsGameGeneralInfo(
    "SkyWars", 1F, 2,
    10, SkyWarsGameRewards, false, true,
    true, true, true,
    true, listOf(SkyWarsSoloGamemode)
)
