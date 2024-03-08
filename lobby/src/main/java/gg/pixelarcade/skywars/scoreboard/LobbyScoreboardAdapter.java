package gg.pixelarcade.skywars.scoreboard;

import gg.pixelarcade.skywars.SkyWarsLobby;
import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.leveling.enums.LevelEnum;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

        //-- Instance Variables
        List<String> lines = new ArrayList<>();

        //-- Date
        DateFormat dateFormat = new SimpleDateFormat("MM,dd,yy");
        Date date = new Date();
        lines.add("");
        lines.add("&7" + dateFormat.format(date).replace(",", "/"));
        lines.add("");

        //-- Level
        int level = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getLevel();
        LevelEnum prestige = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getPrestige(level);

        lines.add("&6■ &fLevel: &r" + prestige.getColor() + "[" + level + "✯]");
        lines.add(SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getXPBar(prestige));

        lines.add("");
        lines.add("&6■ &fWins: &a" + SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getWins());
        lines.add("&6■ &fKills: &a" + SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getKills());

        lines.add("");
        lines.add("&6■ &fCoins: &6" + SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getCoins());

        if (Phoenix.getInstance().getRebootHandler().isRebootScheduled()) {
            lines.add("");
            lines.add("&4&lRebooting in&7:");
            lines.add("&7" + Phoenix.getInstance().getRebootHandler().getTimeRemaining());
        }

        lines.add("");
        lines.add("&eplay.mccade.net");

        return lines;
    }
}
