package gg.pixelarcade.skywars.battlepass.tier


import gg.pixelarcade.skywars.battlepass.tier.sync.TierDataSync
import java.util.concurrent.ConcurrentHashMap

object TierHandler {

    private val tiers = ConcurrentHashMap<Int, Tier>()

    fun init() {
        IntRange(1, 20).forEach { this.tiers.putIfAbsent(it, Tier(it)) }
    }

    fun register(tier: Tier) {
        tier.freeReward?.tier = tier
        tier.premiumReward?.tier = tier

        this.tiers[tier.number] = tier
    }

    fun saveData() {
        val cached = TierDataSync.cached();
        cached.tiers = getAllTiers()
        TierDataSync.sync(cached)
    }

    fun getAllTiers(): List<Tier> {
        return this.tiers.values.toList()
    }

    fun getTierByNumber(number: Int): Tier? {
        return this.tiers[number]
    }

}