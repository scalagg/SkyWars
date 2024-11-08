package gg.pixelarcade.skywars.cages.sync

import gg.pixelarcade.skywars.cages.container.SchemaCageContainer
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
object SchemaCageDataSync : DataSyncService<SchemaCageContainer>()
{
    object SchemaCageKeys : DataSyncKeys
    {
        override fun newStore() = "skywars-schema-cages"

        override fun store() = Key.key("skywars", "sccg")
        override fun sync() = Key.key("skywars", "sccgs")
    }

    override fun locatedIn() = DataSyncSource.Mongo

    override fun keys() = SchemaCageKeys
    override fun type() = SchemaCageContainer::class.java
}
