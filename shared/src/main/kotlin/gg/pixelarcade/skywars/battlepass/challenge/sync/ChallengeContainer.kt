package gg.pixelarcade.skywars.battlepass.challenge.sync

import gg.pixelarcade.skywars.battlepass.challenge.Challenge
import gg.pixelarcade.skywars.battlepass.tier.Tier

/**
 * @date 12/10/2024
 * @author Moose1301
 */
data class ChallengeContainer(
    var challenges: List<Challenge> = mutableListOf()
)
