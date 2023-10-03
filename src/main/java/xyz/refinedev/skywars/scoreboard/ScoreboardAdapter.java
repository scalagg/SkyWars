package xyz.refinedev.skywars.scoreboard;

import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.refinedev.cosmetics.pxCosmetics;
import xyz.refinedev.levels.level.Level;
import xyz.refinedev.levels.pxLevels;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.profile.Profile;
import xyz.refinedev.phoenix.utils.DateUtils;
import xyz.refinedev.phoenix.utils.StringUtils;
import xyz.refinedev.phoenix.utils.Utilities;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.profile.SkyWarsProfile;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.scoreboard.api.AssembleAdapter;
import xyz.refinedev.skywars.scoreboard.api.animation.impl.TitleAnimation;
import xyz.refinedev.skywars.utils.TimeUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Creaxx
 * Created At: 5/21/2023
 */

@RequiredArgsConstructor
public class ScoreboardAdapter implements AssembleAdapter {

    private final pxSkyWars plugin;
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

        Profile profile = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());

        SkyWarsGame game = plugin.getGameHandler().getGame(player);
        if (game != null && profile.hasMetadata("inGame")) {
            if (game.isSpectating(player.getUniqueId())) {
                lines.add("&f");
                lines.add("&4&lYou Died!");
                lines.add("&7■ &fGame Time: &6" + renderTime(game));
                lines.add("&7■ &fMap: &6" + game.getArena().getArenaSchematic().getDisplayName());
                lines.add("&7■ &fAlive: &6" + game.getAlivePlayers().size() + "/" + game.getPlayers().size());
            } else {
                lines.add("&f");
                lines.add("&7■ &fGame Time: &6" + renderTime(game));
                lines.add("&7■ &fMap: &6" + game.getArena().getArenaSchematic().getDisplayName());
                lines.add("&7■ &fAlive: &6" + game.getAlivePlayers().size() + "/" + game.getPlayers().size());
            }
        } else {
            if (profile.hasMetadata("modmode")) {
                lines.add("&f");
                lines.add("&6&lStaff&7:");
                lines.add("&7■ &fVanish: &6" + profile.isVanished());
            }

            lines.add("&f");
            lines.add("&6&lYou&7:");
            lines.add("&7■ &fRank: " + profile.getHighestRank().getDisplayName());
            lines.add("&7■ &fCoins: &e" + pxCosmetics.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getCoins() + "&6⛃");

            int exp = SharedAPI.getInstance().getLevelHook().getExperience(profile);
            Level level = pxLevels.getInstance().getLevelHandler().getLevelByExp(exp);
            lines.add("&7■ &fLevel: " + level.getFormattedName());

            lines.add("");
            lines.add("&6&lOnline&7:");
            lines.add("&7■ &fAll: &60");
            lines.add("&7■ &fPlaying: &60");
        }

        if (Phoenix.getInstance().getRebootHandler().isRebootScheduled()) {
            lines.add("");
            lines.add("&7■ &c&lRebooting in&7:");
            lines.add("&7 " + Phoenix.getInstance().getRebootHandler().getTimeRemaining());
        }

        lines.add("");
        lines.add("&7pixelarcade.gg");

        return lines;
    }

    public String renderTime(SkyWarsGame game) {
        Date startedAt = game.getStartedAt();
        Date endedAt = game.getEndedAt();

        if (startedAt == null) {
            return "00:00";
        } else {
            return TimeUtils.formatLongIntoMMSS(ChronoUnit.SECONDS.between(
                    startedAt.toInstant(),
                    endedAt == null ? Instant.now() : endedAt.toInstant()
            ));
        }
    }

}
