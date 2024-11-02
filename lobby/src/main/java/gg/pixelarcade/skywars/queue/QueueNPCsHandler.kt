package gg.pixelarcade.skywars.queue

import gg.pixelarcade.skywars.game.state.GameTeamType
import gg.pixelarcade.skywars.menus.GameModeSelectorMenu
import lombok.Getter
import net.citizensnpcs.api.CitizensAPI
import net.citizensnpcs.api.npc.NPC
import net.citizensnpcs.trait.SkinTrait
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

class QueueNPCsHandler {
    //-- Instance Variables

    // Set the names of all the NPCs

    private val experimental: NPC = CitizensAPI.getNPCRegistry().createNPC(
        EntityType.PLAYER,
        (ChatColor.AQUA.toString() + "Experimental" + ChatColor.RED + " Coming Soon")
    )
    private val solo: NPC =
        CitizensAPI.getNPCRegistry().createNPC(
            EntityType.PLAYER,
            (ChatColor.AQUA.toString() + "Solo " + ChatColor.GRAY + "[Normal/Insane/Ranked]")
        )
    private val doubles: NPC =
        CitizensAPI.getNPCRegistry()
            .createNPC(EntityType.PLAYER, (ChatColor.AQUA.toString() + "Doubles " + ChatColor.GRAY + "[Normal/Insane]"))

    private val npcs: MutableList<NPC> = ArrayList()

    //-- Constructor
    init {
        setSkins()

        // Add these NPCs to the npcs list for later use
        npcs.add(experimental)
        npcs.add(solo)
        npcs.add(doubles)

        spawn()
    }

    fun getNpcs(): MutableList<NPC> {
        return npcs
    }

    fun destroy() {
        experimental.destroy()
        solo.destroy()
        doubles.destroy()
    }

    fun getMenu(npc: NPC, player: Player) {
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

    //-- Spawn in the NPCs
    private fun spawn() {
        experimental.spawn(NPCSpawnLocation.SPAWN_3.location)
        solo.spawn(NPCSpawnLocation.SPAWN_2.location)
        doubles.spawn(NPCSpawnLocation.SPAWN_1.location)
    }


    private fun setSkins() {
        val skinTrait1 = experimental.getOrAddTrait(SkinTrait::class.java)
        val skinTrait2 = solo.getOrAddTrait(SkinTrait::class.java)
        val skinTrait3 = doubles.getOrAddTrait(SkinTrait::class.java)

        skinTrait1.setSkinName("Wuusty__", true)
        skinTrait2.setSkinName("KuudraStun", true)
        skinTrait3.setSkinName("luanpereiradev2", true)
    }



    private enum class NPCSpawnLocation(val location: Location) {
        SPAWN_1(Location(Bukkit.getWorld("world"), 6.5, 100.0, 53.5, 180f, 0f)),
        SPAWN_2(Location(Bukkit.getWorld("world"), 0.5, 100.0, 55.5, 180f, 0f)),
        SPAWN_3(Location(Bukkit.getWorld("world"), -5.5, 100.0, 53.5, 180f, 0f))
    }
}
