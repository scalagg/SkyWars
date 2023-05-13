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

    SOLO("Solo", 16),
    DOUBLES("Doubles", 24);

    private final String displayName;
    private final int playerLimit;
}
