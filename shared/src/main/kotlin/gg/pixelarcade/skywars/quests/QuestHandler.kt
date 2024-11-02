package gg.pixelarcade.skywars.quests

import gg.pixelarcade.skywars.quests.impl.WinADoublesGameQuest
import gg.pixelarcade.skywars.quests.impl.WinASoloGameQuest
import lombok.Getter
import java.util.concurrent.ThreadLocalRandom

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 15:29
 */
@Getter
class QuestHandler {
    private val quests: MutableList<Quest> = ArrayList()

    init {
        // Register Quests Here
        quests.add(WinASoloGameQuest())
        quests.add(WinADoublesGameQuest())
    }

    fun getQuest(id: String): Quest? {
        return quests.stream()
            .filter { quest: Quest -> quest.id.equals(id, ignoreCase = true) }
            .findFirst().orElse(null)
    }

    val randomQuest: Quest
        get() = quests[ThreadLocalRandom.current().nextInt(0, quests.size)]
}
