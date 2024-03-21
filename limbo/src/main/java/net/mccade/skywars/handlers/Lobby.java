package net.mccade.skywars.handlers;

import gg.pixelarcade.skywars.game.state.GameTeamType;
import gg.pixelarcade.skywars.game.state.GameType;
import lombok.Data;
import net.mccade.skywars.handlers.task.CountdownTask;
import net.mccade.skywars.util.LimboState;
import net.mccade.skywars.util.StartingState;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class Lobby {

    private final UUID id = UUID.randomUUID();

    private GameType gameType;
    private GameTeamType gameTeamType;

    private LimboState limboState;
    private StartingState startingState;
    private String serverName;
    private boolean serverDeployed;
    private CountdownTask countdown;

    private Set<UUID> players = new HashSet<>();

    public Lobby(GameType gameType, GameTeamType gameTeamType) {
        this.gameType = gameType;
        this.gameTeamType = gameTeamType;

        this.limboState = LimboState.STALE;
        this.startingState = StartingState.WAITING;
        this.serverName = "";
        this.serverDeployed = false;
    }

    public boolean isRanked() {
        return this.gameType == GameType.RANKED;
    }
}