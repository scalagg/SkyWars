package gg.pixelarcade.skywars.leaderboard

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.leaderboard.impl.LevelLeaderboard
import gg.pixelarcade.skywars.leaderboard.impl.RankedLeaderboard
import gg.pixelarcade.skywars.profile.SkyWarsProfile

import net.citizensnpcs.api.CitizensAPI
import net.citizensnpcs.api.npc.NPC
import net.citizensnpcs.trait.SkinTrait
import net.evilblock.cubed.util.bukkit.Tasks.delayed
import net.evilblock.cubed.util.bukkit.Tasks.sync
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.EntityType
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
    private val global1: NPC =
        CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "global1")
    private val global2: NPC =
        CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "global2")
    private val global3: NPC =
        CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "global3")

    private val ranked1: NPC =
        CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "ranked1")
    private val ranked2: NPC =
        CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "ranked2")
    private val ranked3: NPC =
        CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "ranked3")


    private var thread: NPCUpdateThread? = null

    init {
        this.setupNpcMap()
        this.setupNameMap()

        // Spawns the NPCs and start the updater thread
        delayed(20L * 5, Runnable {
            spawn()
            this.thread = NPCUpdateThread()
        })
    }

    /** Spawns in the NPCs  */
    fun spawn() {
        global1.spawn(NPCSpawnLocation.GLOBAL_1.location)
        global2.spawn(NPCSpawnLocation.GLOBAL_2.location)
        global3.spawn(NPCSpawnLocation.GLOBAL_3.location)

        ranked1.spawn(NPCSpawnLocation.RANKED_1.location)
        ranked2.spawn(NPCSpawnLocation.RANKED_2.location)
        ranked3.spawn(NPCSpawnLocation.RANKED_3.location)
    }

    /** Despawns the npcs  */
    fun destroy() {
        global1.destroy()
        global2.destroy()
        global3.destroy()

        ranked1.destroy()
        ranked2.destroy()
        ranked3.destroy()
        thread!!.interrupt()
    }

    /** Updates the npcs  */
    fun update() {
        npcmap.forEach { (id: Int?, supplier: Supplier<CompletableFuture<SkyWarsProfile>>) ->
            val future: CompletableFuture<SkyWarsProfile> = supplier.get()
            val profile = future.join()

            val npc = CitizensAPI.getNPCRegistry().getById(id) ?: return@forEach
            val skinTrait = npc.getOrAddTrait(SkinTrait::class.java)

            val name = profile?.name ?: "Anonymous"
            if (!name.equals(skinTrait.skinName, ignoreCase = true)) {
                sync {
                    skinTrait.setSkinName(name, true)
                    if (profile != null) {
                        npc.name = nameMap[id]!!.apply(name)
                    }
                }
            }
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
        GLOBAL_1(Location(Bukkit.getWorld("world"), -9.5, 102.0, 45.5, -90f, 0f)),
        GLOBAL_2(Location(Bukkit.getWorld("world"), -8.5, 101.0, 47.5, -90f, 0f)),
        GLOBAL_3(Location(Bukkit.getWorld("world"), -8.5, 100.0, 43.5, -90f, 0f)),

        RANKED_1(Location(Bukkit.getWorld("world"), 10.5, 102.0, 45.5, 90f, 0f)),
        RANKED_2(Location(Bukkit.getWorld("world"), 9.5, 101.0, 47.5, 90f, 0f)),
        RANKED_3(Location(Bukkit.getWorld("world"), 9.5, 100.0, 43.5, 90f, 0f)),
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
