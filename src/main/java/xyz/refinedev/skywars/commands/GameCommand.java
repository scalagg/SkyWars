package xyz.refinedev.skywars.commands;

import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.CommandHelp;
import xyz.refinedev.commands.ConditionFailedException;
import xyz.refinedev.commands.annotation.CommandAlias;
import xyz.refinedev.commands.annotation.Name;
import xyz.refinedev.commands.annotation.Subcommand;
import xyz.refinedev.commands.annotation.Syntax;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.utils.SpigotConstants;

/**
 * @author Creaxx
 * Created At: 5/21/2023
 */

@RequiredArgsConstructor
@CommandAlias("game")
public class GameCommand extends BaseCommand {
    private final pxSkyWars plugin;

    @Subcommand("join")
    @Syntax("<type>")
    public void join(Player player, @Name("type") GameType type) {
        if (plugin.getGameHandler().isInGame(player)) {
            throw new ConditionFailedException("alr in game");
        }

        SkyWarsGame game = plugin.getGameHandler().getAvailableGame(type);
        game.addPlayer(player);

        player.sendMessage(SpigotConstants.GAME + "&eYou have joined &b" + game.getArena().getArenaSchematic().getDisplayName() + "&e.");
    }

    @Subcommand("leave")
    public void leave(Player player) {
        if (!plugin.getGameHandler().isInGame(player)) {
            throw new ConditionFailedException("not in game");
        }

        SkyWarsGame game = plugin.getGameHandler().getGame(player);
        game.removePlayer(player);

        player.sendMessage(SpigotConstants.GAME + "&eYou have left &b" + game.getArena().getArenaSchematic().getDisplayName() + "&e.");
    }
    @Subcommand("help")
    @Syntax("[page]")
    public void doHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }


}
