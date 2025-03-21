package gg.scala.skywars.leaderboard.impl

import gg.scala.skywars.leaderboard.Leaderboard
import gg.scala.skywars.profile.SkyWarsProfile

class WinstreakLeaderboard : Leaderboard("winstreak", "Winstreak Leaderboard") {
    //TODO: implement when winstreak are implemented
    override fun score(profile: SkyWarsProfile): Int {
        return 0
    }
}
