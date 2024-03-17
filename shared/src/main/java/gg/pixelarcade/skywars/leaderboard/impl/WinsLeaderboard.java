package gg.pixelarcade.skywars.leaderboard.impl;

import gg.pixelarcade.skywars.leaderboard.Leaderboard;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;

public class WinsLeaderboard extends Leaderboard {

    public WinsLeaderboard() {
        super("wins", "Wins Leaderboard");
    }

    @Override
    public int score(SkyWarsProfile profile) {
        return profile.getWins();
    }
}
