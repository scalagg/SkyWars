package gg.scala.skywars.leaderboard.impl

import gg.scala.skywars.leaderboard.Leaderboard
import gg.scala.skywars.profile.SkyWarsProfile

class LevelLeaderboard : Leaderboard("levels", "Level Leaderboard") {
    override fun score(profile: SkyWarsProfile): Int {
        return profile.level
    }
}
