package xyz.refinedev.skywars.game.state;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.game.state
 * @date 26/09/2023 / 20:47
 */

@RequiredArgsConstructor
@Getter
public enum GameType {

    CASUAL_SOLO(12, false),
    CASUAL_DOUBLES(24, false),
    RANKED_SOLO(12, true),
    RANKED_DOUBLES(24, true);

    private final int maxPlayers;
    private final boolean ranked;

}
