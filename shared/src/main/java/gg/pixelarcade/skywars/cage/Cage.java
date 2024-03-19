package gg.pixelarcade.skywars.cage;

import gg.pixelarcade.skywars.game.state.GameTeamType;
import gg.pixelarcade.skywars.game.state.GameType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.File;

/**
 * @author alfie
 * @project SkyWars
 * @date 18/03/2024 / 22:19
 */

@RequiredArgsConstructor
@Data
public class Cage {

    private final String name;
    private final GameTeamType gameType;

    private String filePath;

    public Cage(String name, GameTeamType gameType, String filePath) {
        this.name = name;
        this.gameType = gameType;
        this.filePath = filePath;
    }
}
