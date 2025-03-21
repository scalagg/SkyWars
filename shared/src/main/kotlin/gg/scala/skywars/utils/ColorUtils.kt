package gg.scala.skywars.utils

import org.bukkit.ChatColor

/**
 * @date 12/10/2024
 * @author Moose1301
 */
object ColorUtils {
    fun colorPercentage(percentage: Double): ChatColor {
        return when {
            percentage >= 100.0 -> ChatColor.GREEN
            percentage >= 30.0 -> ChatColor.YELLOW
            else -> ChatColor.RED
        }
    }
}