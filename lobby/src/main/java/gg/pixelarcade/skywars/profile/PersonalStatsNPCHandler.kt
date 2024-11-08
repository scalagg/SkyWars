package gg.pixelarcade.skywars.profile


import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.updateSkin
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
        npc.updateSkin(UUID.fromString("8aa31200-451c-44d5-821e-4878833ef6e1"))
        EntityHandler.trackEntity(npc)

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
            Bukkit.getWorld("world"), -13.5, 100.0, 8.5, -120f, 10f
        )
        private val HOLOGRAM_LOCATION = Location(
            Bukkit.getWorld("world"), -13.5, 104.5, 8.5, -120f, 0f
        )
    }
}
