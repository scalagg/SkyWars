package xyz.refinedev.skywars.commands;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.ConditionFailedException;
import xyz.refinedev.commands.annotation.Name;
import xyz.refinedev.commands.annotation.Subcommand;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.pxSkyWars;

/**
 * @author Creaxx
 * Created At: 5/21/2023
 */

@RequiredArgsConstructor
public class GameCommand extends BaseCommand {
    private final pxSkyWars plugin;

    @Subcommand("join")
    public void join(Player player, @Name("type") GameType type) {
        if (plugin.getGameHandler().isInGame(player)) {
            throw new ConditionFailedException("alr in game");
        }

        SkyWarsGame game = plugin.getGameHandler().getAvailableGame(type);
        game.addPlayer(player);

        player.sendMessage("added to game");
    }

}
