package gg.pixelarcade.skywars.battlepass.challenge.impl


import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.battlepass.BattlePass
import gg.pixelarcade.skywars.battlepass.challenge.Challenge
import gg.pixelarcade.skywars.battlepass.challenge.ChallengeType
import gg.pixelarcade.skywars.battlepass.daily.DailyChallengeHandler
import gg.pixelarcade.skywars.battlepass.weekly.WeeklyChallengeHandler
import gg.pixelarcade.skywars.utils.ColorUtils
import net.evilblock.cubed.util.bukkit.Constants
import net.evilblock.cubed.util.bukkit.prompt.NumberPrompt
import net.evilblock.cubed.util.text.ProgressBarBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class GamesPlayedChallenge(id: String, var games: Int) : Challenge(id) {

    override fun getText(): String {
        return "Play ${this.games} Games"
    }

    override fun isProgressive(): Boolean {
        return true
    }

    override fun getProgressText(player: Player, battlePass: BattlePass): String {

        val value = if (this.daily) {
            DailyChallengeHandler.getSession().getProgress(player.uniqueId).getPlays()
        } else if (this.weekly) {
            WeeklyChallengeHandler.getSession().getProgress(player.uniqueId).getPlays()
        } else {
            SkyWarsShared.instance.getProfile(player).gamesPlayed;
        }

        val percentage = ProgressBarBuilder.percentage(value, this.games)

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
            DailyChallengeHandler.getSession().getProgress(player.uniqueId).getPlays() >= games
        } else if (weekly) {
            WeeklyChallengeHandler.getSession().getProgress(player.uniqueId).getPlays() >= games
        } else {
            SkyWarsShared.instance.getProfile(player).gamesPlayed >= games
        }
    }

    override fun getType(): ChallengeType {
        return GamesPlayedChallengeType
    }

    object GamesPlayedChallengeType : ChallengeType {

        override fun getName(): String {
            return "Play Games Players"
        }

        override fun getDescription(): String {
            return "Play a given amount of games"
        }

        override fun getIcon(): ItemStack {
            return ItemStack(Material.DIAMOND_SWORD)
        }

        override fun startSetupPrompt(player: Player, id: String, lambda: (Challenge) -> Unit) {
            NumberPrompt().acceptInput { number ->
                assert(number.toInt() > 0)
                lambda.invoke(GamesPlayedChallenge(id, number.toInt()))
            }.start(player)
        }

    }

}