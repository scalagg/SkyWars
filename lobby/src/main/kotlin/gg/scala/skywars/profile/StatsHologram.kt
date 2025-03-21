package gg.scala.skywars.profile

import net.evilblock.cubed.entity.hologram.personalized.PersonalizedHologramEntity
import org.bukkit.Location
import org.bukkit.entity.Player

class StatsHologram(location: Location, val profile: SkyWarsProfile) : PersonalizedHologramEntity(location) {

    override fun getNewLines(player: Player): List<String> {
        val level = profile.getPrestige(profile.level)

        val lines = mutableListOf<String>()
        lines.add("&6&nPersonal Stats")
        lines.add("")
        lines.add("&fLevel: ${level.color} ${profile.level} ${level.star}")
        lines.add("&fProgress: &b${profile.xp}&7/&a${level.xpPerLevel}")
        lines.add("&fWins: &6${profile.wins.value}")
        lines.add("&fKills: &6${ profile.kills.value}")
        lines.add("&fDeaths: &6${profile.deaths.value}")
        lines.add("")

        return lines
    }

    override fun getUpdateInterval(): Long {
       return 5000
    }

}
