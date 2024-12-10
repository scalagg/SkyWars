package gg.pixelarcade.skywars.battlepass.challenge.sync

import gg.pixelarcade.skywars.battlepass.daily.DailyChallengeSession
import gg.pixelarcade.skywars.battlepass.tier.sync.TierContainer
import gg.scala.commons.persist.datasync.DataSyncKeys
import gg.scala.commons.persist.datasync.DataSyncService
import gg.scala.commons.persist.datasync.DataSyncSource
import gg.scala.flavor.service.Service
import net.kyori.adventure.key.Key

/**
 * @date 12/10/2024
 * @author Moose1301
 */
@Service
object ChallengeDataSync : DataSyncService<ChallengeContainer>()
{
    object ChallengeKeys : DataSyncKeys
    {
        override fun newStore() = "skywars-bp-challenge"

        override fun store() = Key.key("skywars", "bp-challenge")
        override fun sync() = Key.key("skywars", "bp-challenges")
    }

    override fun locatedIn() = DataSyncSource.Mongo

    override fun keys() = ChallengeKeys
    override fun type() = ChallengeContainer::class.java
}
