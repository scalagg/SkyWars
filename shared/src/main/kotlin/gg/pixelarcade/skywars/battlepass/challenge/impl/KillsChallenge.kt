package gg.pixelarcade.skywars.battlepass.challenge.impl


import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.battlepass.BattlePass
import gg.pixelarcade.skywars.battlepass.challenge.Challenge
import gg.pixelarcade.skywars.battlepass.challenge.ChallengeType
import gg.pixelarcade.skywars.battlepass.daily.DailyChallengeHandler
import gg.pixelarcade.skywars.utils.ColorUtils
import net.evilblock.cubed.util.bukkit.Constants
import net.evilblock.cubed.util.bukkit.prompt.NumberPrompt
import net.evilblock.cubed.util.text.ProgressBarBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class KillsChallenge(id: String, var kills: Int) : Challenge(id) {

    override fun getText(): String {
        return "Kill ${this.kills} players in PvP"
    }

    override fun isProgressive(): Boolean {
        return true
    }

    override fun getProgressText(player: Player, battlePass: BattlePass): String {

        val value = if (this.daily) {
            DailyChallengeHandler.getSession().getProgress(player.uniqueId).getKills()
        } else if (this.weekly) {
            WeeklyDailyChallengeHandler.getSession().getProgress(player.uniqueId).getKills()
        } else {
            SkyWarsShared.instance.getProfile(player).kills.value
        }

        val percentage = ProgressBarBuilder.percentage(value, this.kills)

        val progressColor = ColorUtils.colorPercentage(percentage)
        val progressBar = ProgressBarBuilder.DEFAULT.build(percentage)

        return "${ChatColor.GRAY}${Constants.THICK_VERTICAL_LINE}$progressBar${ChatColor.GRAY}${Constants.THICK_VERTICAL_LINE} ${ChatColor.GRAY}($progressColor${
            DECIMAL_FORMAT.format(
                percentage
            )
        }%${ChatColor.GRAY})"
    }

    override fun meetsCompletionRequirements(player: Player, battlePass: BattlePass): Boolean {
        return if (daily) {
            DailyChallengeHandler.getSession().getProgress(player.uniqueId).getKills() >= kills
        } else if (weekly) {
            WeeklyChallengeHandler.getSession().getProgress(player.uniqueId).getKills() >= kills
        } else {
            SkyWarsShared.instance.getProfile(player).kills.value >= kills
        }
    }

    override fun getType(): ChallengeType {
        return KillsChallengeType
    }

    object KillsChallengeType : ChallengeType {

        override fun getName(): String {
            return "Kill Players"
        }

        override fun getDescription(): String {
            return "Kill a given amount of players"
        }

        override fun getIcon(): ItemStack {
            return ItemStack(Material.SKULL_ITEM)
        }

        override fun startSetupPrompt(player: Player, id: String, lambda: (Challenge) -> Unit) {
            NumberPrompt().acceptInput { number ->
                assert(number.toInt() > 0)
                lambda.invoke(KillsChallenge(id, number.toInt()))
            }.start(player)
        }

    }

}