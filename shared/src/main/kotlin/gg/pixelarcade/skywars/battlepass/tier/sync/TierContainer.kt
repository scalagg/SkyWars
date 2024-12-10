package gg.pixelarcade.skywars.battlepass.tier.sync

import gg.pixelarcade.skywars.battlepass.tier.Tier
import gg.pixelarcade.skywars.cages.Cage

/**
 * @author GrowlyX
 * @since 8/24/2024
 */
data class TierContainer(
    var tiers: List<Tier> = mutableListOf()
)
