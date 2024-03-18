package gg.pixelarcade.skywars.achievement;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import gg.pixelarcade.skywars.util.SpigotConstants;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.UUID;

public interface Achievement extends Listener {
    String getId();
    String getName();
    String[] getDescription();

    int getGoal();

    default void onAchieve(UUID player) {}

    default boolean isAchieved(UUID player) {
        SkyWarsProfile profile = getProfile(player);
        return profile.getAchievements().get(getId()) != null && profile.getAchievements().get(getId()) == -1;
    }

    default int getProgress(UUID player) {
        SkyWarsProfile profile = getProfile(player);
        return profile.getAchievements().get(getId()) == null ? 0 : profile.getAchievements().get(getId());
    }

    default void increaseProgress(UUID player) {
        SkyWarsProfile profile = getProfile(player);
        profile.getAchievements().put(getId(), getProgress(player) + 1);

        if (getProgress(player) >= getGoal()) {
            onAchieve(player);
            profile.getAchievements().put(getId(), -1);
            profile.save();
        }
    }

    default SkyWarsProfile getProfile(Player player) {
        return SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId());
    }

    default SkyWarsProfile getProfile(UUID uuid) {
        return SkyWarsShared.getInstance().getProfileHandler().getProfile(uuid);
    }

    default boolean isGameServer() {
        return SpigotConstants.getThisServer().getServerGroup().equalsIgnoreCase("GAME-SERVER");
    }
}
