package gg.pixelarcade.skywars.arena

import com.google.common.collect.HashBasedTable
import com.google.common.collect.Table
import gg.pixelarcade.skywars.arena.container.ArenaDataSync
import gg.pixelarcade.skywars.arena.engine.CgsSkywarsArena
import gg.pixelarcade.skywars.game.state.GameType

/**
 * @date 11/15/2024
 * @author Moose1301
 */
class ArenaManager {
    private val arenas: Table<GameType, String, CgsSkywarsArena> = HashBasedTable.create()


    init {
        for (entry in ArenaDataSync.cached().arenas.entries) {
            val arena = entry.value;


            arenas.put(arena.gameType, arena.id, CgsSkywarsArena(arena.id, arena))
        }


    }


    fun getArenaIds(): Set<String> = arenas.columnKeySet()

    fun getArenas(gameType: GameType, teams: Boolean): List<CgsSkywarsArena> {

        if (!arenas.containsRow(gameType)) {
            return listOf()
        }

        val arenas = ArrayList<CgsSkywarsArena>(arenas.row(gameType).values);
        arenas.removeIf {
            if (!teams || !it.arena.valid)
                return@removeIf false;

            return@removeIf !it.arena.supportsTeams;
        }

        return arenas


    }


}
