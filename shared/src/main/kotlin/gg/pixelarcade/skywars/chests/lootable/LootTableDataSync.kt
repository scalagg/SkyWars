package gg.pixelarcade.skywars.chests.lootable

import gg.pixelarcade.skywars.chests.lootable.container.LootTableContainer
import gg.scala.commons.persist.datasync.DataSyncKeys
import gg.scala.commons.persist.datasync.DataSyncService
import gg.scala.commons.persist.datasync.DataSyncSource
import gg.scala.flavor.service.Service
import net.kyori.adventure.key.Key

@Service
object LootTableDataSync : DataSyncService<LootTableContainer>()
{
    object LootTableKeys : DataSyncKeys
    {
        override fun newStore() = "skywars-loottables"

        override fun store() = Key.key("skywars", "table")
        override fun sync() = Key.key("skywars", "tables")
    }

    override fun locatedIn() = DataSyncSource.Mongo

    override fun keys() = LootTableKeys
    override fun type() = LootTableContainer::class.java
}
