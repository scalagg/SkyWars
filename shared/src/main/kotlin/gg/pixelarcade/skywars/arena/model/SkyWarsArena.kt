package gg.pixelarcade.skywars.arena.model

import gg.pixelarcade.skywars.game.state.GameType
import gg.pixelarcade.skywars.utils.SafeLocation

/**
 * @date 11/15/2024
 * @author Moose1301
 */
class SkyWarsArena(var id: String, val name: String, val gameType: GameType, val supportsTeams: Boolean) {

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