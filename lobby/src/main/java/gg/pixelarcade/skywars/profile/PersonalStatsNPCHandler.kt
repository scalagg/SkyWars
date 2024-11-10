package gg.pixelarcade.skywars.profile


import gg.pixelarcade.skywars.SkyWarsShared
import net.evilblock.cubed.entity.EntityHandler
import net.evilblock.cubed.entity.hologram.personalized.PersonalizedHologramEntity
import net.evilblock.cubed.entity.npc.NpcEntity
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.Player
import java.util.*

class PersonalStatsNPCHandler {
    private val holograms: MutableMap<UUID, PersonalizedHologramEntity> =
        HashMap()


    private val npc: NpcEntity = NpcEntity(mutableListOf("&eRIGHT CLICK FOR MORE"), NPC_LOCATION)

    init {
        npc.initializeData()
        npc.updateTextureByUsername("xulvy", onComplete = { success, thrown ->
            EntityHandler.trackEntity(npc)
        })

    }

    fun getMenu(player: Player) {
        val playerName = player.name
        player.sendMessage(ChatColor.UNDERLINE.toString() + ChatColor.AQUA.toString() + "www.mccade.net/u/" + playerName + "/general")
    }

    fun destroy() {
        EntityHandler.forgetEntity(npc)
        holograms.forEach { (playerId: UUID?, hologram: PersonalizedHologramEntity) ->
            run {
                val player: Player? = Bukkit.getPlayer(playerId);

                player?.let { hologram.destroy(it) }
            }
        }
    }

    fun spawnHologram(player: Player) {
        if (holograms[player.uniqueId] != null) {
            holograms[player.uniqueId]!!.destroy(player)
        }

        val profile = SkyWarsShared.instance.getProfile(player.uniqueId)

        val hologram = StatsHologram(HOLOGRAM_LOCATION, profile)
        hologram.initializeData()
        hologram.spawn(player)

        holograms[player.uniqueId] = hologram
    }

    fun destroyHologram(player: Player) {
        if (holograms[player.uniqueId] != null) {
            holograms[player.uniqueId]!!.destroy(player)

            holograms.remove(player.uniqueId)
        }
    }


    companion object {
        private val NPC_LOCATION = Location(
            Bukkit.getWorld("world"), -11.5, 91.0, 5.5, -120f, 10f
        )
        private val HOLOGRAM_LOCATION = Location(
            Bukkit.getWorld("world"), -11.5, 92.0, 5.5, -120f, 0f
        )
    }
}
