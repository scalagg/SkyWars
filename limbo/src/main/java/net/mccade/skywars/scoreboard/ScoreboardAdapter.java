package net.mccade.skywars.scoreboard;

import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.scoreboard.api.AssembleAdapter;
import gg.pixelarcade.skywars.scoreboard.api.animation.impl.TitleAnimation;
import lombok.RequiredArgsConstructor;
import net.mccade.skywars.Limbo;
import net.mccade.skywars.util.LimboState;
import net.mccade.skywars.util.StartingState;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class ScoreboardAdapter implements AssembleAdapter {

    private final Limbo plugin;

    private final TitleAnimation animation = new TitleAnimation(
            "SKYWARS",
            "&6&l",
            "&e&l",
            "&f&l"
    );

    @Override
    public String getTitle(Player player) {
        return animation.getCurrentFrame();
    }

    @Override
    public List<String> getLines(Player player) {
        //-- Instance Variables
        List<String> lines = new ArrayList<>();

        //-- Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        lines.add("&7" + dateFormat.format(System.currentTimeMillis()));
        lines.add("");

        //-- Player Count
        int currentPlayers = plugin.getLobbyHandler().getLobby().getPlayers().size();
        int maxPlayers = plugin.getLobbyHandler().getLobby().getGameTeamType().getMaxPlayers();
        lines.add("&fPlayers: &a" + currentPlayers + "/" + maxPlayers);
        lines.add("");

        //-- Waiting
        if (plugin.getLobbyHandler().getLobby().getStartingState() != StartingState.COUNTING) {
            lines.add("&fWaiting...");
            lines.add("");
        } else {
            lines.add("&aStarting in: " + plugin.getLobbyHandler().getLobby().getCountdown().getCountdown() + "s");
            lines.add("");
        }

        String badType = plugin.getLobbyHandler().getLobby().getGameTeamType().name().toLowerCase();
        String goodType = badType.substring(0,1).toUpperCase() + badType.substring(1);

        //-- Map, Mode, Bracket if there is one
        lines.add("&fType: " + "&b&l" + goodType);
        lines.add("&fMode: " + "&b&l" + plugin.getLobbyHandler().getLobby().getGameType().getDisplayName());
        GameType gameType = plugin.getLobbyHandler().getLobby().getGameType();
//        if (gameType == GameType.RANKED) {
//            //lines.add("&fBracket: " + playerBracket"); TODO: Show the player's bracket
//        }
        lines.add("");

        //-- Footer
        lines.add("&eplay.mccade.net");

        return lines;
    }
}
