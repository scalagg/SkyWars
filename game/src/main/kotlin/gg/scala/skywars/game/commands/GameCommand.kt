package gg.scala.skywars.game.commands

import gg.scala.skywars.SkyWarsGame
import gg.scala.skywars.game.state.GameType
import gg.scala.skywars.util.SpigotConstants
import gg.scala.commons.acf.CommandHelp
import gg.scala.commons.acf.ConditionFailedException
import gg.scala.commons.acf.annotation.*
import gg.scala.commons.annotations.commands.AutoRegister
import gg.scala.commons.command.ScalaCommand

import net.evilblock.cubed.util.Color
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@AutoRegister
@CommandAlias("game")
class GameCommand : ScalaCommand() {
    private val handler = SkyWarsGame.gameInstance.gameHandler

    @Subcommand("join")
    @Syntax("<type>")
    fun join(player: Player?, @Name("type") type: GameType?) {
//        Tasks.runAsync(() -> {
//            if (plugin.getGameHandler().isPlayingGame(player.getUniqueId())) {
//                throw new ConditionFailedException("Already in Game");
//            }
//
//            Game game = plugin.getGameHandler().getAvailableGame(player, type);
//            Party party = pxParty.getInstance().getPartyHandler().getByPlayer(player.getUniqueId());
//
//            if (party != null) {
//                if (!party.isLeader(player.getUniqueId())) {
//                    player.sendMessage(Color.translate("&cYou must be the leader of the party to join a game."));
//                    return;
//                }
//
//                PacketHandler.send(new PartyWarpPacket(SharedAPI.getInstance().getNetworkHandler().getServerName(), party.getMembers()));
//
//                Tasks.runLater(() -> {
//                    for (UUID uuid : party.getMembersWithLeader()) {
//                        Player member = Bukkit.getPlayer(uuid);
//                        if (member == null)
//                            continue;
//
//                        game.addPlayer(member);
//                    }
//                }, 20L);
//
//            } else game.addPlayer(player);
//        });
    }

    @Subcommand("leave")
    fun leave(player: Player) {
        handler.removePlayer(player)

        val map = SkyWarsGame.gameInstance.gameHandler.map

        player.sendMessage(
            Color.translate(
                (_root_ide_package_.gg.scala.skywars.util.SpigotConstants.GAME + "&eYou have left &b"
                        + map.name + "&e.")
            )
        )
    }

    @Subcommand("forcestart")
    @CommandPermission("skywars.forcestart")
    fun forcestart(player: Player?) {
        if (handler.game.forceStarted) throw ConditionFailedException("Already force started")

        handler.game.forceStarted = true
        handler.startCountdown()
    }

    @HelpCommand
    fun doHelp(sender: CommandSender?, help: CommandHelp) {
        help.showHelp()
    }
}
