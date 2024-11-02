package gg.pixelarcade.skywars.scoreboard

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.scoreboard.api.AssembleAdapter
import gg.pixelarcade.skywars.scoreboard.api.animation.impl.TitleAnimation
import org.bukkit.entity.Player
import java.text.SimpleDateFormat

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 02:32
 */
class LobbyScoreboardAdapter : AssembleAdapter {
    private val animation = TitleAnimation(
        "SKYWARS",
        "&6&l",
        "&e&l",
        "&f&l"
    )

    override fun getTitle(player: Player): String {
        return animation.currentFrame
    }

    override fun getLines(player: Player): List<String> {
        //-- Instance Variables

        val lines: MutableList<String> = ArrayList()
        val profile = SkyWarsShared.instance.getProfile(player.uniqueId)

        //-- Date
        val dateFormat = SimpleDateFormat("MM/dd/yy HH:mm:ss")
        lines.add("&7" + dateFormat.format(System.currentTimeMillis()))
        lines.add("")

        //-- Level
        val level = profile.level
        val prestige = profile.getPrestige(level)

        lines.add("&6● &fLevel: &r" + prestige!!.color + "[" + level + "✯]")
        lines.add(profile.getXPBar(prestige))

        lines.add("")
        lines.add("&6● &fWins: &5" + profile.wins)
        lines.add("&6● &fKills: &5" + profile.kills)

        lines.add("")
        lines.add("&6● &fCoins: &6" + profile.coins)

        lines.add("")
        lines.add("&eplay.mccade.net")

        return lines
    }
}
