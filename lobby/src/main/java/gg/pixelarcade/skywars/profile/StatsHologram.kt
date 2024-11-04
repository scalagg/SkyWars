package gg.pixelarcade.skywars.profile

import net.evilblock.cubed.entity.hologram.personalized.PersonalizedHologramEntity
import org.bukkit.Location
import org.bukkit.entity.Player

class StatsHologram(location: Location, val profile: SkyWarsProfile) : PersonalizedHologramEntity(location) {
    override fun getNewLines(player: Player): List<String> {
        val level = profile.getPrestige(profile.level)

        val lines = mutableListOf<String>()
        lines.add("&b&nPersonal Stats")
        lines.add("")
        lines.add("&fLevel: " + level!!.color + profile.level + " ✯")
        lines.add("&fProgress: &b" + profile.xp + "&7/&a" + level.xpPerLevel)
        lines.add("&fWins: &b" + profile.wins)
        lines.add("&fKills: &b" + profile.kills)
        lines.add("&fDeaths: &b" + profile.deaths)
        lines.add("")

        return lines
    }

    override fun getUpdateInterval(): Long {
       return 5000
    }

}
