package gg.scala.skywars.ability.impl

import gg.scala.skywars.ability.Ability
import gg.scala.skywars.ability.type.Interact
import gg.scala.skywars.util.Vector3D
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import kotlin.math.abs

/**
 * @author alfie
 * @project Skywars / gg.scala.game.ability.impl
 * @date 17/10/2023 / 17:06
 */
class Magnet : Ability(), Interact {
    override val name: String
        get() = Color.translate("&d&lMagnet")

    override fun getItem(): ItemStack {
        return ItemBuilder(Material.IRON_BARDING)
            .name(name)
            .addToLore(Color.translate("&7Click in the direction of a nearby player to pull them towards you."))
            .addToLore(" ")
            .addToLore(Color.translate("&e&lNOTE: &fThe heavier the armor the greater the force."))
            .build()
    }

    override fun onPlayerInteract(event: PlayerInteractEvent): Boolean {
        val player = event.player
        val target = getTargetPlayer(player)

        if (target == null) {
            player.sendMessage(Color.translate("&c&lERROR: &fCould not find a player to pull."))
            return false
        }

        val armorWeight = getArmorForce(target.inventory.armorContents)
        val power = armorWeight * 0.5

        val location = player.location
        val targetLocation = target.location
        val direction = targetLocation.toVector().subtract(location.toVector()).normalize()
        direction.multiply(-1)

        target.velocity = direction.multiply(power)
        return true
    }

    private fun getTargetPlayer(player: Player): Player? {
        var targetPlayer: Player? = null
        val playerPos = player.eyeLocation
        val playerDir = Vector3D(playerPos.direction)
        val playerStart = Vector3D(playerPos)
        val playerEnd = playerStart.add(playerDir.multiply(100))

        for (p in player.world.players) {
            val targetPos = Vector3D(p.location)
            val minimum = targetPos.add(-0.5, 0.0, -0.5)
            val maximum = targetPos.add(0.5, 1.67, 0.5)

            if (p !== player && hasIntersection(playerStart, playerEnd, minimum, maximum)) {
                if (targetPlayer == null || targetPlayer.location.distanceSquared(playerPos) > p.location.distanceSquared(
                        playerPos
                    )
                ) {
                    targetPlayer = p
                }
            }
        }

        return targetPlayer
    }

    private fun hasIntersection(p1: Vector3D, p2: Vector3D, min: Vector3D, max: Vector3D): Boolean {
        val epsilon = 0.0001
        val d = p2.subtract(p1).multiply(0.5)
        val e = max.subtract(min).multiply(0.5)
        val c = p1.add(d).subtract(min.add(max).multiply(0.5))
        val ad = d.abs()

        if (abs(c.x) > e.x + ad.x) return false
        if (abs(c.y) > e.y + ad.y) return false
        if (abs(c.z) > e.z + ad.z) return false

        if (abs(d.y * c.z - d.z * c.y) > e.y * ad.z + e.z * ad.y + epsilon) return false
        if (abs(d.z * c.x - d.x * c.z) > e.z * ad.x + e.x * ad.z + epsilon) return false

        return !(abs(d.x * c.y - d.y * c.x) > e.x * ad.y + e.y * ad.x + epsilon)
    }

    // might need to change values, would be good to test on closed beta while playing
    private fun getArmorForce(contents: Array<ItemStack>): Double {
        var armorWeight = 0.0
        for (armor in contents) {
            when (armor.type) {
                Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS -> armorWeight =
                    4.0

                Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS -> armorWeight =
                    3.0

                Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS -> armorWeight =
                    2.0

                Material.GOLD_HELMET, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS -> armorWeight =
                    1.0

                Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS -> armorWeight =
                    0.8

                Material.AIR -> armorWeight = 0.4
                else -> {}
            }
        }

        return armorWeight
    }
}
