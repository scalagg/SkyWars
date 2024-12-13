package gg.pixelarcade.skywars

import com.cryptomorin.xseries.XMaterial
import gg.pixelarcade.skywars.gamemode.ranked.SkyWarsDuoInsaneGamemode
import gg.pixelarcade.skywars.gamemode.ranked.SkyWarsDuoNormalGamemode
import gg.pixelarcade.skywars.gamemode.solo.SkyWarsSoloInsaneGamemode
import gg.pixelarcade.skywars.gamemode.solo.SkyWarsSoloNormalGamemode
import gg.pixelarcade.skywars.leaderboard.ranking.TopKillsRankingEntry
import gg.pixelarcade.skywars.leaderboard.ranking.TopWinsRankingEntry
import gg.pixelarcade.skywars.profile.SkyWarsProfile
import gg.pixelarcade.skywars.scoreboard.LobbyScoreboardAdapter
import gg.scala.cgs.common.information.CgsGameGeneralInfo
import gg.scala.cgs.common.instance.CgsServerInstance
import gg.scala.cgs.lobby.gamemode.CgsGameLobby
import gg.scala.cgs.lobby.updater.CgsGameInfoUpdater
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.bungee.BungeeUtil
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*

/**
 * @date 11/1/2024
 * @author Moose1301
 */
object SkyWarsLobbyEngine : CgsGameLobby<SkyWarsProfile>(
    SkyWarsProfile::class
)
{

    override fun getFormattedButton(
        info: CgsServerInstance, player: Player
    ): Button
    {
        return ItemBuilder.of(XMaterial.FIRE_CHARGE)
            .name("${CC.GREEN}${info.internalServerId}")
            .addToLore(
                "${CC.SEC}Participants: ${CC.PRI}${
                    info.gameServerInfo!!.participants.size
                }",
                "${CC.SEC}Status: ${CC.PRI}${
                    info.gameServerInfo!!.state.name
                        .lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                }",
                "",
                "${CC.SEC}Map: ${CC.PRI}${
                    info.gameServerInfo!!.arenaId
                }",
                "${CC.SEC}Mode: ${CC.PRI}${
                    info.gameServerInfo!!.gameMode.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                }",
                "",
                "${CC.GREEN}Click to spectate!"
            )
            .toButton { _, _ ->
                BungeeUtil.sendToServer(player, info.internalServerId)
            }
    }

    override fun getGameInfo(): CgsGameGeneralInfo {
        return SkyWarsGameInfo
    }

    override fun getGameModeButtons() = mutableMapOf(
        12 to ItemBuilder(Material.FISHING_ROD)
            .name("${CC.PRI}Normal Solo")
            .addToLore(
                "${CC.GRAY}Join a game of",
                "${CC.GRAY}Normal Solo SkyWars!",
                "",
                " ${CC.WHITE}In Game: ${CC.PRI}${CgsGameInfoUpdater.gameModeCounts["normal-solo"]}",
                "",
                "${CC.GREEN}Click to join game!"
            )
            .toButton { player, _ ->
                val server = CgsGameInfoUpdater
                    .findAvailableServer(
                        SkyWarsSoloNormalGamemode, "SkyWars"
                    )

                if (server != null)
                {
                    player!!.sendMessage("${CC.SEC}Connecting you to ${CC.PRI}${server.internalServerId}${CC.SEC}...")
                    BungeeUtil.sendToServer(player, server.internalServerId)
                } else
                {
                    player!!.sendMessage("${CC.RED}Bare with us while we find you a game to join!")
                }
            },
        14 to ItemBuilder(Material.FISHING_ROD)
            .name("${CC.PRI}Insane Solo")
            .addToLore(
                "${CC.GRAY}Join a game of",
                "${CC.GRAY}Insane Solo SkyWars!",
                "",
                " ${CC.WHITE}In Game: ${CC.PRI}${CgsGameInfoUpdater.gameModeCounts["insane-solo"]}",
                "",
                "${CC.GREEN}Click to join game!"
            )
            .toButton { player, _ ->
                val server = CgsGameInfoUpdater
                    .findAvailableServer(
                        SkyWarsSoloInsaneGamemode, "SkyWars"
                    )

                if (server != null)
                {
                    player!!.sendMessage("${CC.SEC}Connecting you to ${CC.PRI}${server.internalServerId}${CC.SEC}...")
                    BungeeUtil.sendToServer(player, server.internalServerId)
                } else
                {
                    player!!.sendMessage("${CC.RED}Bare with us while we find you a game to join!")
                }
            },
        19 to ItemBuilder(Material.FISHING_ROD)
            .name("${CC.PRI}Normal Duos")
            .addToLore(
                "${CC.GRAY}Join a game of",
                "${CC.GRAY}Normal Duos SkyWars!",
                "",
                " ${CC.WHITE}In Game: ${CC.PRI}${CgsGameInfoUpdater.gameModeCounts["normal-teams"]}",
                "",
                "${CC.GREEN}Click to join game!"
            )
            .toButton { player, _ ->
                val server = CgsGameInfoUpdater
                    .findAvailableServer(
                        SkyWarsDuoNormalGamemode, "SkyWars"
                    )

                if (server != null)
                {
                    player!!.sendMessage("${CC.SEC}Connecting you to ${CC.PRI}${server.internalServerId}${CC.SEC}...")
                    BungeeUtil.sendToServer(player, server.internalServerId)
                } else
                {
                    player!!.sendMessage("${CC.RED}Bare with us while we find you a game to join!")
                }
            },
        21 to ItemBuilder(Material.FISHING_ROD)
            .name("${CC.PRI}Insane Duos")
            .addToLore(
                "${CC.GRAY}Join a game of",
                "${CC.GRAY}Insane Duos SkyWars!",
                "",
                " ${CC.WHITE}In Game: ${CC.PRI}${CgsGameInfoUpdater.gameModeCounts["insane-teams"]}",
                "",
                "${CC.GREEN}Click to join game!"
            )
            .toButton { player, _ ->
                val server = CgsGameInfoUpdater
                    .findAvailableServer(
                        SkyWarsDuoInsaneGamemode, "SkyWars"
                    )

                if (server != null)
                {
                    player!!.sendMessage("${CC.SEC}Connecting you to ${CC.PRI}${server.internalServerId}${CC.SEC}...")
                    BungeeUtil.sendToServer(player, server.internalServerId)
                } else
                {
                    player!!.sendMessage("${CC.RED}Bare with us while we find you a game to join!")
                }
            },
    )


    override fun getRankingEntries() = mutableListOf(
        TopKillsRankingEntry,
        TopWinsRankingEntry
    )

    override fun getScoreboardAdapter() = LobbyScoreboardAdapter


}
