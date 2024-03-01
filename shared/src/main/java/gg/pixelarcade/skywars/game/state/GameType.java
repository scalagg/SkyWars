package gg.pixelarcade.skywars.game.state;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author puugz
 * @since Mar 01, 2024
 */
@Getter
@RequiredArgsConstructor
public enum GameType {

    NORMAL("Normal"),
    INSANE("Insane"),
    RANKED("Ranked");

    private final String displayName;

}
