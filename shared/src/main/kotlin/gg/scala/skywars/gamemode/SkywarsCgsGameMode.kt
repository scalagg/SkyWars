package gg.scala.skywars.gamemode

import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.game.state.GameType
import gg.scala.cgs.common.information.arena.CgsGameArena
import gg.scala.cgs.common.information.mode.CgsGameMode

/**
 * @date 11/15/2024
 * @author Moose1301
 */
abstract class SkywarsCgsGameMode(val gameType: GameType, val teams: Boolean) : CgsGameMode {
    override fun getId() = "${gameType.name.lowercase()}-${
        if (teams) {
            "teams"
        } else {
            "solo"
        }
    }"

    override fun getName() = "${gameType.displayName} ${
        if (teams) {
            "Teams"
        } else {
            "Solo"
        }
    }"


    override fun getArenas(): List<CgsGameArena> = SkyWarsShared.instance.arenaManager.getArenas(gameType, teams)

}