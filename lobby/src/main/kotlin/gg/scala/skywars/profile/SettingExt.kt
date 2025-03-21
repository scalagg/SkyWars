package gg.scala.skywars.profile

import gg.scala.basics.plugin.profile.BasicsProfileService
import gg.scala.basics.plugin.settings.defaults.values.StateSettingValue
import org.bukkit.entity.Player

/**
 * @date 11/1/2024
 * @author Moose1301
 */
fun Player.hasMusicEnabled(): Boolean
{
    val profile = BasicsProfileService.find(this) ?: return false

    return profile.setting<StateSettingValue>("skywars:music") == StateSettingValue.ENABLED
}
