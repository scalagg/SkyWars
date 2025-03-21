package gg.scala.skywars.battlepass.tier.sync

import gg.scala.skywars.battlepass.tier.Tier
import gg.scala.skywars.cages.Cage

/**
 * @author GrowlyX
 * @since 8/24/2024
 */
data class TierContainer(
    var tiers: List<Tier> = mutableListOf()
)
