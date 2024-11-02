package gg.pixelarcade.skywars.leaderboard.impl

import gg.pixelarcade.skywars.leaderboard.Leaderboard
import gg.pixelarcade.skywars.profile.SkyWarsProfile

class WinsLeaderboard : Leaderboard("wins", "Wins Leaderboard") {
    override fun score(profile: SkyWarsProfile): Int {
        return profile.wins.value
    }
}
