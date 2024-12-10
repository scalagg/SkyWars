package gg.pixelarcade.skywars.battlepass.weekly.sync

import gg.pixelarcade.skywars.battlepass.weekly.WeeklyChallengeSession
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
object WeeklyChallengeDataSync : DataSyncService<WeeklyChallengeSession>()
{
    object WeeklyChallengeKeys : DataSyncKeys
    {
        override fun newStore() = "skywars-bp-weekly-challenge"

        override fun store() = Key.key("skywars", "bp-weekly-challenge")
        override fun sync() = Key.key("skywars", "bp-weekly-challenges")
    }

    override fun locatedIn() = DataSyncSource.Mongo

    override fun keys() = WeeklyChallengeKeys
    override fun type() = WeeklyChallengeSession::class.java
}
