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

    RANKED_SOLO("Solo", 4),
    RANKED_DOUBLES("Doubles", 8),
    CASUAL_SOLO("Casual Solo", 16),
    CASUAL_DOUBLES("Casual Doubles", 32)
    ;

    private final String displayName;
    private final int playerLimit;
}
