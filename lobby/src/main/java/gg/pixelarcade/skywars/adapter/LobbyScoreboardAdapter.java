package gg.pixelarcade.skywars.adapter;

import gg.pixelarcade.skywars.scoreboard.api.AssembleAdapter;
import gg.pixelarcade.skywars.scoreboard.api.animation.impl.TitleAnimation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.refinedev.cosmetics.pxCosmetics;
import xyz.refinedev.levels.level.Level;
import xyz.refinedev.levels.pxLevels;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.profile.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 02:32
 */

public class LobbyScoreboardAdapter implements AssembleAdapter {

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

        lines.add("&f");
        lines.add("&6&lYou&7:");
        lines.add("&7■ &fRank: &r" + profile.getHighestRank().getDisplayName());
        lines.add("&7■ &fCoins: &e" + pxCosmetics.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getCoins() + "&6⛃");

        int exp = SharedAPI.getInstance().getLevelHook().getExperience(profile);
        Level level = pxLevels.getInstance().getLevelHandler().getLevelByExp(exp);
        lines.add("&7■ &fLevel: " + level.getFormattedName());

        lines.add("");
        lines.add("&6&lOnline&7:");
        lines.add("&7■ &fAll: &6" + Bukkit.getOnlinePlayers().stream().filter(p -> !SharedAPI.getInstance().getModUtilsHook().isVanished(p.getUniqueId())).count());
        lines.add("&7■ &fPlaying: &60"); //TODO: make this grab all players

        if (Phoenix.getInstance().getRebootHandler().isRebootScheduled()) {
            lines.add("");
            lines.add("&4&lRebooting in&7:");
            lines.add("&7" + Phoenix.getInstance().getRebootHandler().getTimeRemaining());
        }

        lines.add("");
        lines.add("&7pixelarcade.gg");

        return lines;
    }
}
