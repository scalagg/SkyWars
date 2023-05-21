package xyz.refinedev.skywars.scoreboard;

import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.refinedev.levels.level.Level;
import xyz.refinedev.levels.pxLevels;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.profile.Profile;
import xyz.refinedev.skywars.game.SkyWarsGame;
import xyz.refinedev.skywars.profile.SkyWarsProfile;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.scoreboard.api.AssembleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Creaxx
 * Created At: 5/21/2023
 */

@RequiredArgsConstructor
public class ScoreboardAdapter implements AssembleAdapter {
    private final pxSkyWars plugin;

    @Override
    public String getTitle(Player player) {
        return "I Cri With My Little I";
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> lines = new ArrayList<>();

        SkyWarsProfile profile = plugin.getProfileHandler().getProfile(player.getUniqueId());
        Profile phoenix = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());

        if (plugin.getGameHandler().isInGame(player)) {
            SkyWarsGame game = plugin.getGameHandler().getGame(player);

            lines.add("&f");
            lines.add("&7■ &fMap: &a" + game.getArena().getArenaSchematic().getDisplayName());
            lines.add("&7■ &fAlive: &a" + game.getAlivePlayers().size() + "/" + game.getOriginalPlayers());
            lines.add("&7■ &fKit: &a" + profile.getSelectedKit().getDisplayName());
        } else {
            lines.add("&7■ &fRank: " + phoenix.getHighestRank().getDisplayName());

            int exp = SharedAPI.getInstance().getLevelHook().getExperience(phoenix, "skywars");
            Level level = pxLevels.getInstance().getLevelHandler().getLevelByExp(exp);

            lines.add("&7■ &fLevel: " + level.getFormattedName());
            lines.add(getProgressBar(exp, level.getExperienceNeeded(), 10));
            lines.add("");
            lines.add("&f&lOnline");
            lines.add("&7■ &fAll: &a" + Bukkit.getOnlinePlayers().stream().filter(p -> SharedAPI.getInstance().getModUtilsHook().canSee(phoenix, SharedAPI.getInstance().getProfileHandler().getProfile(p.getUniqueId()))).count());
            lines.add("&7■ &fPlaying: &a" + plugin.getGameHandler().getPlaying());
        }


        return lines;
    }

    public String getProgressBar(int current, int max, int totalBars) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        return Strings.repeat(ChatColor.YELLOW.toString() + '▋', progressBars) + Strings.repeat(ChatColor.GRAY.toString() + '▋', totalBars - progressBars);
    }
}
