package gg.pixelarcade.skywars.music.listener

import com.xxmicloxx.NoteBlockAPI.event.SongNextEvent
import gg.pixelarcade.skywars.music.MusicHandler
import gg.pixelarcade.skywars.util.PlayerUtil
import gg.scala.commons.util.Players

import net.evilblock.cubed.util.Color
import net.minecraft.server.v1_8_R3.ChatComponentText
import net.minecraft.server.v1_8_R3.PacketPlayOutChat
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

/**
 * @author alfie
 * @project Phoenix-FORK / xyz.refinedev.phoenix.music.listener
 * @date 18/10/2023 / 17:55
 */

class MusicListener(private val handler: MusicHandler) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerJoin(event: PlayerJoinEvent?) {
//       TODO: Disabled for now
//       handler.getRadioSongPlayer().addPlayer(event.getPlayer().getUniqueId());
//       sendActionBar(event.getPlayer(), Color.translate("&6&lPixel Radio &7- &fCurrent Song: &e" + handler.getRadioSongPlayer().getSong().getTitle()));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerQuit(event: PlayerQuitEvent) {
        handler.radioSongPlayer?.removePlayer(event.player.uniqueId)
    }

    @EventHandler
    fun onSongEnd(event: SongNextEvent) {
        val songPlayer = event.songPlayer ?: return

        for (uuid in songPlayer.playerUUIDs) sendActionBar(
            Bukkit.getPlayer(uuid),
            Color.translate("&6&lPixel Radio &7- &fNext Song: &e" + event.songPlayer.song.title)
        )
    }

    fun sendActionBar(player: Player, string: String?) {
        (player as CraftPlayer).handle.playerConnection.sendPacket(
            PacketPlayOutChat(
                ChatComponentText(string),
                2.toByte()
            )
        )
    }
}