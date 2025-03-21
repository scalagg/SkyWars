package gg.scala.skywars.leaderboard.impl

import gg.scala.skywars.leaderboard.Leaderboard
import gg.scala.skywars.profile.SkyWarsProfile

class WinsLeaderboard : Leaderboard("wins", "Wins Leaderboard") {
    override fun score(profile: SkyWarsProfile): Int {
        return profile.wins.value
    }
}
