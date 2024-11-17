package gg.pixelarcade.skywars.arena.container

import gg.scala.commons.persist.datasync.DataSyncKeys
import gg.scala.commons.persist.datasync.DataSyncService
import gg.scala.commons.persist.datasync.DataSyncSource
import gg.scala.flavor.service.Service
import net.kyori.adventure.key.Key

@Service
object ArenaDataSync : DataSyncService<ArenaContainer>()
{
    object ArenaDataKeys : DataSyncKeys
    {
        override fun newStore() = "skywars-arena"

        override fun store() = Key.key("skywars", "arena")
        override fun sync() = Key.key("skywars", "arenas")
    }

    override fun locatedIn() = DataSyncSource.Mongo

    override fun keys() = ArenaDataKeys
    override fun type() = ArenaContainer::class.java
}
