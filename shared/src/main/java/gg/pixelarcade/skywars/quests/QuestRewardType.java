package gg.pixelarcade.skywars.quests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author alfie
 * @project SkyWars
 * @date 19/03/2024 / 15:27
 */

@RequiredArgsConstructor
@Getter
public enum QuestRewardType {

    TEST("Test", "test");

    private final String displayName;
    private final String command;

}
