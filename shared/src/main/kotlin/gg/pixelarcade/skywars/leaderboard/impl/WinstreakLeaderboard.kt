package gg.pixelarcade.skywars.leaderboard.impl

import gg.pixelarcade.skywars.leaderboard.Leaderboard
import gg.pixelarcade.skywars.profile.SkyWarsProfile

class WinstreakLeaderboard : Leaderboard("winstreak", "Winstreak Leaderboard") {
    //TODO: implement when winstreak are implemented
    override fun score(profile: SkyWarsProfile): Int {
        return 0
    }
}
