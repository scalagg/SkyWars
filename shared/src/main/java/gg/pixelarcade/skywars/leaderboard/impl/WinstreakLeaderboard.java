package gg.pixelarcade.skywars.leaderboard.impl;

import gg.pixelarcade.skywars.leaderboard.Leaderboard;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;

public class WinstreakLeaderboard extends Leaderboard {
    public WinstreakLeaderboard() {
        super("winstreak", "Winstreak Leaderboard");
    }

    @Override //TODO: implement when winstreak are implemented
    public int score(SkyWarsProfile profile) {
        return 0;
    }
}
