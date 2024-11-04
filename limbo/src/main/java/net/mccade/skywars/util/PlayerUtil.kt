package net.mccade.skywars.util

import org.bukkit.GameMode
import org.bukkit.entity.Player

object PlayerUtil {
    fun resetPlayer(player: Player) {
        player.inventory.clear()
        player.activePotionEffects.clear()
        player.inventory.armorContents = arrayOfNulls(4)

        player.gameMode = GameMode.SURVIVAL
        player.health = 20.0
        player.foodLevel = 20
        player.fireTicks = 0
        player.exp = 0f
        player.level = 0

        player.isFlying = false
        player.allowFlight = false
    }
}
