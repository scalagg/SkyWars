package gg.pixelarcade.skywars.adapter

import gg.pixelarcade.skywars.SkyWarsGame
import gg.pixelarcade.skywars.game.Game
import gg.pixelarcade.skywars.scoreboard.api.animation.impl.TitleAnimation
import gg.pixelarcade.skywars.util.TimeUtils.formatLongIntoMMSS
import gg.scala.cgs.common.player.scoreboard.CgsGameScoreboardRenderer
import gg.scala.cgs.common.states.CgsGameState

import net.evilblock.cubed.scoreboard.ScoreboardAdapter
import org.bukkit.entity.Player
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.collections.ArrayList

//TODO: make a scoreboard stage system

object SkywarsScoreboardAdapter : CgsGameScoreboardRenderer {
    private val plugin: SkyWarsGame? = null
    private val animation = TitleAnimation(
        "SKYWARS",
        "&6&l",
        "&e&l",
        "&f&l"
    )

    override fun getTitle(): String {
        return animation.currentFrame
    }

    override fun render(lines: LinkedList<String>, player: Player, state: CgsGameState) {
        lines.add("&f")

        val game = plugin!!.gameHandler.game
        if (plugin.gameHandler.isSpectating(player.uniqueId)) {
            lines.add("&4&l✖ DEAD! ✖")
            lines.add("&7■ &fGame Time: &6" + renderTime(game))
            lines.add("&7■ &fMap: &6" + plugin.gameHandler.map.name)
            lines.add("&7■ &fAlive: &6" + game.alivePlayers.size + "/" + game.players.size)
        } else {
            lines.add("&7■ &fGame Time: &6" + renderTime(game))
            lines.add("&7■ &fMap: &6" + plugin.gameHandler.map.name)
            lines.add("&7■ &fAlive: &6" + game.alivePlayers.size + "/" + game.players.size)
        }

        lines.add("")
        lines.add("&7pixelarcade.gg")
    }

    fun renderTime(game: Game): String {
        val startedAt = game.startedAt
        val endedAt = game.endedAt

        return if (startedAt == null) "00:00"
        else formatLongIntoMMSS(
            ChronoUnit.SECONDS.between(
                startedAt.toInstant(),
                if (endedAt == null) Instant.now() else endedAt.toInstant()
            )
        )
    }
}
