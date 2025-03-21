package gg.scala.skywars.ability

import gg.scala.skywars.SkyWarsPlugin
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack

/**
 * @author alfie
 * @project Skywars / gg.scala.game.ability
 * @date 03/10/2023 / 06:01
 */
abstract class Ability : Listener {
    init {
        Bukkit.getServer().pluginManager.registerEvents(this, SkyWarsPlugin.instance)
    }

    abstract val name: String?

    abstract fun getItem(): ItemStack

    fun isSimilar(item: ItemStack?): Boolean {
        if (item == null) return false

        if (item.itemMeta.displayName != this.getItem().itemMeta.displayName) return false

        if (item.itemMeta.lore != this.getItem().itemMeta.lore) return false

        return item.type == this.getItem().type
    }

    fun consume(player: Player) {
        if (player.itemInHand.amount == 1) player.itemInHand = null
        else player.itemInHand.amount -= 1
    }
}
