package gg.pixelarcade.skywars.achievement;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter @SuppressWarnings("unchecked")
public class AchievementHandler {

    private final List<Achievement> achievements;

    public AchievementHandler() {
        this.achievements = new ArrayList<>();
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
