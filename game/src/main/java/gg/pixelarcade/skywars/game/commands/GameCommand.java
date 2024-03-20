package gg.pixelarcade.skywars.game.commands;

import gg.pixelarcade.skywars.SkyWarsGame;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.game.GameHandler;
import gg.pixelarcade.skywars.map.GameMap;
import gg.pixelarcade.skywars.util.SpigotConstants;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.CommandHelp;
import xyz.refinedev.commands.ConditionFailedException;
import xyz.refinedev.commands.annotation.*;
import xyz.refinedev.phoenix.utils.Color;
import gg.pixelarcade.skywars.game.state.GameType;

@RequiredArgsConstructor
@CommandAlias("game")
public class GameCommand extends BaseCommand {

    private final GameHandler handler = SkyWarsGame.getGameInstance().getGameHandler();

    @Subcommand("join")
    @Syntax("<type>")
    public void join(Player player, @Name("type") GameType type) {
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
    public void leave(Player player) {
        handler.removePlayer(player);

        GameMap map = SkyWarsGame.getGameInstance().getGameHandler().getMap();

        player.sendMessage(Color.translate(SpigotConstants.GAME + "&eYou have left &b"
                + map.getName() + "&e."));
    }

    @Subcommand("forcestart")
    @CommandPermission("skywars.forcestart")
    public void forcestart(Player player) {
        if (handler.getGame().isForceStarted())
            throw new ConditionFailedException("Already force started");

        handler.getGame().setForceStarted(true);
        handler.startCountdown();
    }

    @HelpCommand
    public void doHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }


}
