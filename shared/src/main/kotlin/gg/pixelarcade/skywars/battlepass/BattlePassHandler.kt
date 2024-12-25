package gg.pixelarcade.skywars.battlepass

import gg.pixelarcade.skywars.SkyWarsShared
import gg.scala.lemon.LemonConstants
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.io.File
import java.util.*

/**
 * @date 12/10/2024
 * @author Moose1301
 */
object BattlePassHandler {
    fun getBattlePassByPlayer(player: Player): BattlePass {
        return SkyWarsShared.instance.getProfile(player).battlePass;
    }

    fun getBattlePassByPlayerId(uuid: UUID): BattlePass {
        return SkyWarsShared.instance.getProfile(uuid).battlePass;
    }

    const val PREFIX = "§7[§c§lBattle Pass§7] §6"
    const val DAILY_CHALLENGES = 16
    const val WEEKLY_CHALLENGES = 16

    val NOT_PREMIUM = "${ChatColor.RED}You don't have access to the Premium Battle Pass challenges! Purchase on our store at ${ChatColor.YELLOW}${ChatColor.BOLD}${ChatColor.UNDERLINE}${LemonConstants.STORE_LINK}${ChatColor.RED}."
}