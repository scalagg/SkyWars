package gg.pixelarcade.skywars.scoreboard

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.scoreboard.api.animation.impl.TitleAnimation
import net.evilblock.cubed.scoreboard.ScoreboardAdapter
import org.bukkit.entity.Player
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 02:32
 */
object LobbyScoreboardAdapter : ScoreboardAdapter() {
    private val animation = TitleAnimation(
        "SKYWARS",
        "&6&l",
        "&e&l",
        "&f&l"
    )

    override fun getLines(lines: LinkedList<String>, player: Player) {
        //-- Instance Variables
        val profile = SkyWarsShared.instance.getProfile(player.uniqueId)

        //-- Date
        val dateFormat = SimpleDateFormat("MM/dd/yy HH:mm:ss")
        lines.add("&7" + dateFormat.format(System.currentTimeMillis()))
        lines.add("")

        //-- Level
        val level = profile.level
        val prestige = profile.getPrestige(level)

        lines.add("&6● &fLevel: &r" + prestige.color + "[" + level + "✯]")
        lines.add(profile.getXPBar(prestige))

        lines.add("")
        lines.add("&6● &fWins: &5" + profile.wins.value)
        lines.add("&6● &fKills: &5" + profile.kills.value)

        lines.add("")
        lines.add("&6● &fCoins: &6" + profile.coins)

        lines.add("")
        lines.add("&eplay.mccade.net")
    }

    override fun getTitle(player: Player): String {
        return animation.currentFrame
    }


}
