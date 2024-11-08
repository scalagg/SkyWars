package gg.pixelarcade.skywars.queue

import gg.pixelarcade.skywars.game.state.GameTeamType
import gg.pixelarcade.skywars.menus.GameModeSelectorMenu
import gg.pixelarcade.skywars.updateSkin
import gg.scala.cache.uuid.ScalaStoreUuidCache

import net.evilblock.cubed.entity.npc.NpcEntity
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.ArrayList

class QueueNPCsHandler {
    //-- Instance Variables

    // Set the names of all the NPCs

    private val experimental: NpcEntity
    private val solo: NpcEntity
    private val doubles: NpcEntity

    private val npcs: MutableList<NpcEntity> = ArrayList()

    //-- Constructor
    init {
        experimental = NpcEntity(listOf(ChatColor.AQUA.toString() + "Experimental" + ChatColor.RED + " Coming Soon"), NPCSpawnLocation.SPAWN_3.location)
        solo = NpcEntity(listOf  (ChatColor.AQUA.toString() + "Solo " + ChatColor.GRAY + "[Normal/Insane/Ranked]"), NPCSpawnLocation.SPAWN_2.location)
        doubles = NpcEntity(listOf(ChatColor.AQUA.toString() + "Doubles " + ChatColor.GRAY + "[Normal/Insane]"), NPCSpawnLocation.SPAWN_1.location)

        // Add these NPCs to the npcs list for later use
        npcs.add(experimental)
        npcs.add(solo)
        npcs.add(doubles)
        npcs.forEach { it.initializeData() }
        setSkins()



    }

    fun getNpcs(): MutableList<NpcEntity> {
        return npcs
    }

    fun destroy() {
        experimental.destroyForCurrentWatchers()
        solo.destroyForCurrentWatchers()
        doubles.destroyForCurrentWatchers()
    }

    fun getMenu(npc: NpcEntity, player: Player) {
        if (npc == experimental) {
            player.sendMessage(ChatColor.RED.toString() + "Sorry, this gamemode isn't out yet!")
        }
        if (npc == solo) {
            GameModeSelectorMenu(GameTeamType.SOLO).openMenu(player)
        }
        if (npc == doubles) {
            GameModeSelectorMenu(GameTeamType.DOUBLES).openMenu(player)
        }
    }




    private fun setSkins() {
        experimental.updateSkin(UUID.fromString("a9c58c9d-660a-416b-820d-a56b2ed3824a"))
        solo.updateSkin(UUID.fromString("b4ecea17-46c2-4965-8419-efe45f5c5771"))
        doubles.updateSkin(UUID.fromString("8fd2a5c2-0ce0-4844-a99e-7c5ec74ae94a"))
    }



    private enum class NPCSpawnLocation(val location: Location) {
        SPAWN_1(Location(Bukkit.getWorld("world"), 6.5, 100.0, 53.5, 180f, 0f)),
        SPAWN_2(Location(Bukkit.getWorld("world"), 0.5, 100.0, 55.5, 180f, 0f)),
        SPAWN_3(Location(Bukkit.getWorld("world"), -5.5, 100.0, 53.5, 180f, 0f))
    }
}
