package gg.pixelarcade.skywars.achievement;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.achievement.impl.*;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter @SuppressWarnings("unchecked")
public class AchievementHandler {
    private final List<Achievement> achievements;

    public AchievementHandler(SkyWarsShared plugin) {
        this.achievements = Arrays.asList(
                new ChickenDinnerAchievement(), new NoWeaponAchievement(), new TacticalTacticianAchievement(),
                new FirstBloodAchievement()
        );

        this.achievements.forEach(achievement ->
                plugin.getPlugin().getServer().getPluginManager().registerEvents(achievement, plugin.getPlugin())
        );
    }

    public Achievement getAchievement(String id) {
        return this.achievements.stream().filter(achievement -> achievement.getId().equals(id)).findFirst().orElse(null);
    }

    public <T extends Achievement> T getAchievement(Class<T> clazz) {
        return (T) this.achievements.stream()
                .filter(achievement -> achievement.getClass().equals(clazz))
                .findFirst().orElse(null);
    }
}
