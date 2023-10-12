package gg.pixelarcade.skywars.achievement;

import org.bukkit.Bukkit;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;

/**
 * @author Creaxx
 * Created At: 5/8/2023
 */
public class AchievementHandler {

    public void giveAchievement(SkyWarsProfile profile, Achievement achievement) {
        profile.getOwnedAchievements().add(achievement);
        profile.save();

        for (String command : achievement.getCommands()) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("<player>", profile.getName()));
        }
    }

}
