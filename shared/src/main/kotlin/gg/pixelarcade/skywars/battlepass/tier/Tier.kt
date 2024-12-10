package gg.pixelarcade.skywars.battlepass.tier

data class Tier(val number: Int) {

    var freeReward: TierReward? = null
    var premiumReward: TierReward? = null
    var requiredExperience: Int = 999

}