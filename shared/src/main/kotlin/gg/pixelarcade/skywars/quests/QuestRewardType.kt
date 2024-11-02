package gg.pixelarcade.skywars.quests

import lombok.Getter

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 15:27
 */
@Getter
enum class QuestRewardType(val displayName: String, val command: String) {
    TEST("Test", "test")
}
