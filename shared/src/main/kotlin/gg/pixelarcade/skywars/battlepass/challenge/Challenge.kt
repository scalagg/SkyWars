package gg.pixelarcade.skywars.battlepass.challenge


import gg.pixelarcade.skywars.battlepass.BattlePass
import gg.pixelarcade.skywars.battlepass.BattlePassHandler
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat

abstract class Challenge(val id: String,internal var daily: Boolean = false,internal var weekly: Boolean = false) {

    var name: String = "Default name"

    internal var rewardXp: Int = 0

    abstract fun getText(): String
    abstract fun getType(): ChallengeType

    open fun isProgressive(): Boolean {
        return false
    }

    open fun getProgressText(player: Player,battlePass: BattlePass): String {
        return ""
    }

    abstract fun meetsCompletionRequirements(player: Player,battlePass: BattlePass): Boolean

    fun onComplete(player: Player,battlePass: BattlePass) {

        val nextTier = battlePass.getNextTier()
        val formattedExp = NumberFormat.getInstance().format(rewardXp)

        player.sendMessage("${BattlePassHandler.PREFIX}You have completed the ${ChatColor.YELLOW}${this.name} ${ChatColor.GOLD}challenge! ${ChatColor.YELLOW}(${ChatColor.GREEN}+$formattedExp XP${ChatColor.YELLOW})")

        battlePass.completeChallenge(player,this)

        if (battlePass.getNextTier() != nextTier && nextTier != null) {
            var newRewards = false

            if (nextTier.freeReward != null) {
                newRewards = true
            }

            if (nextTier.premiumReward != null && battlePass.isPremium()) {
                newRewards = true
            }

            if (newRewards) {
                player.sendMessage("${BattlePassHandler.PREFIX}You have reached ${ChatColor.GREEN}${ChatColor.BOLD}Tier ${nextTier.number}${ChatColor.GOLD}! You have new rewards waiting to be collected!")
            } else {
                player.sendMessage("${BattlePassHandler.PREFIX}You have reached ${ChatColor.GREEN}${ChatColor.BOLD}Tier ${nextTier.number}${ChatColor.GOLD}!")
            }
        }
    }

    open fun isSetup(): Boolean {
        return true
    }

    override fun equals(other: Any?): Boolean {
        return other is Challenge && other.id == this.id && other.daily == this.daily && other.weekly == this.weekly
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + this.daily.hashCode() + this.weekly.hashCode()
        return result
    }

    companion object {
        internal val DECIMAL_FORMAT = DecimalFormat("#.##").also{
            it.roundingMode = RoundingMode.HALF_UP
        }
    }

}