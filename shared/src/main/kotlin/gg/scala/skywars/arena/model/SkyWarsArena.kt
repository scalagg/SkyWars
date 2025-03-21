package gg.scala.skywars.arena.model

import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.utils.SafeLocation

/**
 * @date 11/15/2024
 * @author Moose1301
 */
class SkyWarsArena(var id: String, val name: String, val gameType: GameType, var supportsTeams: Boolean) {

    val spawns: MutableList<SafeLocation> = ArrayList()
    val zombieSpawns: MutableList<SafeLocation> = ArrayList()
    val chests: MutableMap<SafeLocation, String> = HashMap()
    var spectatorSpawnLoc: SafeLocation? = null
    var lobbySpawnLoc: SafeLocation? = null
    var suddenDeathSpawnLoc: SafeLocation? = null
    var tntGeneratorSpawnLoc: SafeLocation? = null

    var boundsLower: SafeLocation? = null;
    var boundsUpper: SafeLocation? = null;

    var valid: Boolean = false
}