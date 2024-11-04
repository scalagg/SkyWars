package gg.pixelarcade.skywars.ability.impl

import gg.pixelarcade.skywars.ability.Ability
import gg.pixelarcade.skywars.ability.type.Interact
import net.evilblock.cubed.util.Color
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.bukkit.Tasks.delayed
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.List

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.ability.impl
 * @date 17/10/2023 / 17:06
 */
class ZombieGuardian : Ability(), Interact {
    override val name: String
        get() = Color.translate("&b&lZombie Guardian")

    override fun getItem(): ItemStack {
        return ItemBuilder(Material.MONSTER_EGG)
            .data(54.toShort())
            .name(name)
            .setLore(
                Color.translate(
                    List.of(
                        "&7Click to spawn a Zombie Guardian which will fight for you.",
                        " ",
                        "&e&lNOTE: &fZombie Guardian will only last for " + ZOMBIE_DURATION + " seconds."
                    )
                )
            ).build()
    }

    override fun onPlayerInteract(event: PlayerInteractEvent): Boolean {
        val player = event.player

        val zombie = player.world.spawnEntity(player.location, EntityType.ZOMBIE) as Zombie
        zombie.isBaby = false
        zombie.isVillager = false
        zombie.customName = Color.translate("&b&lZombie Guardian")
        zombie.isCustomNameVisible = true
        zombie.removeWhenFarAway = false
        zombie.addPotionEffect(PotionEffect(PotionEffectType.FIRE_RESISTANCE, Int.MAX_VALUE, 0, false, false))

        for (entity in zombie.getNearbyEntities(
            ZOMBIE_RADIUS.toDouble(),
            ZOMBIE_RADIUS.toDouble(),
            ZOMBIE_RADIUS.toDouble()
        )) {
            if (entity is Player) {
                if (entity === player) continue

                entity.sendMessage(Color.translate("&b&lZombie Guardian &7has been spawned by &b" + player.name + "&7."))
                entity.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 20 * 3, 0, false, false))
                entity.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 20 * 3, 0, false, false))
            }
        }

        delayed((20 * ZOMBIE_DURATION).toLong(), Runnable { zombie.remove() })
        return true
    }

    companion object {
        private const val ZOMBIE_DURATION = 30
        private const val ZOMBIE_RADIUS = 5
    }
}
