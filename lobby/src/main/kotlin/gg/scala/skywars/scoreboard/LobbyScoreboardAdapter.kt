package gg.scala.skywars.scoreboard

import gg.scala.skywars.SkyWarsShared
import gg.scala.skywars.scoreboard.api.animation.impl.TitleAnimation
import gg.scala.skywars.utils.ScoreboardTitleService
import net.evilblock.cubed.scoreboard.ScoreboardAdapter
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.Constants
import org.bukkit.entity.Player
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 02:32
 */
object LobbyScoreboardAdapter : ScoreboardAdapter() {

    override fun getLines(lines: LinkedList<String>, player: Player) {
        //-- Instance Variables
        val profile = SkyWarsShared.instance.getProfile(player.uniqueId)

        //-- Date
        val dateFormat = SimpleDateFormat("MM/dd/yy")
        lines.add("&7" + dateFormat.format(System.currentTimeMillis()))
        lines.add("")

        //-- Level
        val level = profile.level
        val prestige = profile.getPrestige(level)

        lines.add("&7${Constants.THIN_VERTICAL_LINE} &fLevel: &r${prestige.color}[$level${prestige.star}]")
        lines.add(profile.getXPBar(prestige))

        lines.add("")
        lines.add("&7${Constants.THIN_VERTICAL_LINE} &fWins: ${CC.PRI}" + profile.wins.value)
        lines.add("&7${Constants.THIN_VERTICAL_LINE} &fKills: ${CC.PRI}" + profile.kills.value)

        lines.add("")
        lines.add("&7${Constants.THIN_VERTICAL_LINE} &fCoins: ${CC.GREEN}" + profile.coins)
        lines.add("&7${Constants.THIN_VERTICAL_LINE} &fSouls: &b" + profile.souls)

        lines.add("")
        lines.add("&7scala.gg")
    }

    override fun getTitle(player: Player): String {
        return ScoreboardTitleService.getCurrentTitle()
    }


}
