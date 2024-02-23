package gg.pixelarcade.skywars.game;

import gg.pixelarcade.skywars.chests.type.RefillType;
import gg.pixelarcade.skywars.game.state.GameState;
import gg.pixelarcade.skywars.game.state.GameType;
import lombok.Data;

import java.util.*;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.game
 * @date 26/09/2023 / 20:43
 */

@Data
public class Game {

    public final static int SECOND_REFILL_DELAY = 20 * 60 * 3;
    public final static int THIRD_REFILL_DELAY = 20 * 60 * 5;
    public final static int SUDDEN_DEATH_COMMENCES = 20 * 60 * 10;

    private final UUID id = UUID.randomUUID();

    private GameType gameType;

    private Set<UUID> spectators = new HashSet<>();
    private Set<UUID> players = new HashSet<>();
    private Set<UUID> alivePlayers = new HashSet<>();

    private Map<UUID, Integer> kills = new HashMap<>();

    private RefillType refillType = RefillType.FIRST;
    private GameState state = GameState.WAITING;

    private Date startedAt;
    private Date endedAt;

    private boolean forceStarted = false;
    private boolean active = false;

    public Game(GameType gameType) {
        this.gameType = gameType;
    }
}
