package gg.scala.skywars.battlepass.tier


import gg.scala.skywars.battlepass.tier.sync.TierDataSync
import java.util.concurrent.ConcurrentHashMap

object TierHandler {

    private val tiers = ConcurrentHashMap<Int, Tier>()

    fun init() {
        IntRange(1, 20).forEach { tiers.putIfAbsent(it, Tier(it)) }
    }

    fun register(tier: Tier) {
        tier.freeReward?.tier = tier
        tier.premiumReward?.tier = tier

        tiers[tier.number] = tier
    }

    fun saveData() {
        val cached = TierDataSync.cached();
        cached.tiers = getAllTiers()
        TierDataSync.sync(cached)
    }

    fun getAllTiers(): List<Tier> {
        return tiers.values.toList()
    }

    fun getTierByNumber(number: Int): Tier? {
        return tiers[number]
    }

}