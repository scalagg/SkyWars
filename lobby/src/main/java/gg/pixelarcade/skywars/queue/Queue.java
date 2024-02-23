package gg.pixelarcade.skywars.queue;

import gg.pixelarcade.skywars.game.state.GameType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author alfie
 * @project SkyWars
 * @date 30/10/2023 / 22:24
 */

@RequiredArgsConstructor
@Data
public class Queue {

    private final GameType gameType;
    private List<UUID> queuedPlayers = new ArrayList<>();

}
