package gg.pixelarcade.skywars.leaderboard.impl;

import gg.pixelarcade.skywars.leaderboard.Leaderboard;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;

public class LevelLeaderboard extends Leaderboard {

    public LevelLeaderboard() {
        super("levels", "Level Leaderboard");
    }

    @Override
    public int score(SkyWarsProfile profile) {
        return profile.getLevel();
    }
}
