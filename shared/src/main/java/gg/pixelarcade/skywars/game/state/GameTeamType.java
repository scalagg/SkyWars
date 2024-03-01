package gg.pixelarcade.skywars.game.state;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author puugz
 * @since Mar 01, 2024
 */
@Getter
@AllArgsConstructor
public enum GameTeamType {

    SOLO(12),
    DOUBLES(24);

    private final int maxPlayers;

}
