package gg.scala.skywars.item

import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * @author alfie
 * @project Skywars
 * @date 25/09/2023 / 22:24
 */
abstract class Item {
    constructor(player: Player) {
        if (!HOTBAR_ITEMS.containsKey(player.uniqueId)) {
            HOTBAR_ITEMS[player.uniqueId] =
                ConcurrentHashMap()
        }

        HOTBAR_ITEMS[player.uniqueId]!![javaClass.simpleName] = this
    }

    constructor(player: Player, id: String) {
        if (!HOTBAR_ITEMS.containsKey(player.uniqueId)) {
            HOTBAR_ITEMS[player.uniqueId] =
                ConcurrentHashMap()
        }

        HOTBAR_ITEMS[player.uniqueId]!![id] = this
    }

    abstract fun getItem(player: Player): ItemStack
    abstract fun click(action: Action)

    companion object {
        @JvmField
        val HOTBAR_ITEMS: ConcurrentHashMap<UUID, ConcurrentHashMap<String, Item>> = ConcurrentHashMap()

        fun unregisterItem(player: Player, id: String) {
            if (!HOTBAR_ITEMS.containsKey(player.uniqueId)) {
                return
            }

            HOTBAR_ITEMS[player.uniqueId]!!.remove(id)
        }
    }
}
