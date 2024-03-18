package gg.pixelarcade.skywars.cage;

import gg.pixelarcade.skywars.game.state.GameType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author alfie
 * @project SkyWars
 * @date 18/03/2024 / 22:19
 */

@RequiredArgsConstructor
@Data
public class Cage {

    private final String name;
    private final String displayName;
    private final GameType gameType;

}
