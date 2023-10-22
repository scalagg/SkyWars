package gg.pixelarcade.skywars.game.commands;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.game.SkyWarsGame;
import gg.pixelarcade.skywars.utils.SpigotConstants;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.CommandHelp;
import xyz.refinedev.commands.ConditionFailedException;
import xyz.refinedev.commands.annotation.*;
import xyz.refinedev.party.packet.PartyWarpPacket;
import xyz.refinedev.party.party.Party;
import xyz.refinedev.party.pxParty;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.packet.api.PacketHandler;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.general.Tasks;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.UUID;

@RequiredArgsConstructor
@CommandAlias("game")
public class GameCommand extends BaseCommand {

    private final SkyWarsPlugin plugin;

    @Subcommand("join")
    @Syntax("<type>")
    public void join(Player player, @Name("type") GameType type) {
        if (plugin.getGameHandler().isPlayingGame(player.getUniqueId())) {
            throw new ConditionFailedException("alr in game");
        }

        SkyWarsGame game = plugin.getGameHandler().getAvailableGame(player, type);
        Party party = pxParty.getInstance().getPartyHandler().getByPlayer(player.getUniqueId());

        if (party != null) {
            if (!party.isLeader(player.getUniqueId())) {
                player.sendMessage(Color.translate("&cYou must be the leader of the party to join a game."));
                return;
            }

            PacketHandler.send(new PartyWarpPacket(SharedAPI.getInstance().getNetworkHandler().getServerName(), party.getMembers()));

            Tasks.runLater(() -> {
                for (UUID uuid : party.getMembersWithLeader()) {
                    Player member = Bukkit.getPlayer(uuid);
                    if (member == null)
                        continue;

                    game.addPlayer(member);
                }
            }, 20L);

        } else game.addPlayer(player);
    }

    @Subcommand("leave")
    public void leave(Player player) {
        if (!plugin.getGameHandler().isPlayingGame(player.getUniqueId())) {
            throw new ConditionFailedException("Not currently in a game");
        }

        SkyWarsGame game = plugin.getGameHandler().getGame(player.getUniqueId());
        game.removePlayer(player);

        player.sendMessage(Color.translate(SpigotConstants.GAME + "&eYou have left &b" + game.getArena().getArenaSchematic().getDisplayName() + "&e."));
    }

    @Subcommand("forcestart")
    @CommandPermission("skywars.forcestart")
    public void forcestart(Player player) {
        if (!plugin.getGameHandler().isPlayingGame(player.getUniqueId())) {
            throw new ConditionFailedException("Not currently in a game");
        }

        SkyWarsGame game = plugin.getGameHandler().getGame(player.getUniqueId());
        if (game.isForceStarted())
            throw new ConditionFailedException("Already force started");

        game.setForceStarted(true);
        game.startCountdown();
    }

    @Subcommand("help")
    @Syntax("[page]")
    public void doHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }


}
