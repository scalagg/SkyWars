package gg.scala.skywars.battlepass.tier.sync

import gg.scala.commons.persist.datasync.DataSyncKeys
import gg.scala.commons.persist.datasync.DataSyncService
import gg.scala.commons.persist.datasync.DataSyncSource
import gg.scala.flavor.service.Service
import net.kyori.adventure.key.Key

/**
 * @author GrowlyX
 * @since 8/24/2024
 */
@Service
object TierDataSync : DataSyncService<TierContainer>()
{
    object TierKeys : DataSyncKeys
    {
        override fun newStore() = "skywars-bp-tier"

        override fun store() = Key.key("skywars", "bp-tier")
        override fun sync() = Key.key("skywars", "bp-tiers")
    }

    override fun locatedIn() = DataSyncSource.Mongo

    override fun keys() = TierKeys
    override fun type() = TierContainer::class.java
}
