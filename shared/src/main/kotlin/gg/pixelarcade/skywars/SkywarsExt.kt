package gg.pixelarcade.skywars

import org.bukkit.event.entity.EntityDamageEvent

/**
 * @date 11/11/2024
 * @author Moose1301
 */
fun EntityDamageEvent.DamageCause.isEnvironmental(): Boolean {
    return this == EntityDamageEvent.DamageCause.FALL || this == EntityDamageEvent.DamageCause.CONTACT
}