package gg.pixelarcade.skywars.quests;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.quests.impl.WinADoublesGameQuest;
import gg.pixelarcade.skywars.quests.impl.WinASoloGameQuest;
import lombok.Getter;

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
        quests.add(new WinADoublesGameQuest());
    }

    public Quest getQuest(String id) {
        return quests.stream()
                .filter(quest -> quest.getId().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }

    public Quest getRandomQuest() {
        return quests.get(ThreadLocalRandom.current().nextInt(0, quests.size()));
    }

}
