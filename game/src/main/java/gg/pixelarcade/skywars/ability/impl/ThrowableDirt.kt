package gg.pixelarcade.skywars.ability.impl

import gg.pixelarcade.skywars.ability.Ability
import gg.pixelarcade.skywars.ability.type.Interact
import gg.pixelarcade.skywars.util.VelocityUtil.knockback
import gg.pixelarcade.skywars.util.VelocityUtil.throwEntity
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.FallingBlock
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityChangeBlockEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.ability.impl
 * @date 06/10/2023 / 15:53
 */
class ThrowableDirt : Ability(), Interact {
    override val name: String
        get() = Color.translate("&d&lThrowable Dirt")

    override fun getItem(): ItemStack {
        return ItemBuilder(Material.DIRT)
            .name(name)
            .addToLore(Color.translate("&7Right click to throw dirt, anyone hit by it will be knocked back"))
            .enchant(Enchantment.DURABILITY, 1)
            .build()
    }

    override fun onPlayerInteract(event: PlayerInteractEvent): Boolean {
        val player = event.player
        val location = player.eyeLocation

        val fallingBlock = player.world.spawnFallingBlock(location, Material.DIRT, 0.toByte())
        fallingBlock.dropItem = false

        throwEntity(fallingBlock, location.direction, 1.3, 0.3, 1.0, false)
        return true
    }

    @EventHandler
    fun onEntityChangeBlock(event: EntityChangeBlockEvent) {
        if (event.entity !is FallingBlock) return

        val fallingBlock = event.entity as FallingBlock;

        if (fallingBlock.material != Material.DIRT) return

        for (entity in fallingBlock.getNearbyEntities(RADIUS.toDouble(), RADIUS.toDouble(), RADIUS.toDouble())) {
            if (entity !is Player) continue

            knockback(entity, fallingBlock.location, 1.0, 0.5)
            entity.playSound(entity.getLocation(), Sound.EXPLODE, 1f, 1f)
        }

        event.isCancelled = true
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
        private const val RADIUS = 4
    }
}
