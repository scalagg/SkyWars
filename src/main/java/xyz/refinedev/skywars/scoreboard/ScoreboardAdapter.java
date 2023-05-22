package xyz.refinedev.skywars.scoreboard;

import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.refinedev.cosmetics.pxCosmetics;
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
        return "&b&lStellarMC &cVenus";
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> lines = new ArrayList<>();

        SkyWarsProfile profile = plugin.getProfileHandler().getProfile(player.getUniqueId());
        Profile phoenix = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());

        if (plugin.getGameHandler().isInGame(player)) {
            SkyWarsGame game = plugin.getGameHandler().getGame(player);

            lines.add("&f");
            lines.add("&7■ &fMap: &3" + game.getArena().getArenaSchematic().getDisplayName());
            lines.add("&7■ &fAlive: &3" + game.getAlivePlayers().size() + "/" + game.getOriginalPlayers());
            lines.add("&7■ &fKit: &3" + profile.getSelectedKit().getDisplayName());
            lines.add("");
            lines.add("&7stellarmc.gg");
        } else {
            lines.add("&f");
            lines.add("&b&lYou&7:");
            lines.add("&7■ &fRank: " + phoenix.getHighestRank().getDisplayName());
            Profile phoenixprofile = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());
            lines.add("&7■ &fCoins: &3" + pxCosmetics.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getCoins() + "&e⛃");
            if(phoenixprofile.getDisguiseData().isDisguised()) {
                lines.add("&7■ &c&lDisguised as&7:");
                lines.add("&7 " + phoenixprofile.getDisguiseData().getDisguiseName());
            }
            int exp = SharedAPI.getInstance().getLevelHook().getExperience(phoenix, "skywars");
            Level level = pxLevels.getInstance().getLevelHandler().getLevelByExp(exp);

            lines.add("&7■ &fLevel: " + level.getFormattedName());
            lines.add(getProgressBar(exp, level.getExperienceNeeded(), 10) + " &3" + exp + "/" + level.getExperienceNeeded());
            lines.add("");
            lines.add("&b&lOnline&7:");
            lines.add("&7■ &fAll: &3" + Bukkit.getOnlinePlayers().stream().filter(p -> SharedAPI.getInstance().getModUtilsHook().canSee(phoenix, SharedAPI.getInstance().getProfileHandler().getProfile(p.getUniqueId()))).count());
            lines.add("&7■ &fPlaying: &3" + plugin.getGameHandler().getPlaying());
            lines.add("");
            lines.add("&7stellarmc.gg");
        }


        return lines;
    }

    public String getProgressBar(int current, int max, int totalBars) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        return Strings.repeat(ChatColor.YELLOW.toString() + '▋', progressBars) + Strings.repeat(ChatColor.GRAY.toString() + '▋', totalBars - progressBars);
    }
}
