package gg.pixelarcade.skywars.battlepass

import gg.pixelarcade.skywars.SkyWarsShared
import gg.pixelarcade.skywars.battlepass.challenge.ChallengeHandler
import gg.pixelarcade.skywars.battlepass.challenge.sync.ChallengeDataSync
import gg.pixelarcade.skywars.battlepass.daily.DailyChallengeService
import gg.pixelarcade.skywars.battlepass.service.BattlePassThread
import gg.pixelarcade.skywars.battlepass.tier.TierHandler
import gg.pixelarcade.skywars.battlepass.tier.sync.TierDataSync
import gg.pixelarcade.skywars.battlepass.weekly.WeeklyChallengeService

/**
 * @date 12/9/2024
 * @author Moose1301
 */
class BattlePassManager {

    init {
        TierDataSync.cached().tiers.forEach { TierHandler.register(it) }
        TierHandler.init()

        ChallengeDataSync.cached().challenges.filter { it.isSetup() }.forEach { ChallengeHandler.register(it) }

        BattlePassThread.start()

        SkyWarsShared.instance.plugin.server.scheduler.runTaskTimerAsynchronously(
            SkyWarsShared.instance.plugin,
            DailyChallengeService,
            60 * 20L, 60 * 20L
        )
        SkyWarsShared.instance.plugin.server.scheduler.runTaskTimerAsynchronously(
            SkyWarsShared.instance.plugin,
            WeeklyChallengeService,
            60 * 20L, 60 * 20L
        )
    }

    fun shutdown() {
        val cached = TierDataSync.cached();
        cached.tiers = TierHandler.getAllTiers()
        TierDataSync.sync(cached)


        val challengeCached = ChallengeDataSync.cached();
        challengeCached.challenges = ChallengeHandler.getChallenges()
        ChallengeDataSync.sync(challengeCached)
    }
}