package gg.scala.skywars.ability.impl

import gg.scala.skywars.ability.Ability
import gg.scala.skywars.ability.type.Interact
import gg.scala.skywars.util.VelocityUtil.throwEntity
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

/**
 * @author alfie
 * @project Skywars / gg.scala.game.ability.impl
 * @date 06/10/2023 / 15:20
 */
class ThrowableTNT : Ability(), Interact {
    override val name: String
        get() = Color.translate("&6&lThrowable TNT")

    override fun getItem(): ItemStack {
        return ItemBuilder(Material.TNT)
            .name(name)
            .addToLore(Color.translate("&7Right click to throw TNT"))
            .build()
    }

    override fun onPlayerInteract(event: PlayerInteractEvent): Boolean {
        val player = event.player
        val location = player.eyeLocation

        val primedTNT = player.world.spawn(location, TNTPrimed::class.java)
        primedTNT.fuseTicks = FUSE_TICKS

        throwEntity(primedTNT, location.direction, 1.3, 0.3, 1.0, false)
        return true
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        val item = event.itemInHand
        if (item == null || item.type != Material.TNT) return

        if (!item.hasItemMeta() && !item.itemMeta.hasDisplayName()) return

        if (!item.itemMeta.displayName.contains("Throwable")) return

        event.isCancelled = true
    }

    companion object {
        private const val FUSE_TICKS = 60
    }
}
