package gg.pixelarcade.skywars.queue

import gg.pixelarcade.skywars.game.state.GameTeamType
import gg.pixelarcade.skywars.menus.GameModeSelectorMenu
import gg.scala.cache.uuid.ScalaStoreUuidCache
import net.evilblock.cubed.entity.EntityHandler

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
        for (npc in npcs) {
            npc.destroyForCurrentWatchers()
            EntityHandler.forgetEntity(npc)
        }
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
        experimental.updateTextureByUsername("Wuusty__", onComplete = { success, thrown -> EntityHandler.trackEntity(experimental) })
        solo.updateTextureByUsername("KuudraStun", onComplete = { success, thrown -> EntityHandler.trackEntity(solo) })
        doubles.updateTextureByUsername("luanpereiradev2", onComplete = { success, thrown -> EntityHandler.trackEntity(doubles) })
    }



    private enum class NPCSpawnLocation(val location: Location) {
        SPAWN_1(Location(Bukkit.getWorld("world"), -37.5, 92.5, -3.5, -90f, 0f)),
        SPAWN_2(Location(Bukkit.getWorld("world"), -38.5, 92.5, -0.5, -90f, 0f)),
        SPAWN_3(Location(Bukkit.getWorld("world"), -37.5, 92.5, 2.5, -90f, 0f))
    }
}
