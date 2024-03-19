package gg.pixelarcade.skywars.quests;

import gg.pixelarcade.skywars.quests.impl.GetTwentyKillsQuest;
import gg.pixelarcade.skywars.quests.impl.WinASoloGameQuest;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 15:29
 */

@Getter
public class QuestHandler {

    private final List<Quest> quests = new ArrayList<>();

    public QuestHandler() {
        // Register Quests Here
        quests.add(new WinASoloGameQuest());
        quests.add(new GetTwentyKillsQuest());
    }

    public Quest getQuest(String id) {
        return quests.stream()
                .filter(quest -> quest.getId().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }

    public Quest getQuest(int priority) {
        return quests.stream()
                .filter(quest -> quest.getPriority() == priority)
                .findFirst().orElse(null);
    }

    public Quest getRandomQuest() {
        return quests.get(ThreadLocalRandom.current().nextInt(0, quests.size()));
    }

}
