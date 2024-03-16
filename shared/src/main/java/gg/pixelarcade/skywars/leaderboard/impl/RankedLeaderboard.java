package gg.pixelarcade.skywars.leaderboard.impl;

import gg.pixelarcade.skywars.leaderboard.Leaderboard;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;

public class RankedLeaderboard extends Leaderboard {

    public RankedLeaderboard() {
        super("ranked", "Ranked Leaderboard");
    }

    @Override
    public int score(SkyWarsProfile profile) {
        return profile.getElo();
    }
}
