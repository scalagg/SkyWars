package gg.scala.skywars.feature

import gg.scala.commons.annotations.plugin.SoftDependency
import gg.scala.flavor.service.Configure
import gg.scala.flavor.service.Service
import gg.scala.flavor.service.ignore.IgnoreAutoScan
import gg.scala.lemon.channel.channels.DefaultChatChannel
import gg.scala.game.extensions.cosmetics.CosmeticLocalConfig
import gg.scala.skywars.SkyWarsShared
import net.evilblock.cubed.util.CC
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

/**
 * @author Topu
 * @date 3/22/2025
 */
@Service
@IgnoreAutoScan
@SoftDependency("CoreGameExtensions")
object CoreGameExtensionsFeature
{
    @Configure
    fun configure()
    {
        DefaultChatChannel.provideAdditionalPrefix { player ->
            val profile = SkyWarsShared.instance.getProfile(player.uniqueId)
            val level = profile.level
            val prestige = profile.getPrestige(level)
            LegacyComponentSerializer.legacySection()
                .deserialize("${prestige.color}[$level${prestige.star}] ${CC.RESET}")
        }
    }
}