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
        SkyWarsProfile profile = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId());

        //-- Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
        lines.add("&7" + dateFormat.format(System.currentTimeMillis()));
        lines.add("");

        //-- Level
        int level = profile.getLevel();
        LevelEnum prestige = profile.getPrestige(level);

        lines.add("&6● &fLevel: &r" + prestige.getColor() + "[" + level + "✯]");
        lines.add(profile.getXPBar(prestige));

        lines.add("");
        lines.add("&6● &fWins: &5" + profile.getWins());
        lines.add("&6● &fKills: &5" + profile.getKills());

        lines.add("");
        lines.add("&6● &fCoins: &6" + profile.getCoins());

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
