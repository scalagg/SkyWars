package gg.pixelarcade.skywars.scoreboard;

import gg.pixelarcade.skywars.SkyWarsGame;
import gg.pixelarcade.skywars.game.Game;
import gg.pixelarcade.skywars.scoreboard.api.AssembleAdapter;
import gg.pixelarcade.skywars.scoreboard.api.animation.impl.TitleAnimation;
import gg.pixelarcade.skywars.util.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.profile.Profile;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO: make a scoreboard stage system

@RequiredArgsConstructor
public class ScoreboardAdapter implements AssembleAdapter {

    private final SkyWarsGame plugin;
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
        List<String> lines = new ArrayList<>();
        lines.add("&f");

        Profile profile = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());
        Game game = plugin.getGameHandler().getGame();
        if (profile.hasMetadata("inGame") && plugin.getGameHandler().isSpectating(player.getUniqueId())) {
            lines.add("&4&l✖ DEAD! ✖");
            lines.add("&7■ &fGame Time: &6" + renderTime(game));
            lines.add("&7■ &fMap: &6" + plugin.getGameMapHandler().getGameMap().getName());
            lines.add("&7■ &fAlive: &6" + game.getAlivePlayers().size() + "/" + game.getPlayers().size());
        } else {
            lines.add("&7■ &fGame Time: &6" + renderTime(game));
            lines.add("&7■ &fMap: &6" + plugin.getGameMapHandler().getGameMap().getName());
            lines.add("&7■ &fAlive: &6" + game.getAlivePlayers().size() + "/" + game.getPlayers().size());
        }

        lines.add("");
        lines.add("&7pixelarcade.gg");

        return lines;
    }

    public String renderTime(Game game) {
        Date startedAt = game.getStartedAt();
        Date endedAt = game.getEndedAt();

        if (startedAt == null)
            return "00:00";
        else return TimeUtils.formatLongIntoMMSS(ChronoUnit.SECONDS.between(
                startedAt.toInstant(),
                endedAt == null ? Instant.now() : endedAt.toInstant())
        );
    }

}
