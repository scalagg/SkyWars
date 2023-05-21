package xyz.refinedev.skywars.game.state;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Creaxx
 * Created At: 5/13/2023
 */

@Getter
@RequiredArgsConstructor
public enum GameType {

    RANKED_SOLO("Solo", 4,true),
    RANKED_DOUBLES("Doubles", 8, true),
    CASUAL_SOLO("Casual Solo", 16, false),
    CASUAL_DOUBLES("Casual Doubles", 32, false)
    ;


    private final String displayName;
    private final int playerLimit;
    private final boolean ranked;
}
