package gg.scala.skywars.cages.sync

import gg.scala.skywars.cages.container.CageContainer
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
object CageDataSync : DataSyncService<CageContainer>()
{
    object CageKeys : DataSyncKeys
    {
        override fun newStore() = "skywars-cages"

        override fun store() = Key.key("skywars", "cg")
        override fun sync() = Key.key("skywars", "cgs")
    }

    override fun locatedIn() = DataSyncSource.Mongo

    override fun keys() = CageKeys
    override fun type() = CageContainer::class.java
}
