package gg.pixelarcade.skywars.profile

import eu.decentsoftware.holograms.api.DHAPI
import eu.decentsoftware.holograms.api.holograms.Hologram
import gg.pixelarcade.skywars.SkyWarsShared
import net.citizensnpcs.api.CitizensAPI
import net.citizensnpcs.api.npc.NPC
import net.citizensnpcs.trait.SkinTrait
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import java.util.*

class PersonalStatsNPCHandler {
    private val holograms: MutableMap<UUID, Hologram?> =
        HashMap()


    private val npc: NPC =
        CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "&eRIGHT CLICK FOR MORE")

    init {
        val skin = npc.getOrAddTrait(SkinTrait::class.java)
        skin.skinName = "xulvy"
        npc.spawn(NPC_LOCATION)
    }

    fun getMenu(player: Player) {
        val playerName = player.name
        player.sendMessage(ChatColor.UNDERLINE.toString() + ChatColor.AQUA.toString() + "www.mccade.net/u/" + playerName + "/general")
    }

    fun destroy() {
        npc.destroy()
        holograms.forEach { (_: UUID?, hologram: Hologram?) -> hologram!!.destroy() }
    }

    fun spawnHologram(player: Player) {
        if (holograms[player.uniqueId] != null) {
            holograms[player.uniqueId]!!.destroy()
        }

        val profile = SkyWarsShared.instance.getProfile(player.uniqueId)
        val level = profile.getPrestige(profile.level)

        val hologram = DHAPI.createHologram("stats-" + player.uniqueId.toString(), HOLOGRAM_LOCATION)

        DHAPI.addHologramLine(hologram, "&b&nPersonal Stats")
        DHAPI.addHologramLine(hologram, "")
        DHAPI.addHologramLine(hologram, "&fLevel: " + level!!.color + profile.level + " ✯")
        DHAPI.addHologramLine(hologram, "&fProgress: &b" + profile.xp + "&7/&a" + level.xpPerLevel)
        DHAPI.addHologramLine(hologram, "&fWins: &b" + profile.wins)
        DHAPI.addHologramLine(hologram, "&fKills: &b" + profile.kills)
        DHAPI.addHologramLine(hologram, "&fDeaths: &b" + profile.deaths)
        DHAPI.addHologramLine(hologram, "")
        hologram.isDefaultVisibleState = false
        hologram.showPlayers.add(player.uniqueId)
        hologram.show(player, 0)
        holograms[player.uniqueId] = hologram
    }

    fun destroyHologram(player: Player) {
        if (holograms[player.uniqueId] != null) {
            holograms[player.uniqueId]!!.destroy()
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
