package gg.scala.skywars.battlepass.daily.sync

import gg.scala.skywars.battlepass.daily.DailyChallengeSession
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
object DailyChallengeDataSync : DataSyncService<DailyChallengeSession>()
{
    object DailyChallengeKeys : DataSyncKeys
    {
        override fun newStore() = "skywars-bp-daily-challenge"

        override fun store() = Key.key("skywars", "bp-daily-challenge")
        override fun sync() = Key.key("skywars", "bp-daily-challenges")
    }

    override fun locatedIn() = DataSyncSource.Mongo

    override fun keys() = DailyChallengeKeys
    override fun type() = DailyChallengeSession::class.java
}
