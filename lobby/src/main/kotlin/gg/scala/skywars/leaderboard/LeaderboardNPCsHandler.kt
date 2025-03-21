package gg.scala.skywars.leaderboard

import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.leaderboard.impl.LevelLeaderboard
import gg.scala.skywars.leaderboard.impl.RankedLeaderboard
import gg.scala.skywars.profile.SkyWarsProfile
import net.evilblock.cubed.entity.EntityHandler
import net.evilblock.cubed.entity.npc.NpcEntity
import net.evilblock.cubed.util.bukkit.Tasks.delayed
import org.bukkit.Bukkit
import org.bukkit.Location
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Function
import java.util.function.Supplier

/** The handler that manages Leaderboard NPCs  */
class LeaderboardNPCsHandler {
    private val npcmap: MutableMap<Int, Supplier<CompletableFuture<SkyWarsProfile>>> =
        HashMap()
    private val nameMap: MutableMap<Int, Function<String, String>> =
        HashMap()

    /** The npc that show its respective top player  */

    // Creates the NPCs
    private val global1: NpcEntity
    private val global2: NpcEntity
    private val global3: NpcEntity

    private val ranked1: NpcEntity
    private val ranked2: NpcEntity
    private val ranked3: NpcEntity


    private var thread: NPCUpdateThread? = null

    init {
        global1 = NpcEntity(mutableListOf("???"), NPCSpawnLocation.GLOBAL_1.location)
        global2 = NpcEntity(mutableListOf("???"), NPCSpawnLocation.GLOBAL_2.location)
        global3 = NpcEntity(mutableListOf("???"), NPCSpawnLocation.GLOBAL_3.location)

        ranked1 = NpcEntity(mutableListOf("???"), NPCSpawnLocation.RANKED_1.location)
        ranked2 = NpcEntity(mutableListOf("???"), NPCSpawnLocation.RANKED_2.location)
        ranked3 = NpcEntity(mutableListOf("???"), NPCSpawnLocation.RANKED_3.location)


        global1.initializeData()
        global2.initializeData()
        global3.initializeData()
        ranked1.initializeData()
        ranked2.initializeData()
        ranked3.initializeData()
        EntityHandler.trackEntity(global1)
        EntityHandler.trackEntity(global2)
        EntityHandler.trackEntity(global3)
        EntityHandler.trackEntity(ranked1)
        EntityHandler.trackEntity(ranked2)
        EntityHandler.trackEntity(ranked3)


        this.setupNpcMap()
        this.setupNameMap()

        // Spawns the NPCs and start the updater thread
        delayed(20L * 5, Runnable {
            this.thread = NPCUpdateThread()
        })
    }


    /** Despawns the npcs  */
    fun destroy() {
        global1.destroyForCurrentWatchers()
        global2.destroyForCurrentWatchers()
        global3.destroyForCurrentWatchers()

        ranked1.destroyForCurrentWatchers()
        ranked2.destroyForCurrentWatchers()
        ranked3.destroyForCurrentWatchers()
        thread!!.interrupt()

        EntityHandler.forgetEntity(global1)
        EntityHandler.forgetEntity(global2)
        EntityHandler.forgetEntity(global3)
        EntityHandler.forgetEntity(ranked1)
        EntityHandler.forgetEntity(ranked2)
        EntityHandler.forgetEntity(ranked3)
    }

    /** Updates the npcs  */
    fun update() {
        npcmap.forEach { (id: Int, supplier: Supplier<CompletableFuture<SkyWarsProfile>>) ->
            val future: CompletableFuture<SkyWarsProfile> = supplier.get()
            val profile = future.join()

            val npc: NpcEntity = EntityHandler.getEntityById(id) as NpcEntity

            val name = profile?.name ?: "Anonymous"
            npc.updateTextureByUsername(name, onComplete = { success, thrown -> })
            npc.hologram.updateLines(listOf(nameMap[id]!!.apply(name)))
        }
    }

    private fun setupNameMap() {
        nameMap[global1.id] = Function { name: String -> "&3#1 $name" }
        nameMap[global2.id] = Function { name: String -> "&6#2 $name" }
        nameMap[global3.id] = Function { name: String -> "&7#3 $name" }

        nameMap[ranked1.id] = Function { name: String -> "&3#1 $name" }
        nameMap[ranked2.id] = Function { name: String -> "&6#2 $name" }
        nameMap[ranked3.id] = Function { name: String -> "&7#3 $name" }
    }

    private fun setupNpcMap() {
        npcmap[global1.id] = Supplier {
            GLOBAL_LEADERBOARD!!.top(1).thenApply { playerId: UUID? ->
                shared.getProfile(
                    playerId!!
                )
            }
        }
        npcmap[global2.id] = Supplier {
            GLOBAL_LEADERBOARD!!.top(2).thenApply { playerId: UUID? ->
                shared.getProfile(
                    playerId!!
                )
            }
        }
        npcmap[global3.id] = Supplier {
            GLOBAL_LEADERBOARD!!.top(3).thenApply { playerId: UUID? ->
                shared.getProfile(
                    playerId!!
                )
            }
        }

        npcmap[ranked1.id] = Supplier {
            RANKED_LEADERBOARD!!.top(1).thenApply { playerId: UUID? ->
                shared.getProfile(
                    playerId!!
                )
            }
        }
        npcmap[ranked2.id] = Supplier {
            RANKED_LEADERBOARD!!.top(2).thenApply { playerId: UUID? ->
                shared.getProfile(
                    playerId!!
                )
            }
        }
        npcmap[ranked3.id] = Supplier {
            RANKED_LEADERBOARD!!.top(3).thenApply { playerId: UUID? ->
                shared.getProfile(
                    playerId!!
                )
            }
        }
    }

    private inner class NPCUpdateThread : Thread("SkyWars - NPC Update Thread") {
        init {
            this.isDaemon = true
            this.start()
        }

        override fun run() {
            while (!isInterrupted) {
                update()
                try {
                    sleep(10000)
                } catch (ignored: InterruptedException) {
                }
            }
        }
    }


    private enum class NPCSpawnLocation(val location: Location) {
        GLOBAL_1(Location(Bukkit.getWorld("world"), -25.5, 95.0, 17.5, -125f, 0f)),
        GLOBAL_2(Location(Bukkit.getWorld("world"), -24.5, 94.0, 20.5, -125f, 0f)),
        GLOBAL_3(Location(Bukkit.getWorld("world"), -21.5, 93.0, 21.5, -125f, 0f)),

        RANKED_1(Location(Bukkit.getWorld("world"), -25.5, 95.0, -18.5, 90f, 0f)),
        RANKED_2(Location(Bukkit.getWorld("world"), -24.5, 94.0, -21.5, 90f, 0f)),
        RANKED_3(Location(Bukkit.getWorld("world"), -21.5, 93.0, -22.5, 90f, 0f)),
    }

    companion object {
        private val shared = SkyWarsShared.instance
        private val GLOBAL_LEADERBOARD = shared.leaderboardHandler.getLeaderboard(
            LevelLeaderboard::class.java
        )
        private val RANKED_LEADERBOARD = shared.leaderboardHandler.getLeaderboard(
            RankedLeaderboard::class.java
        )
    }
}
