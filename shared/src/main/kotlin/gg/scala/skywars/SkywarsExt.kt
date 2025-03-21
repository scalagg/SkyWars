package gg.scala.skywars

import com.lunarclient.apollo.Apollo
import com.lunarclient.apollo.module.richpresence.RichPresenceModule
import com.lunarclient.apollo.module.richpresence.ServerRichPresence
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent

/**
 * @date 11/11/2024
 * @author Moose1301
 */
fun EntityDamageEvent.DamageCause.isEnvironmental(): Boolean {
    return this == EntityDamageEvent.DamageCause.FALL || this == EntityDamageEvent.DamageCause.CONTACT
}

fun Player.setDiscordPresence(presence: ServerRichPresence) {
    val discordModule = Apollo.getModuleManager().getModule(RichPresenceModule::class.java)

    Apollo.getPlayerManager().getPlayer(uniqueId).ifPresent {
        discordModule.overrideServerRichPresence(it, presence)
    }
}

