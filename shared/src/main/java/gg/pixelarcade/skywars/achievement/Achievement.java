package gg.pixelarcade.skywars.achievement;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import gg.pixelarcade.skywars.util.SpigotConstants;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.UUID;

public interface Achievement extends Listener {
    /**
     * Get the id of the achievement
     *
     * @return the id of the achievement
     */
    String getId();

    /**
     * Get the name of the achievement
     *
     * @return the name of the achievement
     */
    String getName();

    /**
     * Get the description of the achievement
     *
     * @return the description of the achievement
     */
    String[] getDescription();

    /**
     * Get the goal (amount of progress needed) of the achievement
     *
     * @return the goal of the achievement
     */
    int getGoal();

    /**
     * Called when the achievement is achieved
     * @param player the player who achieved the achievement
     */
    default void onAchieve(UUID player) {}

    /**
     * Check if the achievement is achieved
     *
     * @param player the player to check
     * @return if the achievement is achieved
     */
    default boolean isAchieved(UUID player) {
        SkyWarsProfile profile = getProfile(player);
        return profile.getAchievements().get(getId()) != null && profile.getAchievements().get(getId()) == -1;
    }

    /**
     * Get the progress of the achievement
     *
     * @param player the player to get the progress of
     * @return the progress of the achievement
     */
    default int getProgress(UUID player) {
        SkyWarsProfile profile = getProfile(player);
        return profile.getAchievements().get(getId()) == null ? 0 : profile.getAchievements().get(getId());
    }

    /**
     * Increase the progress of the achievement
     *
     * @param player the player to increase the progress of
     */
    default void increaseProgress(UUID player) {
        SkyWarsProfile profile = getProfile(player);
        profile.getAchievements().put(getId(), getProgress(player) + 1);

        if (getProgress(player) >= getGoal()) {
            onAchieve(player);
            profile.getAchievements().put(getId(), -1);
            profile.save();
        }
    }

    /**
     * Reset the progress of the achievement
     *
     * @param player the player to reset the progress of
     */
    default void resetProgress(UUID player) {
        SkyWarsProfile profile = getProfile(player);
        profile.getAchievements().put(getId(), 0);
        profile.save();
    }

    /**
     * Get the skywars profile of a player
     * @param player the player to get the profile of
     * @return the skywars profile of the player
     */
    default SkyWarsProfile getProfile(Player player) {
        return SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId());
    }

    /**
     * Get the skywars profile of a player
     * @param uuid the uuid of the player to get the profile of
     * @return the skywars profile of the player
     */
    default SkyWarsProfile getProfile(UUID uuid) {
        return SkyWarsShared.getInstance().getProfileHandler().getProfile(uuid);
    }

    /**
     * Check if the current server is a game server
     * @return if the current server is a game server
     */
    @Deprecated
    default boolean isGameServer() {
        return SpigotConstants.getThisServer().getServerGroup().equalsIgnoreCase("GAME-SERVER");
    }
}
